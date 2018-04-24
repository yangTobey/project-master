package com.spring.boot.bean.master.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * 物业大屏数据展示实体类
 * Created by xiaoyang on 2018/4/20.
 */
public class SysPropertyDataAnalysisEntity implements Serializable {

    /********************************工程能耗统计信息************************/
    //工程能耗统计信息
    private  Map<String, Object> sysProjectEnergyMap;

    /**********************************基础数据统计信息**************************/
    private  SysBasicDataEntity sysBasicData;
    private  Map<String, Object> sysBasicDataMap;

    /****************************************品质检查统计数据******************************/
    //品质检查信息
    private  Map<String, Object> qualityManageMap;

    /*******************************人员成本统计信息************************/
    private  Map<String, Object> sysLaborCostDetailsMap;
    private  SysLaborCostDetailsEntity sysLaborCostDetails;

    public Map<String, Object> getSysProjectEnergyMap() {
        return sysProjectEnergyMap;
    }

    public void setSysProjectEnergyMap(Map<String, Object> sysProjectEnergyMap) {
        this.sysProjectEnergyMap = sysProjectEnergyMap;
    }

    public Map<String, Object> getSysBasicDataMap() {
        return sysBasicDataMap;
    }

    public void setSysBasicDataMap(Map<String, Object> sysBasicDataMap) {
        this.sysBasicDataMap = sysBasicDataMap;
    }

    public Map<String, Object> getQualityManageMap() {
        return qualityManageMap;
    }

    public void setQualityManageMap(Map<String, Object> qualityManageMap) {
        this.qualityManageMap = qualityManageMap;
    }

    public Map<String, Object> getSysLaborCostDetailsMap() {
        return sysLaborCostDetailsMap;
    }

    public void setSysLaborCostDetailsMap(Map<String, Object> sysLaborCostDetailsMap) {
        this.sysLaborCostDetailsMap = sysLaborCostDetailsMap;
    }

    public SysBasicDataEntity getSysBasicData() {
        return sysBasicData;
    }

    public void setSysBasicData(SysBasicDataEntity sysBasicData) {
        this.sysBasicData = sysBasicData;
    }

    public SysLaborCostDetailsEntity getSysLaborCostDetails() {
        return sysLaborCostDetails;
    }

    public void setSysLaborCostDetails(SysLaborCostDetailsEntity sysLaborCostDetails) {
        this.sysLaborCostDetails = sysLaborCostDetails;
    }
}


