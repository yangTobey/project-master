package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysLaborCost;
import com.spring.boot.bean.master.SysLaborCostDetails;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysLaborCostService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysLaborCostBusinessService;
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
public class SysLaborCostServiceImpl implements SysLaborCostService {
    @Autowired
    private SysLaborCostBusinessService sysLaborCostBusinessService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> getSysLaborCostList() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("total", sysLaborCostBusinessService.getSysLaborCostList().size());
        map.put("list", sysLaborCostBusinessService.getSysLaborCostList());
        return map;
    }

    @Override
    public int addSysLaborCost(String companyId, String year, String month, String propertyLaborCost, String propertyHeadcountTotal, String propertyEmployeeTotal, String propertyEntryTotal, String propertyDemissionTotal,
                               String eBusinessLaborCost, String eBusinessHeadcountTotal, String eBusinessEmployeeTotal, String eBusinessEntryTotal, String eBusinessDemissionTotal,
                               String saleLaborCost, String saleHeadcountTotal, String saleEmployeeTotal, String saleEntryTotal, String saleDemissionTotal) {
        SysLaborCost sysLaborCost=new SysLaborCost();
        sysLaborCost.setCompanyId(Integer.valueOf(companyId));
        sysLaborCost.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        sysLaborCost.setYear(year);
        sysLaborCost.setMonth(month);
        int count=sysLaborCostBusinessService.addSysLaborCost(sysLaborCost);
        if (count>0) {
            long laborCostId=sysLaborCost.getLaborCostId();
            SysLaborCostDetails propertySysLaborCostDetails=new SysLaborCostDetails();
            propertySysLaborCostDetails.setDemissionTotal(Integer.valueOf(propertyDemissionTotal));
            propertySysLaborCostDetails.setDepartmentType(1);
            propertySysLaborCostDetails.setEmployeeTotal(Integer.valueOf(propertyEmployeeTotal));
            propertySysLaborCostDetails.setEntryTotal(Integer.valueOf(propertyEntryTotal));
            propertySysLaborCostDetails.setHeadcountTotal(Integer.valueOf(propertyHeadcountTotal));
            propertySysLaborCostDetails.setLaborCostId(laborCostId);
            propertySysLaborCostDetails.setLaborCostTotal(Integer.valueOf(propertyLaborCost));
            int propertyCount=sysLaborCostBusinessService.addSysLaborCostDetails(propertySysLaborCostDetails);
            if(propertyCount>0){
                SysLaborCostDetails eBusinessSysLaborCostDetails=new SysLaborCostDetails();
                eBusinessSysLaborCostDetails.setDemissionTotal(Integer.valueOf(eBusinessDemissionTotal));
                eBusinessSysLaborCostDetails.setDepartmentType(2);
                eBusinessSysLaborCostDetails.setEmployeeTotal(Integer.valueOf(eBusinessEmployeeTotal));
                eBusinessSysLaborCostDetails.setEntryTotal(Integer.valueOf(eBusinessEntryTotal));
                eBusinessSysLaborCostDetails.setHeadcountTotal(Integer.valueOf(eBusinessHeadcountTotal));
                eBusinessSysLaborCostDetails.setLaborCostId(laborCostId);
                eBusinessSysLaborCostDetails.setLaborCostTotal(Integer.valueOf(eBusinessLaborCost));
                int eBusinessCount=sysLaborCostBusinessService.addSysLaborCostDetails(eBusinessSysLaborCostDetails);
                if(eBusinessCount>0){
                    SysLaborCostDetails saleSysLaborCostDetails=new SysLaborCostDetails();
                    saleSysLaborCostDetails.setDemissionTotal(Integer.valueOf(saleDemissionTotal));
                    saleSysLaborCostDetails.setDepartmentType(2);
                    saleSysLaborCostDetails.setEmployeeTotal(Integer.valueOf(saleEmployeeTotal));
                    saleSysLaborCostDetails.setEntryTotal(Integer.valueOf(saleEntryTotal));
                    saleSysLaborCostDetails.setHeadcountTotal(Integer.valueOf(saleHeadcountTotal));
                    saleSysLaborCostDetails.setLaborCostId(laborCostId);
                    saleSysLaborCostDetails.setLaborCostTotal(Integer.valueOf(saleLaborCost));
                    sysLaborCostBusinessService.addSysLaborCostDetails(saleSysLaborCostDetails);
                }
            }
        }
        return 0;
    }

    @Override
    public int updateSysLaborCostInfo(String companyId, String companyName, String companyPhone, String companyAddress) {
        return 0;
    }

    @Override
    public int deleteSysLaborCostInfo(String companyId) {
        return 0;
    }
}
