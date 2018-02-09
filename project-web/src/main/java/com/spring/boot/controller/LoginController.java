package com.spring.boot.controller;

import com.spring.boot.bean.User;
import com.spring.boot.service.UserService;
import com.spring.boot.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
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
            password = new Sha256Hash(password).toHex();
            System.out.println("密码："+password);
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
        }catch (UnknownAccountException e) {
           return "views/login/login";
        }catch (IncorrectCredentialsException e) {
            //return R.error(e.getMessage());
        }catch (LockedAccountException e) {
            return "views/login/login";
        }catch (AuthenticationException e) {
            return "views/login/login";
        }
        return "views/login/login";
    }
}
