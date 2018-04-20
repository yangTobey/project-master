package com.spring.boot.service.impl;

import com.spring.boot.bean.master.SysProject;
import com.spring.boot.bean.master.entity.*;
import com.spring.boot.dao.web.cluster.ActivityUserDao;
import com.spring.boot.service.SysPropertyDataAnalysisService;
import com.spring.boot.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysPropertyDataAnalysisServiceImpl implements SysPropertyDataAnalysisService {
    @Autowired
    private ActivityUserDao activityUserDao;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public Map<String,Object> sysPropertyDataAnalysis() {
        Map<String, Object> map = new HashMap<String, Object>();
        SysPropertyDataAnalysisEntity sysPropertyDataAnalysisEntity=new SysPropertyDataAnalysisEntity();
        /***********************************基础数据缓存******************************/
        // 缓存key是否存在
        boolean key = redisTemplate.hasKey("sysBasicData");
        if(key){
            SysBasicDataEntity sysBasicDataEntity=(SysBasicDataEntity)redisTemplate.opsForValue().get("sysBasicData");
            sysPropertyDataAnalysisEntity.setForSaleHouseScale(sysBasicDataEntity.getForSaleHouseScale());
            sysPropertyDataAnalysisEntity.setDecorateHouseScale(sysBasicDataEntity.getDecorateHouseScale());
            sysPropertyDataAnalysisEntity.setForSaleParkingSpaceScale(sysBasicDataEntity.getForSaleParkingSpaceScale());
            sysPropertyDataAnalysisEntity.setSubsidiaryCount(sysBasicDataEntity.getSubsidiaryCount());
            sysPropertyDataAnalysisEntity.setCityNumber(sysBasicDataEntity.getCityNumber());
            sysPropertyDataAnalysisEntity.setProjectNumber(sysBasicDataEntity.getProjectNumber());
            sysPropertyDataAnalysisEntity.setConstructionArea(sysBasicDataEntity.getConstructionArea());
            sysPropertyDataAnalysisEntity.setChargeArea(sysBasicDataEntity.getChargeArea());
            sysPropertyDataAnalysisEntity.setHouseNumber(sysBasicDataEntity.getHouseNumber());
            sysPropertyDataAnalysisEntity.setAcceptHouseNumber(sysBasicDataEntity.getAcceptHouseNumber());
            sysPropertyDataAnalysisEntity.setForSaleHouseNumber(sysBasicDataEntity.getForSaleHouseNumber());
            sysPropertyDataAnalysisEntity.setDecorateHouseNumber(sysBasicDataEntity.getDecorateHouseNumber());
            sysPropertyDataAnalysisEntity.setParkingSpace(sysBasicDataEntity.getParkingSpace());
            sysPropertyDataAnalysisEntity.setForSaleParkingSpaceScale(sysBasicDataEntity.getForSaleParkingSpaceScale());
            sysPropertyDataAnalysisEntity.setSalesDistribution(sysBasicDataEntity.getSalesDistribution());
        }
        /***********************************品质检查数据缓存******************************/
        // 缓存key是否存在
        //年度统计信息
        boolean qualityManageYear = redisTemplate.hasKey("qualityManageYear");
        //月度统计信息
        boolean qualityManageMonth = redisTemplate.hasKey("qualityManageMonth");
        if(qualityManageYear){
            SysQualityManageEntity sysQualityManageEntityForYear=(SysQualityManageEntity)redisTemplate.opsForValue().get("qualityManageYear");
            Map<String, Object> qualityManageYearMap=new HashMap<String, Object>();
            qualityManageYearMap.put("qualityManageYear",sysQualityManageEntityForYear);
            sysPropertyDataAnalysisEntity.setQualityManageYearMap(qualityManageYearMap);
        }
        if(qualityManageMonth){
            SysQualityManageEntity sysQualityManageEntity=(SysQualityManageEntity)redisTemplate.opsForValue().get("qualityManageMonth");
            Map<String, Object> qualityManageYearMap=new HashMap<String, Object>();
            qualityManageYearMap.put("qualityManageMonth",sysQualityManageEntity);
            sysPropertyDataAnalysisEntity.setQualityManageMonth(qualityManageYearMap);
        }
        /***********************************工程能耗管理数据缓存******************************/
        // 缓存key是否存在
        //年度统计信息
        boolean sysProjectForYear = redisTemplate.hasKey("sysProjectForYear");
        //月度统计信息
        boolean sysProjectEnergyForMonth = redisTemplate.hasKey("sysProjectEnergyForMonth");
        if(sysProjectForYear){
            SysProject sysProject=(SysProject)redisTemplate.opsForValue().get("sysProjectForYear");
            Map<String, Object> sysProjectForYearMap=new HashMap<String, Object>();
            sysProjectForYearMap.put("sysProjectForYear",sysProject);
            sysPropertyDataAnalysisEntity.setSysProjectForYearMap(sysProjectForYearMap);
        }
        if(sysProjectEnergyForMonth){
            SysProjectEnergyEntity sysProjectEnergyEntity=(SysProjectEnergyEntity)redisTemplate.opsForValue().get("qualityManageYear");
            Map<String, Object> sysProjectEnergyEntityMap=new HashMap<String, Object>();
            sysProjectEnergyEntityMap.put("sysProjectEnergyForMonth",sysProjectEnergyEntity);
            sysPropertyDataAnalysisEntity.setSysProjectEnergyForMonthMap(sysProjectEnergyEntityMap);
        }

        /***********************************人员成本管理数据缓存******************************/
        // 缓存key是否存在
        //年度统计信息
        boolean sysLaborCostDetails = redisTemplate.hasKey("sysLaborCostDetails");
        if(sysProjectForYear){
            SysLaborCostDetailsEntity SysLaborCostDetailsEntity=(SysLaborCostDetailsEntity)redisTemplate.opsForValue().get("sysLaborCostDetails");
            Map<String, Object> sysLaborCostDetailsMap=new HashMap<String, Object>();
            sysLaborCostDetailsMap.put("sysLaborCostDetails",SysLaborCostDetailsEntity);
            sysPropertyDataAnalysisEntity.setSysLaborCostDetailsMap(sysLaborCostDetailsMap);
        }
        return R.ok().putData(200,sysPropertyDataAnalysisEntity,"获取成功！！");
    }

}
