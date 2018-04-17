package com.spring.boot.bean.master.entity;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/13.
 */
public class SysProjectEnergyEntity {
    private String monthInfo;
    //耗电量环比
    private Map<Integer, Double> mtOMtCsElectricityScaleMap ;
    //耗水量环比
    private Map<Integer, Double> mtOMtCsWaterScaleMap ;
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

    public Map<Integer, Double> getMtOMtCsElectricityScaleMap() {
        return mtOMtCsElectricityScaleMap;
    }

    public void setMtOMtCsElectricityScaleMap(Map<Integer, Double> mtOMtCsElectricityScaleMap) {
        this.mtOMtCsElectricityScaleMap = mtOMtCsElectricityScaleMap;
    }

    public Map<Integer, Double> getMtOMtCsWaterScaleMap() {
        return mtOMtCsWaterScaleMap;
    }

    public void setMtOMtCsWaterScaleMap(Map<Integer, Double> mtOMtCsWaterScaleMap) {
        this.mtOMtCsWaterScaleMap = mtOMtCsWaterScaleMap;
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
