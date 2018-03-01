package com.spring.boot.controller;

import com.spring.boot.service.UserService;
import com.spring.boot.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2018/2/6.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录系统
     * @param userName 账号
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String userName,String password){
        try{
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            //password = new Sha256Hash(password).toHex();
           // password= new SimpleHash("md5", password,  ByteSource.Util.bytes(userName), 2).toHex();
            System.out.println("密码："+new SimpleHash("md5", password,  ByteSource.Util.bytes(userName), 2).toHex());
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
            //验证是否通过
            if(subject.isAuthenticated()){
                System.out.println("登录成功，请进行对应操作！！");
            }
        }catch (UnknownAccountException e) {
            e.printStackTrace();
           return "views/login/login";
        }catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            //return R.error(e.getMessage());
        }catch (LockedAccountException e) {
            e.printStackTrace();
            return "views/login/login";
        }catch (AuthenticationException e) {
            e.printStackTrace();
            return "views/login/login";
        }
        return "views/login/login";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        //清除登录缓存
        ShiroUtils.logout();
        return "views/login/login";
    }
}
