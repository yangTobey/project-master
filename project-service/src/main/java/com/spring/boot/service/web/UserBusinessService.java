package com.spring.boot.service.web;

import com.spring.boot.bean.master.User;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface UserBusinessService {
    /**
     * 根据用户id查找用户信息
     *
     * @param userId
     * @return
     */
    User findByUserId(Map<String, Object> map);

    /**
     * 根据用户账号查找用户信息你
     *
     * @param account
     * @return
     */
    User findByUserAccount(Map<String, Object> map);

    int updatePassword(Map<String, Object> map);
}
