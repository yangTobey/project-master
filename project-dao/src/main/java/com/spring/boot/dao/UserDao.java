package com.spring.boot.dao;

import com.spring.boot.bean.User;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface UserDao {

    User findByUserId(Map<String, Object> map);
}
