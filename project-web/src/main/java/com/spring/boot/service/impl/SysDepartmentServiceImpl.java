package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.service.SysDepartmentService;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.SysDepartmentBusinessService;
import com.spring.boot.service.web.SysUserBusinessService;
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
public class SysDepartmentServiceImpl implements SysDepartmentService {
    @Autowired
    private SysDepartmentBusinessService sysDepartmentBusinessService;

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
        return sysDepartmentBusinessService.addDepartment(map);
    }

    @Override
    public int updateDepartmentInfo(String departmentId, String departmentName, String companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("departmentId", departmentId);
        map.put("departmentName", departmentName);
        map.put("companyId", companyId);
        map.put("status", 2);
        return sysDepartmentBusinessService.updateDepartmentInfo(map);
    }

    @Override
    public int deleteDepartment(String departmentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("departmentId", departmentId);
        return sysDepartmentBusinessService.deleteDepartment(map);
    }
}
