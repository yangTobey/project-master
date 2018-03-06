package com.spring.boot.controller;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.service.SysUserService;
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
public class SysUserController {
    private static final Logger logger = Logger.getLogger(SysUserController.class);
    @Autowired
    private SysUserService sysUserService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        System.out.println("hello world!");
        return "views/main/index";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public SysUser findByUserId(@RequestParam(value = "id", required = true) String id) {
        return sysUserService.findByUserId(id);
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
        int count = sysUserService.updatePassword(ShiroUtils.getUserEntity().getUserId(), password, newPassword);
        if(count>0){
            return "修改成功！";
        }
        return "error";

    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(String userAccount, String password,String companyId,String roleId,String departmentId) {
        if(UtilHelper.isEmpty(userAccount)){
            return "登录账号不能为空！";
        }
        if(UtilHelper.isEmpty(password)){
            return "登录密码不能为空！";
        }
        SysUser user = sysUserService.findByUserAccount(userAccount);
        if(user!=null){
            return "该账号已存在，不能再次添加！！";
        }
        int count = sysUserService.addUser(userAccount, password, companyId,roleId,departmentId);
        if(count>0){
            return "修改成功！";
        }
        return "error";

    }

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.GET)
    public String updateUserInfo(String userId,String companyId,String roleId,String departmentId) {
        if(UtilHelper.isEmpty(userId)){
            return "用户编号不能为空，请联系系统管理员！";
        }
        int count = sysUserService.updateUserInfo(userId, companyId,roleId,departmentId);
        if(count>0){
            return "修改成功！";
        }
        return "error";

    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(String userId) {
        if(UtilHelper.isEmpty(userId)){
            return "用户编号不能为空，请联系系统管理员！";
        }
        int count = sysUserService.deleteUser(userId);
        if(count>0){
            return "修改成功！";
        }
        return "error";

    }

}
