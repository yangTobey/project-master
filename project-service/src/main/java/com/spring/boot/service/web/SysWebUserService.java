package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysUser;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysWebUserService {
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
}
