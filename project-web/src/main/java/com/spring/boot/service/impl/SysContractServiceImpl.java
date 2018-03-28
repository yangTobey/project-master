package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysContract;
import com.spring.boot.bean.master.SysContractType;
import com.spring.boot.controller.SysCompanyController;
import com.spring.boot.service.SysContractService;
import com.spring.boot.service.SysDepartmentService;
import com.spring.boot.service.web.SysContractBusinessService;
import com.spring.boot.service.web.SysDepartmentBusinessService;
import com.spring.boot.util.R;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysContractServiceImpl implements SysContractService {
    private static final Logger logger = Logger.getLogger(SysContractServiceImpl.class);
    @Autowired
    private SysContractBusinessService sysContractBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> sysContractTypeList(Integer limit, Integer offset) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("limit", limit);
        map.put("offset", offset);
        try {
            int count = sysContractBusinessService.sysContractTypeDataTotal(map);
            List<SysContractType> list = sysContractBusinessService.sysContractTypeList(map);

            resultMap.put("total", count);
            resultMap.put("list", list);
            return R.ok().putData(200,resultMap,"获取成功！");
        }catch (Exception e){
            e.printStackTrace();
            logger.info("获取合同分类列表失败："+e.getMessage());
            return R.error(500,"获取合同分类列表失败,服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> addSysContractType(String contractTypeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //公司编码（服务识别号）
        String contractTypeCode = "D" + RandomUtils.nextInt(10) + RandomUtils.nextInt(10) + String.valueOf(System.currentTimeMillis()).substring(5, 12) + UtilHelper.chars.charAt((int) (Math.random() * 52));
        map.put("contractTypeName", contractTypeName);
        map.put("contractTypeCode", contractTypeCode);
        try {
            int count=sysContractBusinessService.addSysContractType(map);
            if(count>0){
                return R.ok(200,"新增成功！");
            }else{
                return R.error(500,"新增失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("新增合同分类失败："+e.getMessage());
            return R.error(500,"新增合同分类失败,服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> updateSysContractType(String contractTypeId, String contractTypeName) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("contractTypeId", contractTypeId);
        map.put("contractTypeName", contractTypeName);
        map.put("status", 2);
        int count=sysContractBusinessService.updateSysContractType(map);
        if(count>0){
            return R.ok(200,"更新成功！");
        }else{
            return R.error(500,"更新失败！");
        }
    }

    @Override
    public Map<String, Object> deleteSysContractType(String contractTypeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("contractTypeId", contractTypeId);
        int count=sysContractBusinessService.deleteSysContractType(map);
        if(count>0){
            return R.ok(200,"删除成功！");
        }else{
            return R.error(500,"删除失败！");
        }
    }

    @Override
    public Map<String, Object> sysContractDataList(String contractName, String contractCode,Integer statusCode,String contractStartTime, String contractEndTime, String contractTypeId, String firstPartyCompany
            , String secondPartyCompany, Integer limit, Integer offset, Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("contractName", contractName);
        map.put("contractCode", contractCode);
        map.put("companyId", companyId);
        map.put("statusCode", statusCode);
        map.put("contractStartTime", contractStartTime);
        map.put("contractEndTime", contractEndTime);
        map.put("contractTypeId", contractTypeId);
        map.put("firstPartyCompany", firstPartyCompany);
        map.put("secondPartyCompany", secondPartyCompany);
        map.put("limit", limit);
        map.put("offset", offset);

        int count = sysContractBusinessService.sysContractDataTotal(map);
        List<SysContract> list = sysContractBusinessService.sysContractDataList(map);

        map = new HashMap<String, Object>();

        map.put("total", count);
        map.put("list", list);
        return R.ok().putData(200,map,"获取成功！");
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
        SysContract sysContract = sysContractBusinessService.findSysContractByContractCode(contractCode);
        if (sysContract != null) {
            return R.error(500,"添加合同失败，系统已存在相同编号的合同，请重新添加或者联系系统管理员！！");
        } else {
            try {
                int count = sysContractBusinessService.addSysContract(map);
                if (count > 0) {
                    return R.ok(200,"添加合同成功！！");
                }else {
                    return R.error(500,"添加合同失败，请联系系统管理员！！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("添加合同失败："+e.getMessage());
                return R.error(500,"服务器异常，请联系系统管理员！！");
            }
        }
    }

    @Override
    public Map<String, Object> updateSysContract(Long contractId, String contractName, String contractCode, String contractMoney, String contractStartTime, String contractEndTime, String contractTypeId, String firstPartyCompany, String secondPartyCompany, String personLiableName) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
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

        try {
            SysContract sysContract = sysContractBusinessService.findSysContractByContractCode(contractCode);
            if (sysContract != null) {
                return R.error(500,"更新合同失败，系统已存在相同编号的合同，请重新添加或者联系系统管理员！！");
            }else{
                int count = sysContractBusinessService.updateSysContract(map);
                if (count > 0) {
                    return R.ok(200,"更新合同信息成功！！！");
                }else {
                    return R.error(500,"更新合同失败，请联系系统管理员！！");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新合同失败："+e.getMessage());
            return R.error(500,"服务器异常，请联系系统管理员！！");
        }
    }

    @Override
    public Map<String, Object> deleteSysContract(Long contractId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        map.put("contractId", contractId);
        try {
            int count=sysContractBusinessService.deleteSysContract(map);
            if(count>0){
                return R.ok(200,"删除合同成功！！");
            }else {
                return R.error(500,"删除合同失败，请联系系统管理员！！");
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.info("删除合同失败："+e.getMessage());
            return R.error(500,"服务器异常，请联系管理员！");
        }
    }

    @Override
    public Map<String, Object> getSysContractExpireDataTotal(Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        /*组装请求参数数据*/
        map.put("contractCode", 3);
        map.put("companyId", companyId);
        /*组装结果数据*/
        resultMap.put("total", sysContractBusinessService.sysContractDataTotal(map));
        resultMap.put("code", 200);
        resultMap.put("msg", "获取即将过期合同信息总条数成功！！");
        return resultMap;
    }

    @Override
    public Map<String, Object> sysContractAnalysisData(Long companyId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            List<SysContract> list = sysContractBusinessService.sysContractAnalysisData(companyId);
            int contractWorking = 0;
            int contractNumber = 0;
            int contractexpired = 0;
            for (SysContract sysContract : list) {
                if (sysContract.getStatusCode() == 2) {
                    contractWorking += sysContract.getTotal();
                } else if (sysContract.getStatusCode() == 4) {
                    contractexpired += sysContract.getTotal();
                }
                contractNumber += sysContract.getTotal();
            }
            Map<String, Object> map = new HashMap<String, Object>();
            /*组装结果数据*/
            map.put("contractWorking", contractWorking);
            map.put("contractNumber", contractNumber);
            map.put("contractexpired", contractexpired);
            return R.ok().putData(200,map,"获取数据成功！！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("获取合同档案统计数据失败："+e.getMessage());
            return R.error(500,"服务器异常！！");
        }
    }
}
