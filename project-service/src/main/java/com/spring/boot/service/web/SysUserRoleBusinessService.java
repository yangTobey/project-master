package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysUserRole;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysUserRoleBusinessService {
    /**
     * 根据用户id查找用户权限列表信息
     *
     * @param userId
     * @return
     */
    List<SysUserRole> findRoleByUserId(long userId);
    /**
     * 新增用户角色信息
     *
     * @param sysUserRole
     * @return
     */
    int addUserRole(SysUserRole sysUserRole);
    /**
     * 根据公司id删除角色信息
     *
     * @param userId
     * @return
     */
    int deleteUserRoleByUserId(Long userId);
}
