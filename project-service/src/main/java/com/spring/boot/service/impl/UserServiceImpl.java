package com.spring.boot.service.impl;

import com.spring.boot.bean.User;
import com.spring.boot.dao.UserDao;
import com.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User findByUserId(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey("admin");
        if(hasKey){
            String value=operations.get("admin");
            System.out.println("redis-admin:"+value);
        }else{
            operations.set("admin","hello world everybody!");
            System.out.println("set-admin-value:"+operations.get("admin"));
        }
        return userDao.findByUserId(map);
    }
}
