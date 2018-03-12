package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysLaborCost;
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
        return null;
    }

    @Override
    public int addSysLaborCost(String companyId, String year, String month, String propertyLaborCost, String propertyHeadcountTotal, String propertyEmployeeTotal, String propertyEntryTotal, String propertyDemissionTotal, String eBusinessLaborCost, String eBusinessHeadcountTotal, String eBusinessEmployeeTotal, String eBusinessEntryTotal, String eBusinessDemissionTotal, String saleLaborCost, String saleHeadcountTotal, String saleEmployeeTotal, String saleEntryTotal, String saleDemissionTotal) {
        SysLaborCost sysLaborCost=new SysLaborCost();
        sysLaborCost.setCompanyId(Integer.valueOf(companyId));
        sysLaborCost.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        sysLaborCost.setYear(year);
        sysLaborCost.setMonth(month);
        int count=sysLaborCostBusinessService.addSysLaborCost(sysLaborCost);
        if (count>0) {
            long laborCostId=sysLaborCost.getLaborCostId();
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
