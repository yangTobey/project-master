package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.User;
import com.spring.boot.dao.web.master.UserDao;
import com.spring.boot.service.web.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User findByUserId(Map<String, Object> map) {
        return userDao.findByUserId(map);
    }

    @Override
    public User findByUserAccount(Map<String, Object> map) {
        return userDao.findByUserAccount(map);
    }

    @Override
    public int updatePassword(Map<String, Object> map) {
        return 0;
    }
}
