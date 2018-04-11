package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysLaborCost;
import com.spring.boot.bean.master.SysLaborCostDetails;
import com.spring.boot.bean.master.entity.SysLaborCostDetailsEntity;
import com.spring.boot.service.SysCompanyService;
import com.spring.boot.service.SysLaborCostService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysLaborCostBusinessService;
import com.spring.boot.util.UtilHelper;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
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
    public Map<String, Object> getSysLaborCostInfo(String companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Calendar a = Calendar.getInstance();
        int month = a.get(Calendar.MONTH) + 1;
        int year = a.get(Calendar.YEAR);
        SysLaborCostDetailsEntity sysLaborCostDetails = null;
        List<SysLaborCostDetailsEntity> sysLaborCostDepartmentList = null;
        map.put("companyId", companyId);
        map.put("year", year);
        map.put("month", month);
        sysLaborCostDetails = sysLaborCostBusinessService.getSysLaborCostTotal(map);
        sysLaborCostDepartmentList = sysLaborCostBusinessService.getSysLaborCostDepartmentTotal(map);
        resultMap.put("sysLaborCostTotal", sysLaborCostDetails);
        //人工支出占比
        resultMap.put("sysLaborCostScale", 0);
        //人员缺编率
        resultMap.put("sysEmployeeScale", (sysLaborCostDetails.getEmployeeTotal() / sysLaborCostDetails.getHeadcountTotal()) * 100);
        //人员流失率
        resultMap.put("sysDemissionScale", (sysLaborCostDetails.getDemissionTotal() / sysLaborCostDetails.getEntryTotal()) * 100);
        //成本构成
        resultMap.put("sysLaborCostsDepartmentList", sysLaborCostDepartmentList);

        return resultMap;
    }

    @Override
    public Map<String, Object> getSysLaborCostList(String companyId, int year) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Calendar a = Calendar.getInstance();
        int month = a.get(Calendar.MONTH) + 1;
        SysLaborCostDetailsEntity sysLaborCostDetailsEntity = null;
        List<SysLaborCostDetailsEntity> list = null;

        map.put("companyId", companyId);
        map.put("year", year);
        list = sysLaborCostBusinessService.getSysLaborCostList(map);
        resultMap.put("sysLaborCostDetailsList", list);

        return resultMap;
    }

    @Override
    public int addSysLaborCost(String companyId, String year, String month, String propertyLaborCost, String propertyHeadcountTotal, String propertyEmployeeTotal, String propertyEntryTotal, String propertyDemissionTotal,
                               String eBusinessLaborCost, String eBusinessHeadcountTotal, String eBusinessEmployeeTotal, String eBusinessEntryTotal, String eBusinessDemissionTotal,
                               String saleLaborCost, String saleHeadcountTotal, String saleEmployeeTotal, String saleEntryTotal, String saleDemissionTotal) {
        SysLaborCost sysLaborCost = new SysLaborCost();
        sysLaborCost.setCompanyId(Long.valueOf(companyId));
        sysLaborCost.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        sysLaborCost.setYear(Integer.valueOf(year));
        sysLaborCost.setMonth(Integer.valueOf(month));
        int count = sysLaborCostBusinessService.addSysLaborCost(sysLaborCost);
        if (count > 0) {
            long laborCostId = sysLaborCost.getLaborCostId();
            SysLaborCostDetails propertySysLaborCostDetails = new SysLaborCostDetails();
            propertySysLaborCostDetails.setDemissionTotal(Integer.valueOf(propertyDemissionTotal));
            propertySysLaborCostDetails.setDepartmentType(1);
            propertySysLaborCostDetails.setEmployeeTotal(Integer.valueOf(propertyEmployeeTotal));
            propertySysLaborCostDetails.setEntryTotal(Integer.valueOf(propertyEntryTotal));
            propertySysLaborCostDetails.setHeadcountTotal(Integer.valueOf(propertyHeadcountTotal));
            propertySysLaborCostDetails.setLaborCostId(laborCostId);
            propertySysLaborCostDetails.setLaborCostTotal(Integer.valueOf(propertyLaborCost));
            int propertyCount = sysLaborCostBusinessService.addSysLaborCostDetails(propertySysLaborCostDetails);
            if (propertyCount > 0) {
                SysLaborCostDetails eBusinessSysLaborCostDetails = new SysLaborCostDetails();
                eBusinessSysLaborCostDetails.setDemissionTotal(Integer.valueOf(eBusinessDemissionTotal));
                eBusinessSysLaborCostDetails.setDepartmentType(2);
                eBusinessSysLaborCostDetails.setEmployeeTotal(Integer.valueOf(eBusinessEmployeeTotal));
                eBusinessSysLaborCostDetails.setEntryTotal(Integer.valueOf(eBusinessEntryTotal));
                eBusinessSysLaborCostDetails.setHeadcountTotal(Integer.valueOf(eBusinessHeadcountTotal));
                eBusinessSysLaborCostDetails.setLaborCostId(laborCostId);
                eBusinessSysLaborCostDetails.setLaborCostTotal(Integer.valueOf(eBusinessLaborCost));
                int eBusinessCount = sysLaborCostBusinessService.addSysLaborCostDetails(eBusinessSysLaborCostDetails);
                if (eBusinessCount > 0) {
                    SysLaborCostDetails saleSysLaborCostDetails = new SysLaborCostDetails();
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
    public int updateSysLaborCostInfo(String laborCostId, String companyId, String year, String month, String propertyLaborCost, String propertyHeadcountTotal, String propertyEmployeeTotal, String propertyEntryTotal, String propertyDemissionTotal,
                                      String eBusinessLaborCost, String eBusinessHeadcountTotal, String eBusinessEmployeeTotal, String eBusinessEntryTotal, String eBusinessDemissionTotal,
                                      String saleLaborCost, String saleHeadcountTotal, String saleEmployeeTotal, String saleEntryTotal, String saleDemissionTotal) {
        SysLaborCost sysLaborCost = sysLaborCostBusinessService.findSysLaborCostByLaborCostId(Long.valueOf(laborCostId));
        if (sysLaborCost != null) {
            sysLaborCost.setMonth(Integer.valueOf(month));
            sysLaborCost.setYear(Integer.valueOf(year));
            sysLaborCost.setCompanyId(Long.valueOf(companyId));
            sysLaborCostBusinessService.updateSysLaborCostInfo(sysLaborCost);

            long laborCostIdUpdate = Long.valueOf(laborCostId);
            SysLaborCostDetails propertySysLaborCostDetails = new SysLaborCostDetails();
            propertySysLaborCostDetails.setDemissionTotal(Integer.valueOf(propertyDemissionTotal));
            propertySysLaborCostDetails.setDepartmentType(1);
            propertySysLaborCostDetails.setEmployeeTotal(Integer.valueOf(propertyEmployeeTotal));
            propertySysLaborCostDetails.setEntryTotal(Integer.valueOf(propertyEntryTotal));
            propertySysLaborCostDetails.setHeadcountTotal(Integer.valueOf(propertyHeadcountTotal));
            propertySysLaborCostDetails.setLaborCostId(laborCostIdUpdate);
            propertySysLaborCostDetails.setLaborCostTotal(Integer.valueOf(propertyLaborCost));
            int propertyCount = sysLaborCostBusinessService.updateSysLaborCostDetailsInfo(propertySysLaborCostDetails);

            SysLaborCostDetails eBusinessSysLaborCostDetails = new SysLaborCostDetails();
            eBusinessSysLaborCostDetails.setDemissionTotal(Integer.valueOf(eBusinessDemissionTotal));
            eBusinessSysLaborCostDetails.setDepartmentType(2);
            eBusinessSysLaborCostDetails.setEmployeeTotal(Integer.valueOf(eBusinessEmployeeTotal));
            eBusinessSysLaborCostDetails.setEntryTotal(Integer.valueOf(eBusinessEntryTotal));
            eBusinessSysLaborCostDetails.setHeadcountTotal(Integer.valueOf(eBusinessHeadcountTotal));
            eBusinessSysLaborCostDetails.setLaborCostId(laborCostIdUpdate);
            eBusinessSysLaborCostDetails.setLaborCostTotal(Integer.valueOf(eBusinessLaborCost));
            int eBusinessCount = sysLaborCostBusinessService.updateSysLaborCostDetailsInfo(eBusinessSysLaborCostDetails);

            SysLaborCostDetails saleSysLaborCostDetails = new SysLaborCostDetails();
            saleSysLaborCostDetails.setDemissionTotal(Integer.valueOf(saleDemissionTotal));
            saleSysLaborCostDetails.setDepartmentType(3);
            saleSysLaborCostDetails.setEmployeeTotal(Integer.valueOf(saleEmployeeTotal));
            saleSysLaborCostDetails.setEntryTotal(Integer.valueOf(saleEntryTotal));
            saleSysLaborCostDetails.setHeadcountTotal(Integer.valueOf(saleHeadcountTotal));
            saleSysLaborCostDetails.setLaborCostId(laborCostIdUpdate);
            saleSysLaborCostDetails.setLaborCostTotal(Integer.valueOf(saleLaborCost));
            sysLaborCostBusinessService.updateSysLaborCostDetailsInfo(saleSysLaborCostDetails);

        }
        return 0;
    }

    @Override
    public int deleteSysLaborCostInfo(String laborCostId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("laborCostId", laborCostId);
        return sysLaborCostBusinessService.deleteSysLaborCostInfo(map);
    }
}
