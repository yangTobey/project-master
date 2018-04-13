package com.spring.boot.bean.master;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/13.
 */
public class SysProject {
    private Long projectId;
    private String projectName;
    private Integer projectTotal;
    private Integer projectUnfinishedTotal;
    private Integer projectFinishedTotal;
    private Long projectEnergyId;
    private Date createTime;
    private Integer companyId;
    private Integer year;
    private Integer month;

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

    public Long getProjectEnergyId() {
        return projectEnergyId;
    }

    public void setProjectEnergyId(Long projectEnergyId) {
        this.projectEnergyId = projectEnergyId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
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
}
