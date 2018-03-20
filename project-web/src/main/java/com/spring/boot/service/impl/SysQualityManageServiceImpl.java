package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysQualityManageService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysQualityManageBusinessService;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysQualityManageServiceImpl implements SysQualityManageService {
    @Autowired
    private SysQualityManageBusinessService sysQualityManageBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;
    Map<String, Object> resultMap = null;
    Map<String, Object> map = null;

    @Override
    public Map<String, Object> getSysQualityManageList(int limit,int offset) {
        resultMap = new HashMap<String, Object>();
       map = new HashMap<String, Object>();
        map.put("limit",limit);
        map.put("offset",offset);
        resultMap.put("total", sysQualityManageBusinessService.getSysQualityManageListTotal(map));
        resultMap.put("list", sysQualityManageBusinessService.getSysQualityManageList(map));
        return resultMap;
    }

    @Override
    public int addSysQualityManage(long companyId, int year, int month,int qualityCheck,int qualityCheckPass,int qualityCheckFail,int securityEvent,int qualityCheckUnmodified) {
         map = new HashMap<String, Object>();
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("month", month);
        map.put("qualityCheck", qualityCheck);
        map.put("qualityCheckPass", qualityCheckPass);
        map.put("qualityCheckFail", qualityCheckFail);
        map.put("securityEvent", securityEvent);
        map.put("qualityCheckUnmodified", qualityCheckUnmodified);
        map.put("createTime", UtilHelper.getNowTimeStr());
        return sysQualityManageBusinessService.addSysQualityManage(map);
    }

    @Override
    public int updateSysQualityManage(long qualityId,long companyId, int year, int month,int qualityCheck,int qualityCheckPass,int qualityCheckFail,int securityEvent,int qualityCheckUnmodified) {
        map = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("month", month);
        map.put("qualityCheck", qualityCheck);
        map.put("qualityCheckPass", qualityCheckPass);
        map.put("qualityCheckFail", qualityCheckFail);
        map.put("securityEvent", securityEvent);
        map.put("qualityCheckUnmodified", qualityCheckUnmodified);
        return sysQualityManageBusinessService.updateSysQualityManage(map);
    }

    @Override
    public int deleteSysQualityManageById(long qualityId) {
        map = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        return sysQualityManageBusinessService.deleteSysQualityManageById(map);
    }

    @Override
    public Map<String, Object> findSysQualityManageById(long qualityId) {
        map = new HashMap<String, Object>();
        resultMap = new HashMap<String, Object>();
        map.put("qualityId", qualityId);
        SysQualityManage sysCompany=sysQualityManageBusinessService.findSysQualityManageById(map);
        resultMap.put("data", sysQualityManageBusinessService.findSysQualityManageById(map));
        return resultMap;
    }
}
