package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/13.
 */
public class SysProject implements Serializable {
    private static final long serialVersionUID = 4084723666317353000L;
    private Long projectId;
    private String projectName;
    private Integer projectTotal;
    private Integer projectUnfinishedTotal;
    private Integer projectFinishedTotal;
    private Integer yearProjectUnfinishedTotal;
    private Integer yearProjectFinishedTotal;
    private Date createTime;
    private Long companyId;
    private Integer year;
    private Integer month;
    private Integer statusCode;

    private Double monthConsumptionElectricity;
    private Double monthConsumptionWater;
    //年度耗电量
    private Double yearConsumptionElectricity;
    //年度耗水量
    private Double yearConsumptionWater;
    private Integer fileNum;
    private String companyName;

    //年系统遗留问题处理率
    private Double yearProjectUnfinishedScale;
    //耗电量同比
    private Double yoYConsumptionElectricityScale;
    //耗电量环比
    private Double mtoMtConsumptionElectricityScale;
    //耗水量同比
    private Double yoYConsumptionWaterScale;
    //耗水量环比
    private Double mtoMtConsumptionWaterScale;
    List<SysProjectEnergyFile> fileList;
    /**
     * 往来文件信息
     */
    private String previousFileMes;
    /**
     * 验收文件信息
     */
    private String acceptFileMes;



    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getProjectTotal() {
        return projectTotal;
    }

    public void setProjectTotal(Integer projectTotal) {
        this.projectTotal = projectTotal;
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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }

    public List<SysProjectEnergyFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<SysProjectEnergyFile> fileList) {
        this.fileList = fileList;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getYearProjectUnfinishedTotal() {
        return yearProjectUnfinishedTotal;
    }

    public void setYearProjectUnfinishedTotal(Integer yearProjectUnfinishedTotal) {
        this.yearProjectUnfinishedTotal = yearProjectUnfinishedTotal;
    }

    public Integer getYearProjectFinishedTotal() {
        return yearProjectFinishedTotal;
    }

    public void setYearProjectFinishedTotal(Integer yearProjectFinishedTotal) {
        this.yearProjectFinishedTotal = yearProjectFinishedTotal;
    }

    public Double getYearProjectUnfinishedScale() {
        return yearProjectUnfinishedScale;
    }

    public void setYearProjectUnfinishedScale(Double yearProjectUnfinishedScale) {
        this.yearProjectUnfinishedScale = yearProjectUnfinishedScale;
    }

    public Double getYoYConsumptionElectricityScale() {
        return yoYConsumptionElectricityScale;
    }

    public void setYoYConsumptionElectricityScale(Double yoYConsumptionElectricityScale) {
        this.yoYConsumptionElectricityScale = yoYConsumptionElectricityScale;
    }

    public Double getMtoMtConsumptionElectricityScale() {
        return mtoMtConsumptionElectricityScale;
    }

    public void setMtoMtConsumptionElectricityScale(Double mtoMtConsumptionElectricityScale) {
        this.mtoMtConsumptionElectricityScale = mtoMtConsumptionElectricityScale;
    }

    public Double getYoYConsumptionWaterScale() {
        return yoYConsumptionWaterScale;
    }

    public void setYoYConsumptionWaterScale(Double yoYConsumptionWaterScale) {
        this.yoYConsumptionWaterScale = yoYConsumptionWaterScale;
    }

    public Double getMtoMtConsumptionWaterScale() {
        return mtoMtConsumptionWaterScale;
    }

    public void setMtoMtConsumptionWaterScale(Double mtoMtConsumptionWaterScale) {
        this.mtoMtConsumptionWaterScale = mtoMtConsumptionWaterScale;
    }

    public String getPreviousFileMes() {
        return previousFileMes;
    }

    public void setPreviousFileMes(String previousFileMes) {
        this.previousFileMes = previousFileMes;
    }

    public String getAcceptFileMes() {
        return acceptFileMes;
    }

    public void setAcceptFileMes(String acceptFileMes) {
        this.acceptFileMes = acceptFileMes;
    }
}
