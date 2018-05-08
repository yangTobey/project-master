package com.spring.boot.bean.cluster;

import java.util.List;
import java.util.Map;

/**
 * Created by xiaoyang on 2018/4/23.
 */
public class RepairOrder {
    private Long repairId;
    //年工单总数
    private Integer repairTotal;
    //月工单总数，在查询数据时使用
    private Integer monthRepairTotal;
    //工单状态
    private Integer repairStatus;
    //月份,在查询数据时使用
    private Integer month;
    //工单完成率
    private Double repairOrderFinishScale;

    //工单数量(月)
    private Map<Integer, Integer> monthRepairOrderMap ;
    //工单环比增长率(月)
    private Map<Integer, Double> monthToMonthRepairOrderMap ;

    private List<CityInfo> cityInfos;

    private List<RepairOrderLatest> repairOrderLatestList;

    Map<Long,List<String>> cityOrderMap;

    public Long getRepairId() {
        return repairId;
    }

    public void setRepairId(Long repairId) {
        this.repairId = repairId;
    }

    public Integer getRepairTotal() {
        return repairTotal;
    }

    public void setRepairTotal(Integer repairTotal) {
        this.repairTotal = repairTotal;
    }

    public Integer getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(Integer repairStatus) {
        this.repairStatus = repairStatus;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getMonthRepairTotal() {
        return monthRepairTotal;
    }

    public void setMonthRepairTotal(Integer monthRepairTotal) {
        this.monthRepairTotal = monthRepairTotal;
    }

    public Map<Integer, Integer> getMonthRepairOrderMap() {
        return monthRepairOrderMap;
    }

    public void setMonthRepairOrderMap(Map<Integer, Integer> monthRepairOrderMap) {
        this.monthRepairOrderMap = monthRepairOrderMap;
    }

    public Double getRepairOrderFinishScale() {
        return repairOrderFinishScale;
    }

    public void setRepairOrderFinishScale(Double repairOrderFinishScale) {
        this.repairOrderFinishScale = repairOrderFinishScale;
    }

    public Map<Integer, Double> getMonthToMonthRepairOrderMap() {
        return monthToMonthRepairOrderMap;
    }

    public void setMonthToMonthRepairOrderMap(Map<Integer, Double> monthToMonthRepairOrderMap) {
        this.monthToMonthRepairOrderMap = monthToMonthRepairOrderMap;
    }

    public List<CityInfo> getCityInfos() {
        return cityInfos;
    }

    public void setCityInfos(List<CityInfo> cityInfos) {
        this.cityInfos = cityInfos;
    }

    public List<RepairOrderLatest> getRepairOrderLatestList() {
        return repairOrderLatestList;
    }

    public void setRepairOrderLatestList(List<RepairOrderLatest> repairOrderLatestList) {
        this.repairOrderLatestList = repairOrderLatestList;
    }

    public Map<Long, List<String>> getCityOrderMap() {
        return cityOrderMap;
    }

    public void setCityOrderMap(Map<Long, List<String>> cityOrderMap) {
        this.cityOrderMap = cityOrderMap;
    }
}
