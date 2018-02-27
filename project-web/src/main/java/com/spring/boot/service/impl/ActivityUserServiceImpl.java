package com.spring.boot.service.impl;

import com.spring.boot.bean.cluster.ActivityUser;
import com.spring.boot.bean.master.User;
import com.spring.boot.dao.web.cluster.ActivityUserDao;
import com.spring.boot.dao.web.master.UserDao;
import com.spring.boot.service.ActivityUserService;
import com.spring.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class ActivityUserServiceImpl implements ActivityUserService {
    @Autowired
    private ActivityUserDao activityUserDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public ActivityUser findByUserId(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
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
        return activityUserDao.findByUserId(map);
    }

}
