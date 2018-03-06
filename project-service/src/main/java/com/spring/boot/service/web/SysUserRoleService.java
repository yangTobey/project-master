package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysUserRole;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysUserRoleService {
    /**
     * 根据用户id查找用户权限列表信息
     *
     * @param userId
     * @return
     */
    List<SysUserRole> findRoleByUserId(long userId);
}
