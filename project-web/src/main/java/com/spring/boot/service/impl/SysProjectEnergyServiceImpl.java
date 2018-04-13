package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysProjectEnergy;
import com.spring.boot.service.SysProjectEnergyService;
import com.spring.boot.service.web.SysProjectEnergyBusinessService;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysProjectEnergyServiceImpl implements SysProjectEnergyService {
    private static final Logger logger = Logger.getLogger(SysProjectEnergyServiceImpl.class);
    @Autowired
    private SysProjectEnergyBusinessService sysProjectEnergyBusinessService;


    @Autowired
    private StringRedisTemplate redisTemplate;

    Map<String, Object> map = null;
    Map<String, Object> resultMap = null;

    @Override
    public Map<String, Object> addSysProjectEnergy(Long companyId, Integer year, Integer month, Integer projectUnfinishedTotal, Integer projectFinishedTotal, Integer monthConsumptionElectricity, Integer monthConsumptionWater, String fileInfo) {
        SysProjectEnergy sysProjectEnergy=new SysProjectEnergy();
        sysProjectEnergy.setCompanyId(companyId);
        sysProjectEnergy.setMonth(month);
        sysProjectEnergy.setYear(year);
        sysProjectEnergy.setCreateTime(Timestamp.valueOf(UtilHelper.getNowTimeStr()));
        return null;
    }

    @Override
    public Map<String, Object> updateSysProjectEnergy(Long details, Long companyId, Integer year, Integer month, Integer projectUnfinishedTotal, Integer projectFinishedTotal, Integer monthConsumptionElectricity, Integer monthConsumptionWater, String fileInfo) {
        return null;
    }

    @Override
    public Map<String, Object> deleteSysProjectEnergy(Long detailsId) {
        return null;
    }

    @Override
    public Map<String, Object> findSysProjectEnergyById(Long detailsId) {
        return null;
    }

    @Override
    public Map<String, Object> sysProjectEnergyList(Long companyId, Integer year) {
        return null;
    }

    @Override
    public Map<String, Object> sysProjectEnergyAnalysis(Long companyId) {
        return null;
    }
}
