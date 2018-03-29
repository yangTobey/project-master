package com.spring.boot.controller;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.service.SysUserService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@RestController
@RequestMapping("/sysUser")
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
    public R updatePassword(@RequestParam(value = "password", required = false) String password,@RequestParam(value = "newPassword", required = false)  String newPassword) {
        logger.info("hello world");
        if(UtilHelper.isEmpty(password)){
            return R.error(400, "原密码不能为空！");
        }else if(UtilHelper.isEmpty(newPassword)){
            return R.error(400, "新密码不能为空！");
        }
        Map<String, Object> map=  sysUserService.updatePassword(ShiroUtils.getUserEntity().getUserId(), password, newPassword);
        return R.ok(map);
    }

    /**
     * 重置密码
     * @param userId
     * @return
     */
    @RequestMapping(value = "/resetSysUserPassword", method = RequestMethod.GET)
    public R resetSysUserPassword(@RequestParam(value = "userId", required = false) String userId) {
        if(!UtilHelper.isNumer(userId)){
            return R.error(400, "用户id格式不正确！");
        }
        Map<String, Object> map=  sysUserService.resetSysUserPassword(Long.valueOf(userId));
        return R.ok(map);
    }

    @RequestMapping(value = "/addSysUser", method = RequestMethod.GET)
    public R addUser(@RequestParam(value = "userAccount", required = false)String userAccount,@RequestParam(value = "password", required = false) String password
            ,@RequestParam(value = "companyId", required = false) String companyId,@RequestParam(value = "roleId", required = false) String roleId
            ,@RequestParam(value = "departmentId", required = false) String departmentId) {
        if(UtilHelper.isEmpty(userAccount)){
            return R.error(400, "登录账号不能为空！");
        }else if(UtilHelper.isEmpty(password)){
            return R.error(400, "登录密码不能为空！");
        }else if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }else if (!UtilHelper.isNumer(roleId)) {
            return R.error(400, "角色id格式不正确！");
        }else if (!UtilHelper.isNumer(departmentId)) {
            return R.error(400, "部门id格式不正确！");
        }
        Map<String, Object> map=  sysUserService.addUser(userAccount, password, Long.valueOf(companyId),Long.valueOf(roleId),Long.valueOf(departmentId));
        return R.ok(map);

    }

    /**
     * 获取系统用户列表
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping(value = "/sysUserList", method = RequestMethod.GET)
    public R sysUserList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        }else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map=  sysUserService.sysUserList(Integer.valueOf(limit), Integer.valueOf(offset));
        return R.ok(map);

    }

    @RequestMapping(value = "/updateSysUserInfo", method = RequestMethod.GET)
    public R  updateUserInfo(@RequestParam(value = "userId", required = false)String userId,@RequestParam(value = "companyId", required = false)String companyId
            ,@RequestParam(value = "roleId", required = false)String roleId,@RequestParam(value = "departmentId", required = false)String departmentId) {
        if(!UtilHelper.isNumer(userId)){
            return R.error(400, "用户编号不能为空，请联系系统管理员！");
        }else if (!UtilHelper.isNumer(companyId)) {
            return R.error(400, "公司id格式不正确！");
        }else if (!UtilHelper.isNumer(roleId)) {
            return R.error(400, "角色id格式不正确！");
        }else if (!UtilHelper.isNumer(departmentId)) {
            return R.error(400, "部门id格式不正确！");
        }
        Map<String, Object> map= sysUserService.updateUserInfo(Long.valueOf(userId), Long.valueOf(companyId),Long.valueOf(roleId),Long.valueOf(departmentId));
        return R.ok(map);

    }
    @RequestMapping(value = "/deleteSysUser", method = RequestMethod.GET)
    public R  deleteUser(@RequestParam(value = "userId", required = false)String userId) {
        if(!UtilHelper.isNumer(userId)){
            return R.error(400, "用户编号不能为空，请联系系统管理员！");
        }
        Map<String, Object> map=  sysUserService.deleteUser(userId,"delete");
        return R.ok(map);

    }
    @RequestMapping(value = "/closeSysUserAccount", method = RequestMethod.GET)
    public R  closeSysUserAccount(@RequestParam(value = "userId", required = false)String userId) {
        if(!UtilHelper.isNumer(userId)){
            return R.error(400, "用户编号不能为空，请联系系统管理员！");
        }
        Map<String, Object> map=  sysUserService.deleteUser(userId,"close");
        return R.ok(map);

    }

}
