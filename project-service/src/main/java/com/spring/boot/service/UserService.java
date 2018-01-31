package com.spring.boot.service;

import com.spring.boot.bean.User;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface UserService {
    /**
     * 根据用户id查找用户信息
     *
     * @param userId
     * @return
     */
    User findByUserId(String userId);
}
