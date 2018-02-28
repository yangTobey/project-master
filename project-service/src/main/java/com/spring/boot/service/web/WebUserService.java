package com.spring.boot.service.web;

import com.spring.boot.bean.master.User;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface WebUserService {
    /**
     * 根据用户id查找用户信息
     *
     * @param userId
     * @return
     */
    User findByUserId(String userId);

    /**
     * 根据用户账号查找用户信息你
     *
     * @param account
     * @return
     */
    User findByUserAccount(String account);
}
