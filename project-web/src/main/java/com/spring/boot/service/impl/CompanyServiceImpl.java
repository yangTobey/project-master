package com.spring.boot.service.impl;

import com.spring.boot.bean.master.User;
import com.spring.boot.service.CompanyService;
import com.spring.boot.service.UserService;
import com.spring.boot.service.web.CompanyBusinessService;
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
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyBusinessService companyBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public int addCompany(String companyName, String companyPhone, String companyAddress) {
        Map<String, Object> map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String companyCode="C"+ RandomUtils.nextInt(10)+RandomUtils.nextInt(10)+String.valueOf(System.currentTimeMillis()).substring(5,12)+ UtilHelper.chars.charAt((int)(Math.random() * 52));
        map.put("companyName", companyName);
        map.put("companyPhone", companyPhone);
        map.put("parentId", 1);
        map.put("companyAddress", companyAddress);
        map.put("companyCode", companyCode);
        return companyBusinessService.addCompany(map);
    }

    @Override
    public int updateCompanyInfo(String companyId, String companyName, String companyPhone, String companyAddress) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("companyName", companyName);
        map.put("companyPhone", companyPhone);
        map.put("parentId", 1);
        map.put("companyAddress", companyAddress);
        return companyBusinessService.updateCompanyInfo(map);
    }

    @Override
    public int deleteCompanyInfo(String companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        return companyBusinessService.updateCompanyInfo(map);
    }
}
