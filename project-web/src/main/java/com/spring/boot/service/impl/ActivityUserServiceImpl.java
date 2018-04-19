package com.spring.boot.service.impl;

import com.spring.boot.bean.cluster.ActivityUser;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.dao.web.cluster.ActivityUserDao;
import com.spring.boot.service.ActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public ActivityUser findByUserId(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        // 缓存存在
        boolean hasKey = stringRedisTemplate.hasKey("admin");
        if(hasKey){
            String value=operations.get("admin");
            System.out.println("redis-admin:"+value);
        }else{
            operations.set("admin","hello world everybody!");
            System.out.println("set-admin-value:"+operations.get("admin"));
        }

        // 缓存存在
        boolean key = redisTemplate.hasKey("sysBasicData");
        if(key){
            System.out.println("值存在！！");
            SysBasicDataEntity sysBasicDataEntity=(SysBasicDataEntity)redisTemplate.opsForValue().get("sysBasicData");
            System.out.println("data:"+sysBasicDataEntity.getSubsidiaryCount()+","+sysBasicDataEntity.getDecorateHouseScale());
        }else{
            System.out.println("值不存在！！");
        }
        return activityUserDao.findByUserId(map);
    }

}
