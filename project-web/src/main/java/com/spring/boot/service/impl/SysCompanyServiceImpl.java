package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysUserService;
import com.spring.boot.service.web.SysCompanyBusinessService;
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
public class SysCompanyServiceImpl implements SysCompanyService {
    @Autowired
    private SysCompanyBusinessService sysCompanyBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    Map<String, Object> resultMap = null;
    Map<String, Object> map = null;

    @Override
    public Map<String, Object> getSysCompanyList(int limit,int offset) {
        resultMap = new HashMap<String, Object>();
       map = new HashMap<String, Object>();
        map.put("limit",limit);
        map.put("offset",offset);
        resultMap.put("total", sysCompanyBusinessService.getSysCompanyListTotal(map));
        resultMap.put("list", sysCompanyBusinessService.getSysCompanyList(map));
        return resultMap;
    }

    @Override
    public int addSysCompany(String companyName, String companyPhone, String companyAddress) {
         map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String companyCode = "C" + RandomUtils.nextInt(10) + RandomUtils.nextInt(10) + String.valueOf(System.currentTimeMillis()).substring(5, 12) + UtilHelper.chars.charAt((int) (Math.random() * 52));
        map.put("companyName", companyName);
        map.put("companyPhone", companyPhone);
        map.put("parentId", 1);
        map.put("companyAddress", companyAddress);
        map.put("companyCode", companyCode);
        return sysCompanyBusinessService.addSysCompany(map);
    }

    @Override
    public int updateSysCompanyInfo(String companyId, String companyName, String companyPhone, String companyAddress) {
        map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("companyName", companyName);
        map.put("companyPhone", companyPhone);
        map.put("parentId", 1);
        map.put("companyAddress", companyAddress);
        return sysCompanyBusinessService.updateSysCompanyInfo(map);
    }

    @Override
    public int deleteSysCompanyById(String companyId) {
        map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        return sysCompanyBusinessService.deleteSysCompanyById(map);
    }

    @Override
    public Map<String, Object> findSysCompanyByCompanyId(long companyId) {
        map = new HashMap<String, Object>();
        resultMap = new HashMap<String, Object>();
        map.put("companyId", companyId);
        SysCompany sysCompany=sysCompanyBusinessService.findSysCompanyByCompanyId(map);
        resultMap.put("data", sysCompanyBusinessService.findSysCompanyByCompanyId(map));
        return resultMap;
    }
}
