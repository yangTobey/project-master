package com.spring.boot.service.impl;

import com.spring.boot.service.SysContractService;
import com.spring.boot.service.SysDepartmentService;
import com.spring.boot.service.web.SysContractBusinessService;
import com.spring.boot.service.web.SysDepartmentBusinessService;
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
public class SysContractServiceImpl implements SysContractService {
    @Autowired
    private SysContractBusinessService sysContractBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public int addContractType(String contractTypeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String contractTypeCode="D"+ RandomUtils.nextInt(10)+RandomUtils.nextInt(10)+String.valueOf(System.currentTimeMillis()).substring(5,12)+ UtilHelper.chars.charAt((int)(Math.random() * 52));
        map.put("contractTypeName", contractTypeName);
        map.put("contractTypeCode", contractTypeCode);
        return sysContractBusinessService.addContractType(map);
    }

    @Override
    public int updateContractType(String contractTypeId, String contractTypeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractTypeId", contractTypeId);
        map.put("contractTypeName", contractTypeName);
        map.put("status", 2);
        return sysContractBusinessService.updateContractType(map);
    }

    @Override
    public int deleteContractType(String contractTypeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractTypeId", contractTypeId);
        return sysContractBusinessService.deleteContractType(map);
    }

    @Override
    public int addSysContract(String contractName, String contractCode, String contractMoney, String contractStartTime, String contractEndTime, String contractTypeId, String firstPartyCompany, String secondPartyCompany, String personLiableName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractName", contractName);
        map.put("contractCode", contractCode);
        map.put("contractMoney", contractMoney);
        map.put("contractStartTime", contractStartTime);
        map.put("contractEndTime", contractEndTime);
        map.put("contractTypeId", contractTypeId);
        map.put("firstPartyCompany", firstPartyCompany);
        map.put("secondPartyCompany", secondPartyCompany);
        map.put("personLiableName", personLiableName);
        return sysContractBusinessService.addSysContract(map);
    }
}
