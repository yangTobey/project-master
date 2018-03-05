package com.spring.boot.service.impl;

import com.spring.boot.service.ContractService;
import com.spring.boot.service.DepartmentService;
import com.spring.boot.service.web.ContractBusinessService;
import com.spring.boot.service.web.DepartmentBusinessService;
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
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractBusinessService contractBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public int addContractType(String contractTypeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String contractTypeCode="D"+ RandomUtils.nextInt(10)+RandomUtils.nextInt(10)+String.valueOf(System.currentTimeMillis()).substring(5,12)+ UtilHelper.chars.charAt((int)(Math.random() * 52));
        map.put("contractTypeName", contractTypeName);
        map.put("contractTypeCode", contractTypeCode);
        return contractBusinessService.addContractType(map);
    }

    @Override
    public int updateContractType(String contractTypeId, String contractTypeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractTypeId", contractTypeId);
        map.put("contractTypeName", contractTypeName);
        map.put("status", 2);
        return contractBusinessService.updateContractType(map);
    }

    @Override
    public int deleteContractType(String contractTypeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractTypeId", contractTypeId);
        return contractBusinessService.deleteContractType(map);
    }
}
