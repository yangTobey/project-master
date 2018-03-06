package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.dao.web.master.SysUserDao;
import com.spring.boot.service.web.SysWebUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysWebUserServiceImpl implements SysWebUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public SysUser findByUserId(String userId) {
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
        return sysUserDao.findByUserId(map);
    }

    @Override
    public SysUser findByUserAccount(String account) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("account", account);
        return sysUserDao.findByUserAccount(map);
    }
}
