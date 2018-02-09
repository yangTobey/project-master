package com.spring.boot.service.web;

import com.spring.boot.bean.UserRole;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface UserRoleService {
    /**
     * 根据用户id查找用户权限列表信息
     * @param userId
     * @return
     */
    List<UserRole> findRoleByUserId(long userId);
}
