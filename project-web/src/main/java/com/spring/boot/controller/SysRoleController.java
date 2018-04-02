package com.spring.boot.controller;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.service.SysRoleService;
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
    public R getSysRoleList() {
        Map<String, Object> map=sysRoleService.getSysRoleList();
        return R.ok(map);

    }

    /**
     * 添加角色
     * @param roleName
     * @param remark
     * @return
     */
    @RequestMapping(value = "/addSysRole", method = RequestMethod.GET)
    public R addSysRole(@RequestParam(value = "roleName", required = false)String roleName,@RequestParam(value = "moduleId", required = false) String moduleId
            ,@RequestParam(value = "remark", required = false)String remark) {
        if(UtilHelper.isEmpty(roleName)){
            return R.error(400, "角色名称不能为空，请联系系统管理员！");
        }
        Map<String, Object> map= sysRoleService.addSysRole(roleName,moduleId, remark);
        return R.ok(map);

    }

    @RequestMapping(value = "/updateSysRole", method = RequestMethod.GET)
    public R updateSysRole(String roleId,String roleName,String remark) {
        if(!UtilHelper.isNumer(roleId)){
            return R.error(400, "角色编号格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map= sysRoleService.updateSysRole(roleId,roleName,remark);
        return R.ok(map);

    }
    @RequestMapping(value = "/deleteSysRole", method = RequestMethod.GET)
    public R  deleteSysRole(String roleId) {
        if(!UtilHelper.isNumer(roleId)){
            return R.error(400, "角色编号格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map= sysRoleService.deleteSysRole(roleId);
        return R.ok(map);

    }

}
