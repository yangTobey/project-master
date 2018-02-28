package com.spring.boot.service.impl;

import com.spring.boot.bean.master.User;
import com.spring.boot.dao.web.master.UserDao;
import com.spring.boot.service.UserService;
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
public class UserServiceImpl implements UserService {
    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public User findByUserId(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        //springboot 集成redis操作缓存
        /*ValueOperations<String, String> operations = redisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey("admin");
        if(hasKey){
            String value=operations.get("admin");
            System.out.println("redis-admin:"+value);
        }else{
            operations.set("admin","hello world everybody!");
            System.out.println("set-admin-value:"+operations.get("admin"));
        }*/
        return userBusinessService.findByUserId(map);
    }

    @Override
    public User findByUserAccount(String account) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        return userBusinessService.findByUserAccount(map);
    }

    @Override
    public int updatePassword(long userId, String password, String newPassword) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("password", password);
        map.put("newPassword", newPassword);
        return userBusinessService.updatePassword(map);
    }
}
