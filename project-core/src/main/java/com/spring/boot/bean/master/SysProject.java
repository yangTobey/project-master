package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/4/13.
 */
public class SysProject {
    private Long projectId;
    private String projectName;
    private Integer projectTotal;
    private Integer projectUnfinishedTotal;
    private Integer projectFinishedTotal;
    private Date createTime;
    private Long companyId;
    private Integer year;
    private Integer month;
    private Integer statusCode;

    private Integer monthConsumptionElectricity;
    private Integer monthConsumptionWater;
    private Integer fileNum;
    private String companyName;

    List<SysProjectEnergyFile> fileList;

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

    public Integer getMonthConsumptionElectricity() {
        return monthConsumptionElectricity;
    }

    public void setMonthConsumptionElectricity(Integer monthConsumptionElectricity) {
        this.monthConsumptionElectricity = monthConsumptionElectricity;
    }

    public Integer getMonthConsumptionWater() {
        return monthConsumptionWater;
    }

    public void setMonthConsumptionWater(Integer monthConsumptionWater) {
        this.monthConsumptionWater = monthConsumptionWater;
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
}
