package com.spring.boot.controller;

import com.spring.boot.bean.master.User;
import com.spring.boot.service.UserService;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2018/1/25.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        System.out.println("hello world!");
        return "views/main/index";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public User findByUserId(@RequestParam(value = "id", required = true) String id) {
        return userService.findByUserId(id);
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
    public String updatePassword(String password, String newPassword) {
        logger.info("hello world");
        if(UtilHelper.isEmpty(password)){
           return "原密码不能为空！";
        }
        if(UtilHelper.isEmpty(newPassword)){
            return "新密码不能为空！";
        }
        int count = userService.updatePassword(ShiroUtils.getUserEntity().getUserId(), password, newPassword);
        if(count>0){
            return "修改成功！";
        }
        return "error";

    }

}
