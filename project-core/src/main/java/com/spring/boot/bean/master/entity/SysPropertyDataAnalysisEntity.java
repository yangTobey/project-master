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



    //问题遗留数量（月）
    private Integer projectUnfinishedTotal;
    //已处理问题数量（月）
    private Integer projectFinishedTotal;
    //月系统遗留问题处理率
    private Double monthProjectUnfinishedScale;
    //年度耗电量
    private Double yearConsumptionElectricity;
    //年度耗水量
    private Double yearConsumptionWater;
    //耗电量（月）
    private Double monthConsumptionElectricity;
    //耗水量（月）
    private Double monthConsumptionWater;
    //耗电量同比
    private Double yoYElectricityScale;
    //耗电量环比
    private Double mtoMtElectricityScale;
    //耗水量同比
    private Double yoYWaterScale;
    //耗水量环比
    private Double mtoMtWaterScale;
    //耗电量环比
    private Map<Integer, Double> mtoMtCsElectricityScaleMap ;
    //耗水量环比
    private Map<Integer, Double> mtoMtCsWaterScaleMap ;
    //耗电量(月)
    private Map<Integer, Double> monthCsElectricityMap ;
    //耗水量(月)
    private Map<Integer, Double> monthCsWaterMap ;
    /*//年度统计信息
    private  Map<String, Object> sysProjectForYearMap;
    //月度统计信息
    private  Map<String, Object> sysProjectEnergyForMonthMap;*/

    /**********************************基础数据统计信息**************************/
    private  SysBasicDataEntity sysBasicData;

    //建筑总面积
    private Double constructionArea;
    //收费总面积
    private Double chargeArea;
    //分公司总数
    private Integer subsidiaryCount;
    //城市总数
    private Integer cityNumber;
    //项目总数
    private Integer projectNumber;
    //销售分配（销配）
    private Integer salesDistribution;
    //房屋总数量
    private Integer houseNumber;
    //已收房屋数量
    private Integer acceptHouseNumber;
    //装修房屋数量
    private Integer decorateHouseNumber;
    //待售房屋数量（空置）
    private Integer forSaleHouseNumber;
    //停车位总数量
    private Integer parkingSpace;
    //待售车位数量
    private Integer forSaleParkingSpace;
    //房屋空置率(转化后保留两位小数)
    private Double forSaleHouseScale;
    //房屋装修率(转化后保留两位小数)
    private Double decorateHouseScale;
    //车位空置率(转化后保留两位小数)
    private Double forSaleParkingSpaceScale;


    /****************************************品质检查统计数据******************************/
    //品质检查信息
    private  Map<String, Object> qualityManageMap;

    //月品质检查项（个/年）
    private Integer yearQualityCheck;
    //月品质检查合格项（个/年）
    private Integer yearQualityCheckPass;
    //月品质检查不合格项（个/年）
    private Integer yearQualityCheckFail;
    //月品质检查未整改项（个/年）
    private Integer yearQualityCheckUnmodified;
    //检查合格率(年)
    private Double yearQualityCheckPassPercent;
    //整改合格率（年）
    private Double yearModifiedPassPercent;
    //月品质检查项（个/月）
    private Integer qualityCheck;
    //月品质检查合格项（个/月）
    private Integer qualityCheckPass;
    //月品质检查不合格项（个/月）
    private Integer qualityCheckFail;
    //月品质检查未整改项（个/月）
    private Integer qualityCheckUnmodified;
    //月安全事件项
    private Integer securityEvent;
    //检查合格率(月)
    private Double qualityCheckPassPercent;
    //整改合格率（月）
    private Double modifiedPassPercent;
    //合格率(月)
    private  Map<Integer, Double> checkPassScaleMap;
    //整改合格率(月)
    private  Map<Integer, Double> modifiedPassScaleMap;
    /*//年度品质检查信息
    private  Map<String, Object> qualityManageYearMap;
    //月度品质检查信息
    private  Map<String, Object> qualityManageMonth;*/


    /*******************************人员成本统计信息************************/
    private  SysLaborCostDetailsEntity sysLaborCostDetails;


    //月人工成本支出
    private Double laborCostTotal;
    //人均总成本
    private Double averageLaborCost;
    //编制总人数（月）
    private Integer headcountTotal;
    //在职总人员（月）
    private Integer employeeTotal;
    //入职总人数（月）
    private Integer entryTotal;
    //离职总人数（月）
    private Integer demissionTotal;
    //缺编率
    private Double sysEmployeeScale;
    //人工支出占比
    private Double sysLaborCostScale;
    //上月人工支出占比
    private Double sysLaborCostLastMonthScale;
    //流失率
    private Double sysDemissionScale;
    //常态物业成本占比
    private Double propertyLaborCostScale;
    //电商成本占比
    private Double eBusinessScale;
    //销配成本占比
    private Double saleLaborCostScale;

    public Map<String, Object> getSysProjectEnergyMap() {
        return sysProjectEnergyMap;
    }

    public void setSysProjectEnergyMap(Map<String, Object> sysProjectEnergyMap) {
        this.sysProjectEnergyMap = sysProjectEnergyMap;
    }

    public SysBasicDataEntity getSysBasicData() {
        return sysBasicData;
    }

    public void setSysBasicData(SysBasicDataEntity sysBasicData) {
        this.sysBasicData = sysBasicData;
    }

    public Map<String, Object> getQualityManageMap() {
        return qualityManageMap;
    }

    public void setQualityManageMap(Map<String, Object> qualityManageMap) {
        this.qualityManageMap = qualityManageMap;
    }

    public SysLaborCostDetailsEntity getSysLaborCostDetails() {
        return sysLaborCostDetails;
    }

    public void setSysLaborCostDetails(SysLaborCostDetailsEntity sysLaborCostDetails) {
        this.sysLaborCostDetails = sysLaborCostDetails;
    }

    public Integer getProjectUnfinishedTotal() {
        return projectUnfinishedTotal;
    }

    public void setProjectUnfinishedTotal(Integer projectUnfinishedTotal) {
        this.projectUnfinishedTotal = projectUnfinishedTotal;
    }

    public Integer getProjectFinishedTotal() {
        return projectFinishedTotal;
    }

    public void setProjectFinishedTotal(Integer projectFinishedTotal) {
        this.projectFinishedTotal = projectFinishedTotal;
    }

    public Double getMonthProjectUnfinishedScale() {
        return monthProjectUnfinishedScale;
    }

    public void setMonthProjectUnfinishedScale(Double monthProjectUnfinishedScale) {
        this.monthProjectUnfinishedScale = monthProjectUnfinishedScale;
    }

    public Double getYearConsumptionElectricity() {
        return yearConsumptionElectricity;
    }

    public void setYearConsumptionElectricity(Double yearConsumptionElectricity) {
        this.yearConsumptionElectricity = yearConsumptionElectricity;
    }

    public Double getYearConsumptionWater() {
        return yearConsumptionWater;
    }

    public void setYearConsumptionWater(Double yearConsumptionWater) {
        this.yearConsumptionWater = yearConsumptionWater;
    }

    public Double getMonthConsumptionElectricity() {
        return monthConsumptionElectricity;
    }

    public void setMonthConsumptionElectricity(Double monthConsumptionElectricity) {
        this.monthConsumptionElectricity = monthConsumptionElectricity;
    }

    public Double getMonthConsumptionWater() {
        return monthConsumptionWater;
    }

    public void setMonthConsumptionWater(Double monthConsumptionWater) {
        this.monthConsumptionWater = monthConsumptionWater;
    }

    public Double getYoYElectricityScale() {
        return yoYElectricityScale;
    }

    public void setYoYElectricityScale(Double yoYElectricityScale) {
        this.yoYElectricityScale = yoYElectricityScale;
    }

    public Double getMtoMtElectricityScale() {
        return mtoMtElectricityScale;
    }

    public void setMtoMtElectricityScale(Double mtoMtElectricityScale) {
        this.mtoMtElectricityScale = mtoMtElectricityScale;
    }

    public Double getYoYWaterScale() {
        return yoYWaterScale;
    }

    public void setYoYWaterScale(Double yoYWaterScale) {
        this.yoYWaterScale = yoYWaterScale;
    }

    public Double getMtoMtWaterScale() {
        return mtoMtWaterScale;
    }

    public void setMtoMtWaterScale(Double mtoMtWaterScale) {
        this.mtoMtWaterScale = mtoMtWaterScale;
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

    public Integer getSalesDistribution() {
        return salesDistribution;
    }

    public void setSalesDistribution(Integer salesDistribution) {
        this.salesDistribution = salesDistribution;
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

    public Integer getDecorateHouseNumber() {
        return decorateHouseNumber;
    }

    public void setDecorateHouseNumber(Integer decorateHouseNumber) {
        this.decorateHouseNumber = decorateHouseNumber;
    }

    public Integer getForSaleHouseNumber() {
        return forSaleHouseNumber;
    }

    public void setForSaleHouseNumber(Integer forSaleHouseNumber) {
        this.forSaleHouseNumber = forSaleHouseNumber;
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

    public Integer getYearQualityCheck() {
        return yearQualityCheck;
    }

    public void setYearQualityCheck(Integer yearQualityCheck) {
        this.yearQualityCheck = yearQualityCheck;
    }

    public Integer getYearQualityCheckPass() {
        return yearQualityCheckPass;
    }

    public void setYearQualityCheckPass(Integer yearQualityCheckPass) {
        this.yearQualityCheckPass = yearQualityCheckPass;
    }

    public Integer getYearQualityCheckFail() {
        return yearQualityCheckFail;
    }

    public void setYearQualityCheckFail(Integer yearQualityCheckFail) {
        this.yearQualityCheckFail = yearQualityCheckFail;
    }

    public Integer getYearQualityCheckUnmodified() {
        return yearQualityCheckUnmodified;
    }

    public void setYearQualityCheckUnmodified(Integer yearQualityCheckUnmodified) {
        this.yearQualityCheckUnmodified = yearQualityCheckUnmodified;
    }

    public Double getYearQualityCheckPassPercent() {
        return yearQualityCheckPassPercent;
    }

    public void setYearQualityCheckPassPercent(Double yearQualityCheckPassPercent) {
        this.yearQualityCheckPassPercent = yearQualityCheckPassPercent;
    }

    public Double getYearModifiedPassPercent() {
        return yearModifiedPassPercent;
    }

    public void setYearModifiedPassPercent(Double yearModifiedPassPercent) {
        this.yearModifiedPassPercent = yearModifiedPassPercent;
    }

    public Integer getQualityCheck() {
        return qualityCheck;
    }

    public void setQualityCheck(Integer qualityCheck) {
        this.qualityCheck = qualityCheck;
    }

    public Integer getQualityCheckPass() {
        return qualityCheckPass;
    }

    public void setQualityCheckPass(Integer qualityCheckPass) {
        this.qualityCheckPass = qualityCheckPass;
    }

    public Integer getQualityCheckFail() {
        return qualityCheckFail;
    }

    public void setQualityCheckFail(Integer qualityCheckFail) {
        this.qualityCheckFail = qualityCheckFail;
    }

    public Integer getQualityCheckUnmodified() {
        return qualityCheckUnmodified;
    }

    public void setQualityCheckUnmodified(Integer qualityCheckUnmodified) {
        this.qualityCheckUnmodified = qualityCheckUnmodified;
    }

    public Integer getSecurityEvent() {
        return securityEvent;
    }

    public void setSecurityEvent(Integer securityEvent) {
        this.securityEvent = securityEvent;
    }

    public Double getQualityCheckPassPercent() {
        return qualityCheckPassPercent;
    }

    public void setQualityCheckPassPercent(Double qualityCheckPassPercent) {
        this.qualityCheckPassPercent = qualityCheckPassPercent;
    }

    public Double getModifiedPassPercent() {
        return modifiedPassPercent;
    }

    public void setModifiedPassPercent(Double modifiedPassPercent) {
        this.modifiedPassPercent = modifiedPassPercent;
    }

    public Map<Integer, Double> getCheckPassScaleMap() {
        return checkPassScaleMap;
    }

    public void setCheckPassScaleMap(Map<Integer, Double> checkPassScaleMap) {
        this.checkPassScaleMap = checkPassScaleMap;
    }

    public Map<Integer, Double> getModifiedPassScaleMap() {
        return modifiedPassScaleMap;
    }

    public void setModifiedPassScaleMap(Map<Integer, Double> modifiedPassScaleMap) {
        this.modifiedPassScaleMap = modifiedPassScaleMap;
    }

    public Double getLaborCostTotal() {
        return laborCostTotal;
    }

    public void setLaborCostTotal(Double laborCostTotal) {
        this.laborCostTotal = laborCostTotal;
    }

    public Double getAverageLaborCost() {
        return averageLaborCost;
    }

    public void setAverageLaborCost(Double averageLaborCost) {
        this.averageLaborCost = averageLaborCost;
    }

    public Integer getHeadcountTotal() {
        return headcountTotal;
    }

    public void setHeadcountTotal(Integer headcountTotal) {
        this.headcountTotal = headcountTotal;
    }

    public Integer getEmployeeTotal() {
        return employeeTotal;
    }

    public void setEmployeeTotal(Integer employeeTotal) {
        this.employeeTotal = employeeTotal;
    }

    public Integer getEntryTotal() {
        return entryTotal;
    }

    public void setEntryTotal(Integer entryTotal) {
        this.entryTotal = entryTotal;
    }

    public Integer getDemissionTotal() {
        return demissionTotal;
    }

    public void setDemissionTotal(Integer demissionTotal) {
        this.demissionTotal = demissionTotal;
    }

    public Double getSysEmployeeScale() {
        return sysEmployeeScale;
    }

    public void setSysEmployeeScale(Double sysEmployeeScale) {
        this.sysEmployeeScale = sysEmployeeScale;
    }

    public Double getSysLaborCostScale() {
        return sysLaborCostScale;
    }

    public void setSysLaborCostScale(Double sysLaborCostScale) {
        this.sysLaborCostScale = sysLaborCostScale;
    }

    public Double getSysLaborCostLastMonthScale() {
        return sysLaborCostLastMonthScale;
    }

    public void setSysLaborCostLastMonthScale(Double sysLaborCostLastMonthScale) {
        this.sysLaborCostLastMonthScale = sysLaborCostLastMonthScale;
    }

    public Double getSysDemissionScale() {
        return sysDemissionScale;
    }

    public void setSysDemissionScale(Double sysDemissionScale) {
        this.sysDemissionScale = sysDemissionScale;
    }

    public Double getPropertyLaborCostScale() {
        return propertyLaborCostScale;
    }

    public void setPropertyLaborCostScale(Double propertyLaborCostScale) {
        this.propertyLaborCostScale = propertyLaborCostScale;
    }

    public Double geteBusinessScale() {
        return eBusinessScale;
    }

    public void seteBusinessScale(Double eBusinessScale) {
        this.eBusinessScale = eBusinessScale;
    }

    public Double getSaleLaborCostScale() {
        return saleLaborCostScale;
    }

    public void setSaleLaborCostScale(Double saleLaborCostScale) {
        this.saleLaborCostScale = saleLaborCostScale;
    }
}


