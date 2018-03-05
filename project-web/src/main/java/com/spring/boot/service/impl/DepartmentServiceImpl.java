package com.spring.boot.service.impl;

import com.spring.boot.bean.master.User;
import com.spring.boot.service.DepartmentService;
import com.spring.boot.service.UserService;
import com.spring.boot.service.web.DepartmentBusinessService;
import com.spring.boot.service.web.UserBusinessService;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
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
    private DepartmentBusinessService departmentBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public int addDepartment(String departmentName, String companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String departmentCode="D"+ RandomUtils.nextInt(10)+RandomUtils.nextInt(10)+String.valueOf(System.currentTimeMillis()).substring(5,12)+ UtilHelper.chars.charAt((int)(Math.random() * 52));
        map.put("departmentName", departmentName);
        map.put("companyId", companyId);
        map.put("departmentCode", departmentCode);
        return departmentBusinessService.addDepartment(map);
    }

    @Override
    public int updateDepartmentInfo(String departmentId, String departmentName, String companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("departmentId", departmentId);
        map.put("departmentName", departmentName);
        map.put("companyId", companyId);
        map.put("status", 2);
        return departmentBusinessService.updateDepartmentInfo(map);
    }

    @Override
    public int deleteDepartment(String departmentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("departmentId", departmentId);
        return departmentBusinessService.deleteDepartment(map);
    }
}
