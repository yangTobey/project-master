package com.spring.boot.dao;

import com.spring.boot.bean.User;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface UserDao {

    User findByUserId(String userId);
}
