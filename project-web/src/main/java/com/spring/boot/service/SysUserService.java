package com.spring.boot.service;

import com.spring.boot.bean.master.SysUser;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysUserService {
    /**
     * 查找系统用户列表
     *
     * @return
     */
    Map<String, Object> sysUserList(Integer limit, Integer offset);

    /**
     * 根据用户id查找用户信息
     *
     * @param userId
     * @return
     */
    SysUser findByUserId(String userId);

    /**
     * 获取登录用户的个人信息
     *
     * @return
     */
    Map<String, Object> sysUserInfo();

    /**
     * 根据用户账号查找用户信息你
     *
     * @param account
     * @return
     */
    SysUser findByUserAccount(String account);

    /***
     * 用户修改密码
     * @param password
     * @param newPassword
     * @return
     */
    Map<String, Object> updatePassword( String password, String newPassword);

    /***
     * 重置密码
     * @param userId
     * @return
     */
    Map<String, Object> resetSysUserPassword(Long userId);

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
    Map<String, Object> addUser(String userAccount, String password, Long companyId, Long roleId, Long departmentId, String userName, String permsCompanyId);

    /**
     * 更新用户信息
     *
     * @param userId       用户id
     * @param companyId    所属公司id
     * @param roleId       角色id
     * @param departmentId 部门id
     * @return
     */
    Map<String, Object> updateUserInfo(Long userId, Long companyId, Long roleId, Long departmentId, String userName, String permsCompanyId);

    /**
     * 删除用户信息（只更新用户账号状态，不作删除处理）
     *
     * @param userId 用户id
     * @return
     */
    Map<String, Object> deleteUser(String userId, String type);

    /**
     * 获取用户权限下可以操作的公司
     *
     * @return
     */
    Map<String, Object> sysUserCompany();

    /**
     * 获取全部公司中，哪些已有权限和没有权限的具体信息
     *
     * @return
     */
    Map<String, Object> sysUserCompanyAuthority(Long userId);
}
