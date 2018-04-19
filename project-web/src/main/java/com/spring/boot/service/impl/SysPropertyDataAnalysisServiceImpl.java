package com.spring.boot.service.impl;

import com.spring.boot.dao.web.cluster.ActivityUserDao;
import com.spring.boot.service.SysPropertyDataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysPropertyDataAnalysisServiceImpl implements SysPropertyDataAnalysisService {
    @Autowired
    private ActivityUserDao activityUserDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String,Object> sysPropertyDataAnalysis() {
        Map<String, Object> map = new HashMap<String, Object>();

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
        return null;
    }

}
