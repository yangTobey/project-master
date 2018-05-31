package com.spring.boot.controller;

import com.spring.boot.service.SysUserService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2018/2/6.
 */
@Controller
@RequestMapping("/login")
public class SysLoginController {
    private static final Logger logger = Logger.getLogger(SysLoginController.class);

    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录系统
     * @param userAccount 账号
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public R login(@RequestParam(value = "userAccount", required = false)String userAccount,@RequestParam(value = "password", required = false) String password){
        try{
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            //password = new Sha256Hash(password).toHex();
           // password= new SimpleHash("md5", password,  ByteSource.Util.bytes(userName), 2).toHex();
            //logger.info("密码："+new SimpleHash("md5", password,  null, 2).toHex());
            UsernamePasswordToken token = new UsernamePasswordToken(userAccount, password);
            subject.login(token);
            //验证是否通过
            if(subject.isAuthenticated()){
                logger.info("登录成功，请进行对应操作！！");
            }
        }catch (UnknownAccountException e) {
            return R.error(e.getMessage());
        }catch (IncorrectCredentialsException e) {
            return R.error(e.getMessage());
        }catch (LockedAccountException e) {
            return R.error(e.getMessage());
        }catch (AuthenticationException e) {
            return R.error(e.getMessage());
        }
        return R.ok(200,"成功！");
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String logout() {
        //清除登录缓存
        ShiroUtils.logout();
        if (SecurityUtils.getSubject() != null) {
            if (SecurityUtils.getSubject().getSession() != null) {
                SecurityUtils.getSubject().getSession().removeAttribute("user");
            }
        }
        //重定向到初始地址，详细请见WebMvcConfig类
        return "redirect:/";
    }
}
