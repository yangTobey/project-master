package com.spring.boot.bean.master.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/13.
 */
public class SysProjectEnergyEntity implements Serializable {
    private String monthInfo;
    //耗电量环比
    private Map<Integer, Double> mtoMtCsElectricityScaleMap ;
    //耗水量环比
    private Map<Integer, Double> mtoMtCsWaterScaleMap ;
    //耗电量(月)
    private Map<Integer, Double> monthCsElectricityMap ;
    //耗水量(月)
    private Map<Integer, Double> monthCsWaterMap ;

    public String getMonthInfo() {
        return monthInfo;
    }

    public void setMonthInfo(String monthInfo) {
        this.monthInfo = monthInfo;
    }

    public Map<Integer, Double> getMtoMtCsElectricityScaleMap() {
        return mtoMtCsElectricityScaleMap;
    }

    public void setMtoMtCsElectricityScaleMap(Map<Integer, Double> mtoMtCsElectricityScaleMap) {
        this.mtoMtCsElectricityScaleMap = mtoMtCsElectricityScaleMap;
    }

    public Map<Integer, Double> getMtoMtCsWaterScaleMap() {
        return mtoMtCsWaterScaleMap;
    }

    public void setMtoMtCsWaterScaleMap(Map<Integer, Double> mtoMtCsWaterScaleMap) {
        this.mtoMtCsWaterScaleMap = mtoMtCsWaterScaleMap;
    }

    public Map<Integer, Double> getMonthCsElectricityMap() {
        return monthCsElectricityMap;
    }

    public void setMonthCsElectricityMap(Map<Integer, Double> monthCsElectricityMap) {
        this.monthCsElectricityMap = monthCsElectricityMap;
    }

    public Map<Integer, Double> getMonthCsWaterMap() {
        return monthCsWaterMap;
    }

    public void setMonthCsWaterMap(Map<Integer, Double> monthCsWaterMap) {
        this.monthCsWaterMap = monthCsWaterMap;
    }
}
