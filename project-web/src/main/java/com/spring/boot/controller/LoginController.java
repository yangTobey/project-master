package com.spring.boot.controller;

import com.spring.boot.bean.master.User;
import com.spring.boot.service.UserService;
import com.spring.boot.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/2/6.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(String userName,String password){
        /*User user=userService.findByUserAccount(userName);
        if(user!=null){
            if(password.equals(user.getPassword())){

            }
        }else{

        }*/
        System.out.println("账号："+userName+"，密码："+password);
        try{
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            //password = new Sha256Hash(password).toHex();
           // password= new SimpleHash("md5", password,  ByteSource.Util.bytes(userName), 2).toHex();

            System.out.println("密码："+password);
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
            //验证是否通过
            System.out.println("登录："+subject.isAuthenticated());
            if(subject.isAuthenticated()){
                System.out.println("登录成功，请进行对应操作！！");
            }
        }catch (UnknownAccountException e) {
            System.out.println("AA");
            e.printStackTrace();
           return "views/login/login";
        }catch (IncorrectCredentialsException e) {
            System.out.println("BB");
            e.printStackTrace();
            //return R.error(e.getMessage());
        }catch (LockedAccountException e) {
            System.out.println("CC");
            e.printStackTrace();
            return "views/login/login";
        }catch (AuthenticationException e) {
            System.out.println("DD");
            e.printStackTrace();
            return "views/login/login";
        }
        return "views/login/login";
    }
}
