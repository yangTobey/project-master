package com.spring.boot.controller;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.service.SysRoleService;
import com.spring.boot.service.SysUserService;
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
@RequestMapping("/sysRole")
public class SysRoleController {
    private static final Logger logger = Logger.getLogger(SysRoleController.class);
    @Autowired
    private SysRoleService sysRoleService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        System.out.println("hello world!");
        return "views/main/index";
    }

    @RequestMapping(value = "/getSysRoleList", method = RequestMethod.GET)
    public Map<String, Object> getSysRoleList() {

        Map<String, Object> map=sysRoleService.getSysRoleList();
        System.out.println(map.get("list"));

        return map;

    }

    /**
     * 添加角色
     * @param roleName
     * @param remark
     * @return
     */
    @RequestMapping(value = "/addSysRole", method = RequestMethod.GET)
    public String addUser(String roleName, String remark) {
        if(UtilHelper.isEmpty(roleName)){
            return "登录账号不能为空！";
        }
        int count = sysRoleService.addSysRole(roleName, remark);
        if(count>0){
            System.out.println("id值："+count);
            return "修改成功！";
        }
        return "error";

    }

    @RequestMapping(value = "/updateSysRole", method = RequestMethod.GET)
    public String updateUserInfo(String roleId,String roleName,String remark) {
        if(UtilHelper.isEmpty(roleId)){
            return "角色编号不能为空，请联系系统管理员！";
        }
        int count = sysRoleService.updateSysRole(roleId,roleName,remark);
        if(count>0){
            return "修改成功！";
        }
        return "error";

    }
    @RequestMapping(value = "/deleteSysRole", method = RequestMethod.GET)
    public String deleteUser(String roleId) {
        if(UtilHelper.isEmpty(roleId)){
            return "角色编号不能为空，请联系系统管理员！";
        }
        int count = sysRoleService.deleteSysRole(roleId);
        if(count>0){
            return "修改成功！";
        }
        return "error";

    }

}
