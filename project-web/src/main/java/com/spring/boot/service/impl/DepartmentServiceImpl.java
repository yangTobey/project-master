package com.spring.boot.service.impl;

import com.spring.boot.bean.master.User;
import com.spring.boot.service.DepartmentService;
import com.spring.boot.service.UserService;
import com.spring.boot.service.web.UserBusinessService;
import com.spring.boot.util.ShiroUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private UserBusinessService userBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public int addDepartment(String department, String companyId) {
        return 0;
    }
}
