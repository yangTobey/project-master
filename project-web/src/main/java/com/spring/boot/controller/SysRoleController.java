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
    public R getSysRoleList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset) {
        if (!UtilHelper.isNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字！");
        } else if (!UtilHelper.isNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字！");
        }
        Map<String, Object> map=sysRoleService.getSysRoleList(Integer.valueOf(limit), Integer.valueOf(offset));
        return R.ok(map);

    }
    @RequestMapping(value = "/getAllSysRole", method = RequestMethod.GET)
    public R getAllSysRole() {
        Map<String, Object> map=sysRoleService.getAllSysRole();
        return R.ok(map);

    }

    /**
     * 添加角色
     * @param roleName
     * @param remark
     * @return
     */
    @RequestMapping(value = "/addSysRole", method = RequestMethod.GET)
    public R addSysRole(@RequestParam(value = "roleName", required = false)String roleName,@RequestParam(value = "moduleIds", required = false) String moduleIds
            ,@RequestParam(value = "remark", required = false)String remark) {
        if(UtilHelper.isEmpty(roleName)){
            return R.error(400, "角色名称不能为空，请联系系统管理员！");
        }
        try {
            Map<String, Object> map= sysRoleService.addSysRole(roleName,moduleIds, remark);
            return R.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增角色信息失败：" + e.getMessage());
            return R.error(500, "新增角色信息失败，服务器异常，请联系系统管理员！");
        }
    }

    @RequestMapping(value = "/updateSysRole", method = RequestMethod.GET)
    public R updateSysRole(@RequestParam(value = "roleId", required = false)String roleId,@RequestParam(value = "roleName", required = false)String roleName
            ,@RequestParam(value = "remark", required = false)String remark,@RequestParam(value = "moduleIds", required = false) String moduleIds) {
        if(!UtilHelper.isNumer(roleId)){
            return R.error(400, "角色编号格式不正确，请联系系统管理员！");
        }
        try {
            Map<String, Object> map= sysRoleService.updateSysRole(Long.valueOf(roleId),roleName,remark,moduleIds);
            return R.ok(map);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("更新角色信息失败：" + e.getMessage());
            return R.error(500, "更新角色信息失败，服务器异常，请联系系统管理员！");
        }
    }
    @RequestMapping(value = "/deleteSysRole", method = RequestMethod.GET)
    public R  deleteSysRole(@RequestParam(value = "roleId", required = false)String roleId) {
        if(!UtilHelper.isNumer(roleId)){
            return R.error(400, "角色编号格式不正确，请联系系统管理员！");
        }
        Map<String, Object> map= sysRoleService.deleteSysRole(Long.valueOf(roleId));
        return R.ok(map);

    }

}
