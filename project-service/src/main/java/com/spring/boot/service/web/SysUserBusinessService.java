package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.bean.master.SysUserCompany;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysUserBusinessService {
    /**
     * 查找系统用户列表
     */
    List<SysUser> sysUserList(Map<String, Object> map);
    /**
     * 查找系统用户列表总数
     */
    int sysUserTotal();
    /**
     * 根据用户id查找用户信息
     *
     * @param map
     * @return
     */
    SysUser findByUserId(Map<String, Object> map);
    /**
     * 获取登录用户的个人信息
     *
     * @return
     */
    SysUser sysUserInfo(Long userId);

    /**
     * 根据用户账号查找用户信息你
     *
     * @param map
     * @return
     */
    SysUser findByUserAccount(Map<String, Object> map);

    /**
     * 更新用户密码
     *
     * @param map
     * @return
     */
    int updatePassword(Map<String, Object> map);
    /***
     * 重置密码
     * @param map
     * @return
     */
    int resetSysUserPassword(Map<String, Object> map);

    /**
     * 新增用户信息
     *
     * @param sysUser)
     * @return
     */
    int addUser(SysUser sysUser);

    /**
     * 更新用户信息
     *
     * @param map
     * @return
     */
    int updateUserInfo(Map<String, Object> map);

    /**
     * 删除用户信息（只更新用户账号状态，不作删除处理）
     *
     * @param map
     * @return
     */
    int deleteUser(Map<String, Object> map);
    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryUserAllMenuId(Long userId);

    /**
     * 查询用户的所有菜单和功能按钮ID
     */
    List<Long> queryUserAllModuleId(Long userId);


}
