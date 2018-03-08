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
    public Map<String, Object> getSysDepartmentInfo() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", sysDepartmentBusinessService.getSysDepartmentInfo().size());
        map.put("list", sysDepartmentBusinessService.getSysDepartmentInfo());
        return map;
    }

    @Override
    public int addSysDepartment(String departmentName, String companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String departmentCode="D"+ RandomUtils.nextInt(10)+RandomUtils.nextInt(10)+String.valueOf(System.currentTimeMillis()).substring(5,12)+ UtilHelper.chars.charAt((int)(Math.random() * 52));
        map.put("departmentName", departmentName);
        map.put("companyId", companyId);
        map.put("departmentCode", departmentCode);
        return sysDepartmentBusinessService.addSysDepartment(map);
    }

    @Override
    public int updateSysDepartmentInfo(String departmentId, String departmentName, String companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("departmentId", departmentId);
        map.put("departmentName", departmentName);
        map.put("companyId", companyId);
        map.put("status", 2);
        return sysDepartmentBusinessService.updateSysDepartmentInfo(map);
    }

    @Override
    public int deleteSysDepartment(String departmentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("departmentId", departmentId);
        return sysDepartmentBusinessService.deleteSysDepartment(map);
    }
}
