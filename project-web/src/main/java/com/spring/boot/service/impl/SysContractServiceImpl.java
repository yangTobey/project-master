package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysContract;
import com.spring.boot.service.SysContractService;
import com.spring.boot.service.SysDepartmentService;
import com.spring.boot.service.web.SysContractBusinessService;
import com.spring.boot.service.web.SysDepartmentBusinessService;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public Map<String, Object> addSysContract(String contractName, String contractCode, String contractMoney, String contractStartTime, String contractEndTime, String contractTypeId, String firstPartyCompany, String secondPartyCompany, String personLiableName) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("contractName", contractName);
        map.put("contractCode", contractCode);
        map.put("contractMoney", contractMoney);
        map.put("contractStartTime", contractStartTime);
        map.put("contractEndTime", contractEndTime);
        map.put("contractTypeId", contractTypeId);
        map.put("firstPartyCompany", firstPartyCompany);
        map.put("secondPartyCompany", secondPartyCompany);
        map.put("personLiableName", personLiableName);
        map.put("createTime", Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        SysContract sysContract=sysContractBusinessService.findSysContractByContractCode(contractCode);
        if(sysContract!=null){
            resultMap.put("code",200);
            resultMap.put("msg","添加合同失败，系统已存在相同编号的合同，请重新添加或者联系系统管理员！");
        }else{
            try{
                int count= sysContractBusinessService.addSysContract(map);
                if(count>0){
                    resultMap.put("code",200);
                    resultMap.put("msg","添加合同成功！");
                }
            }catch (Exception e){
                e.printStackTrace();
                resultMap.put("code",500);
                resultMap.put("msg","服务器异常，请联系管理员！");
            }

        }
        return resultMap;
    }

    @Override
    public int updateSysContract(Long contractId, String contractName, String contractCode, String contractMoney, String contractStartTime, String contractEndTime, String contractTypeId, String firstPartyCompany, String secondPartyCompany, String personLiableName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractId", contractId);
        map.put("contractName", contractName);
        map.put("contractCode", contractCode);
        map.put("contractMoney", contractMoney);
        map.put("contractStartTime", contractStartTime);
        map.put("contractEndTime", contractEndTime);
        map.put("contractTypeId", contractTypeId);
        map.put("firstPartyCompany", firstPartyCompany);
        map.put("secondPartyCompany", secondPartyCompany);
        map.put("personLiableName", personLiableName);
        map.put("createTime", Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        return sysContractBusinessService.addSysContract(map);

    }

    @Override
    public int deleteSysContract(Long contractId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contractId", contractId);
        return sysContractBusinessService.deleteSysContract(map);
    }
}
