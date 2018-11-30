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

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String index() {
        System.out.println("hello world!");
        return "views/main/index";
    }

    /**
     * 获取用户基本信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sysUserInfo", method = RequestMethod.POST)
    public R sysUserInfo(@RequestParam(value = "id", required = false) String id) {

        Map<String, Object> map = sysUserService.sysUserInfo();
        return R.ok(map);
    }

    /**
     * 修改用户密码
     *
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public R updatePassword(@RequestParam(value = "password", required = false) String password, @RequestParam(value = "newPassword", required = false) String newPassword
            , @RequestParam(value = "comPassword", required = false) String comPassword) {

        if (UtilHelper.isEmpty(password)) {
            return R.error(400, "原密码不能为空！");
        } else if (UtilHelper.isEmpty(newPassword)) {
            return R.error(400, "新密码不能为空！");
        }else if (!UtilHelper.matcherStr(newPassword)) {
            return R.error(400, "新密码存在非法字符！");
        }else if (newPassword.length()<6||newPassword.length()>18) {
            return R.error(400, "新密码长度不符合要求！");
        }else if(!newPassword.equals(comPassword)){
            return R.error(400, "两次输入的新密码不相同，请重新输入！");
        }
        Map<String, Object> map = sysUserService.updatePassword(password, newPassword);
        return R.ok(map);
    }

    /**
     * 重置密码
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/resetSysUserPassword", method = RequestMethod.POST)
    public R resetSysUserPassword(@RequestParam(value = "userId", required = false) String userId) {
        if (!UtilHelper.isLongNumer(userId)) {
            return R.error(400, "用户id格式不正确，或者不符合常理！");
        }
        Map<String, Object> map = sysUserService.resetSysUserPassword(Long.valueOf(userId));
        return R.ok(map);
    }

    /**
     * 添加用户
     *
     * @param account    账号
     * @param password       密码
     * @param companyId      公司id
     * @param roleIds         多个角色id组合
     * @param departmentId   部门id
     * @param permsCompanyId 权限公司id
     * @return
     */
    @RequestMapping(value = "/addSysUser", method = RequestMethod.POST)
    public R addUser(@RequestParam(value = "account", required = false) String account, @RequestParam(value = "password", required = false) String password
            , @RequestParam(value = "companyId", required = false) String companyId, @RequestParam(value = "roleIds", required = false) String roleIds
            , @RequestParam(value = "departmentId", required = false) String departmentId, @RequestParam(value = "userName", required = false) String userName
            , @RequestParam(value = "permsCompanyId", required = false) String permsCompanyId) {
        if (UtilHelper.isEmpty(account)) {
            return R.error(400, "登录账号不能为空！");
        } else if (!UtilHelper.matcherStr(account)) {
            return R.error(400, "登录账号存在非法字符！");
        }else if (account.length()<6||account.length()>18) {
            return R.error(400, "登录账号长度不符合要求！");
        }else if (UtilHelper.isEmpty(password)) {
            return R.error(400, "登录密码不能为空！");
        }else if (!UtilHelper.matcherStr(password)) {
            return R.error(400, "登录密码存在非法字符！");
        }else if (password.length()<6||password.length()>18) {
            return R.error(400, "登录密码长度不符合要求！");
        } else if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        } else if (UtilHelper.isEmpty(roleIds)) {
            return R.error(400, "角色id格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isLongNumer(departmentId)) {
            return R.error(400, "部门id格式不正确，或者不符合常理！");
        }else if (UtilHelper.isEmpty(permsCompanyId)) {
            return R.error(400, "权限公司不能为空！！");
        }
        Map<String, Object> map = sysUserService.addUser(account, password, Long.valueOf(companyId), roleIds, Long.valueOf(departmentId), userName, permsCompanyId);
        return R.ok(map);

    }

    /**
     * 获取系统用户列表
     *
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping(value = "/sysUserList", method = RequestMethod.POST)
    public R sysUserList(@RequestParam(value = "limit", required = false) String limit, @RequestParam(value = "offset", required = false) String offset
            , @RequestParam(value = "account", required = false) String account, @RequestParam(value = "userName", required = false) String userName
            , @RequestParam(value = "companyId", required = false) String companyId) {
        if (!UtilHelper.isIntegerNumer(limit)) {
            return R.error(400, "分页控制，每页条数limit只能为数字，或者不符合常理！");
        } else if (!UtilHelper.isIntegerNumer(offset)) {
            return R.error(400, "分页控制，页码offset只能为数字，或者不符合常理！");
        }
        if(!UtilHelper.isEmpty(companyId)){
            if(!UtilHelper.isLongNumer(companyId)){
                return R.error(400, "公司id格式不正确，或者不符合常理！");
            }
        }else{//全部公司
            companyId="0";
        }
        Map<String, Object> map = sysUserService.sysUserList(Integer.valueOf(limit), Integer.valueOf(offset),account,userName,Long.valueOf(companyId));
        return R.ok(map);

    }

    /**
     * 更新用户信息
     *
     * @param userId
     * @param companyId
     * @param roleIds 多个角色id组合
     * @param departmentId
     * @return
     */
    @RequestMapping(value = "/updateSysUserInfo", method = RequestMethod.POST)
    public R updateUserInfo(@RequestParam(value = "userId", required = false) String userId, @RequestParam(value = "companyId", required = false) String companyId
            , @RequestParam(value = "roleIds", required = false) String roleIds, @RequestParam(value = "departmentId", required = false) String departmentId
            , @RequestParam(value = "userName", required = false) String userName, @RequestParam(value = "permsCompanyId", required = false) String permsCompanyId) {
        if (!UtilHelper.isLongNumer(userId)) {
            return R.error(400, "用户编号不能为空，或者不符合常理，请联系系统管理员！");
        } else if (!UtilHelper.isLongNumer(companyId)) {
            return R.error(400, "公司id格式不正确，或者不符合常理！");
        } else if (UtilHelper.isEmpty(roleIds)) {
            return R.error(400, "角色id格式不正确，或者不符合常理！");
        } else if (!UtilHelper.isLongNumer(departmentId)) {
            return R.error(400, "部门id格式不正确，或者不符合常理！");
        }else if (UtilHelper.isEmpty(permsCompanyId)) {
            return R.error(400, "权限公司不能为空！！");
        }
        Map<String, Object> map = sysUserService.updateUserInfo(Long.valueOf(userId), Long.valueOf(companyId), roleIds, Long.valueOf(departmentId), userName, permsCompanyId);
        return R.ok(map);

    }

    /**
     * 删除用户（只标记，不作数据删除）
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/deleteSysUser", method = RequestMethod.POST)
    public R deleteUser(@RequestParam(value = "userId", required = false) String userId) {
        if (!UtilHelper.isLongNumer(userId)) {
            return R.error(400, "用户编号不能为空，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map = sysUserService.deleteUser(userId, "delete");
        return R.ok(map);

    }

    /**
     * 停用、启用用户
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/closeSysUserAccount", method = RequestMethod.POST)
    public R closeSysUserAccount(@RequestParam(value = "userId", required = false) String userId,@RequestParam(value = "type", required = false) String type) {
        if (!UtilHelper.isLongNumer(userId)) {
            return R.error(400, "用户编号不能为空，或者不符合常理，请联系系统管理员！");
        }else if (UtilHelper.isEmpty(type)) {
            return R.error(400, "操作类型参数不能为空！");
        }
        Map<String, Object> map = sysUserService.deleteUser(userId, type);
        return R.ok(map);

    }

    /**
     * 获取用户权限下可以操作的公司
     *
     * @return
     */
    @RequestMapping(value = "/sysUserCompany", method = RequestMethod.POST)
    public R sysUserCompany() {
        Map<String, Object> map = sysUserService.sysUserCompany();
        return R.ok(map);
    }

    /**
     * 获取全部公司中，哪些已有权限和没有权限的具体信息
     *
     * @return
     */
    @RequestMapping(value = "/sysUserCompanyAuthority", method = RequestMethod.POST)
    public R sysUserCompanyAuthority(@RequestParam(value = "userId", required = false) String userId) {
        if (!UtilHelper.isLongNumer(userId)) {
            return R.error(400, "用户编号不能为空，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map = sysUserService.sysUserCompanyAuthority(Long.valueOf(userId));
        return R.ok(map);
    }

    /**
     * 获取登录用户的操作权限（系统的增删改查等功能）
     *
     * @return
     */
    @RequestMapping(value = "/getUserRole", method = RequestMethod.POST)
    public R getUserRole() {
        Map<String, Object> map = sysUserService.getUserRole();
        return R.ok(map);
    }

    /**
     *根据用户id查找用户信息
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/findSysUserInfoById", method = RequestMethod.POST)
    public R findSysUserInfoById(@RequestParam(value = "userId", required = false) String userId) {
        if (!UtilHelper.isLongNumer(userId)) {
            return R.error(400, "用户编号不能为空，或者不符合常理，请联系系统管理员！");
        }
        Map<String, Object> map = sysUserService.findSysUserInfoById(Long.valueOf(userId));
        return R.ok(map);
    }
}
