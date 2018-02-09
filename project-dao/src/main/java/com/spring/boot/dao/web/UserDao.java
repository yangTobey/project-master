package com.spring.boot.dao.web;

import com.spring.boot.bean.User;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface UserDao {

    User findByUserId(Map<String, Object> map);

    /**
     * 根据用户账号查找用户信息你
     * @param map
     * @return
     */
    User findByUserAccount(Map<String, Object> map);
}
