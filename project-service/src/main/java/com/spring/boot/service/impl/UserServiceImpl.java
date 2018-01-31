package com.spring.boot.service.impl;

import com.spring.boot.bean.User;
import com.spring.boot.dao.UserDao;
import com.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User findByUserId(String userId) {
        return userDao.findByUserId(userId);
    }
}
