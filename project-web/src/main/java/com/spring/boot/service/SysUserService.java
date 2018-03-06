package com.spring.boot.service;

import com.spring.boot.bean.master.SysUser;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysUserService {
    /**
     * 根据用户id查找用户信息
     *
     * @param userId
     * @return
     */
    SysUser findByUserId(String userId);

    /**
     * 根据用户账号查找用户信息你
     *
     * @param account
     * @return
     */
    SysUser findByUserAccount(String account);

    /***
     * 用户修改密码
     * @param userId
     * @param password
     * @param newPassword
     * @return
     */
    int updatePassword(long userId, String password, String newPassword);

    /**
     * 新增用户信息
     *
     * @param userAccount  登录账号
     * @param password     登录密码
     * @param companyId    所属公司id
     * @param roleId       角色id
     * @param departmentId 部门id
     * @return
     */
    int addUser(String userAccount, String password, String companyId, String roleId, String departmentId);

    /**
     * 更新用户信息
     *
     * @param userId       用户id
     * @param companyId    所属公司id
     * @param roleId       角色id
     * @param departmentId 部门id
     * @return
     */
    int updateUserInfo(String userId, String companyId, String roleId, String departmentId);

    /**
     * 删除用户信息（只更新用户账号状态，不作删除处理）
     *
     * @param userId 用户id
     * @return
     */
    int deleteUser(String userId);
}
