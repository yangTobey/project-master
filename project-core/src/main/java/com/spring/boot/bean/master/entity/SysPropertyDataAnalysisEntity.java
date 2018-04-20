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
    /*//问题遗留数量（月）
    private Integer projectUnfinishedTotal;
    //已处理问题数量（月）
    private Integer projectFinishedTotal;
    //问题遗留数量（年）
    private Integer yearProjectUnfinishedTotal;
    //已处理问题数量（年）
    private Integer yearProjectFinishedTotal;
    //耗电量（月）
    private Double monthConsumptionElectricity;
    //耗水量（月）
    private Double monthConsumptionWater;
    //年度耗电量
    private Double yearConsumptionElectricity;
    //年度耗水量
    private Double yearConsumptionWater;
    //年系统遗留问题处理率
    private Double yearProjectUnfinishedScale;
    //耗电量同比
    private Double yoYConsumptionElectricityScale;
    //耗电量环比
    private Double mtOMtConsumptionElectricityScale;
    //耗水量同比
    private Double yoYConsumptionWaterScale;
    //耗电量环比
    private Double mtOMtConsumptionWaterScale;
    //耗电量环比
    private Map<Integer, Double> mtOMtCsElectricityScaleMap ;
    //耗水量环比
    private Map<Integer, Double> mtOMtCsWaterScaleMap ;
    //耗电量(月)
    private Map<Integer, Double> monthCsElectricityMap ;
    //耗水量(月)
    private Map<Integer, Double> monthCsWaterMap ;*/

    //年度统计信息
    private  Map<String, Object> sysProjectForYearMap;
    //月度统计信息
    private  Map<String, Object> sysProjectEnergyForMonthMap;

    /**********************************基础数据统计信息**************************/
    //房屋空置率(转化后保留两位小数)
    private Double forSaleHouseScale;
    //房屋装修率(转化后保留两位小数)
    private Double decorateHouseScale;
    //车位空置率(转化后保留两位小数)
    private Double forSaleParkingSpaceScale;
    //分公司总数
    private Integer subsidiaryCount;
    //城市总数
    private Integer cityNumber;
    //项目总数
    private Integer projectNumber;
    //建筑总面积
    private Double constructionArea;
    //收费总面积
    private Double chargeArea;
    //房屋总数量
    private Integer houseNumber;
    //已收房屋数量
    private Integer acceptHouseNumber;
    //待售房屋数量（空置）
    private Integer forSaleHouseNumber;
    //装修房屋数量
    private Integer decorateHouseNumber;
    //停车位总数量
    private Integer parkingSpace;
    //待售车位数量
    private Integer forSaleParkingSpace;
    //销售分配（销配）
    private Integer salesDistribution;

    /****************************************品质检查统计数据******************************/
    /*//月品质检查项（个/月）
    private Integer qualityCheck;
    //月品质检查合格项（个/月）
    private Integer qualityCheckPass;
    //月品质检查不合格项（个/月）
    private Integer qualityCheckFail;
    //月安全事件项
    private Integer securityEvent;
    //月品质检查未整改项（个/月）
    private Integer qualityCheckUnmodified;
    //检查合格率(月)
    private Double qualityCheckPassScale;
    //整改合格率（月）
    private Double modifiedPassScale;
    //合格率(月)
    private  Map<Integer, Double> checkPassScaleMap;
    //不合格率(月)
    private  Map<Integer, Double> modifiedPassScaleMap;*/
    //年度品质检查信息
    private  Map<String, Object> qualityManageYearMap;
    //月度品质检查信息
    private  Map<String, Object> qualityManageMonth;


    /*******************************人员成本统计信息************************/
    private  Map<String, Object> sysLaborCostDetailsMap;


    public Map<String, Object> getSysProjectForYearMap() {
        return sysProjectForYearMap;
    }

    public void setSysProjectForYearMap(Map<String, Object> sysProjectForYearMap) {
        this.sysProjectForYearMap = sysProjectForYearMap;
    }

    public Map<String, Object> getSysProjectEnergyForMonthMap() {
        return sysProjectEnergyForMonthMap;
    }

    public void setSysProjectEnergyForMonthMap(Map<String, Object> sysProjectEnergyForMonthMap) {
        this.sysProjectEnergyForMonthMap = sysProjectEnergyForMonthMap;
    }

    public Double getForSaleHouseScale() {
        return forSaleHouseScale;
    }

    public void setForSaleHouseScale(Double forSaleHouseScale) {
        this.forSaleHouseScale = forSaleHouseScale;
    }

    public Double getDecorateHouseScale() {
        return decorateHouseScale;
    }

    public void setDecorateHouseScale(Double decorateHouseScale) {
        this.decorateHouseScale = decorateHouseScale;
    }

    public Double getForSaleParkingSpaceScale() {
        return forSaleParkingSpaceScale;
    }

    public void setForSaleParkingSpaceScale(Double forSaleParkingSpaceScale) {
        this.forSaleParkingSpaceScale = forSaleParkingSpaceScale;
    }

    public Integer getSubsidiaryCount() {
        return subsidiaryCount;
    }

    public void setSubsidiaryCount(Integer subsidiaryCount) {
        this.subsidiaryCount = subsidiaryCount;
    }

    public Integer getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(Integer cityNumber) {
        this.cityNumber = cityNumber;
    }

    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
    }

    public Double getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(Double constructionArea) {
        this.constructionArea = constructionArea;
    }

    public Double getChargeArea() {
        return chargeArea;
    }

    public void setChargeArea(Double chargeArea) {
        this.chargeArea = chargeArea;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getAcceptHouseNumber() {
        return acceptHouseNumber;
    }

    public void setAcceptHouseNumber(Integer acceptHouseNumber) {
        this.acceptHouseNumber = acceptHouseNumber;
    }

    public Integer getForSaleHouseNumber() {
        return forSaleHouseNumber;
    }

    public void setForSaleHouseNumber(Integer forSaleHouseNumber) {
        this.forSaleHouseNumber = forSaleHouseNumber;
    }

    public Integer getDecorateHouseNumber() {
        return decorateHouseNumber;
    }

    public void setDecorateHouseNumber(Integer decorateHouseNumber) {
        this.decorateHouseNumber = decorateHouseNumber;
    }

    public Integer getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(Integer parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Integer getForSaleParkingSpace() {
        return forSaleParkingSpace;
    }

    public void setForSaleParkingSpace(Integer forSaleParkingSpace) {
        this.forSaleParkingSpace = forSaleParkingSpace;
    }

    public Integer getSalesDistribution() {
        return salesDistribution;
    }

    public void setSalesDistribution(Integer salesDistribution) {
        this.salesDistribution = salesDistribution;
    }

    public Map<String, Object> getQualityManageYearMap() {
        return qualityManageYearMap;
    }

    public void setQualityManageYearMap(Map<String, Object> qualityManageYearMap) {
        this.qualityManageYearMap = qualityManageYearMap;
    }

    public Map<String, Object> getQualityManageMonth() {
        return qualityManageMonth;
    }

    public void setQualityManageMonth(Map<String, Object> qualityManageMonth) {
        this.qualityManageMonth = qualityManageMonth;
    }

    public Map<String, Object> getSysLaborCostDetailsMap() {
        return sysLaborCostDetailsMap;
    }

    public void setSysLaborCostDetailsMap(Map<String, Object> sysLaborCostDetailsMap) {
        this.sysLaborCostDetailsMap = sysLaborCostDetailsMap;
    }
}


