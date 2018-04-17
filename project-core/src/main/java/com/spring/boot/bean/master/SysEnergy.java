package com.spring.boot.bean.master;

import java.util.Date;

/**
 * Created by Administrator on 2018/4/13.
 */
public class SysEnergy {
    private Long energyId;
    private Double monthConsumptionElectricity;
    private Double monthConsumptionWater;
    private Date createTime;
    private Long companyId;
    private Integer year;
    private Integer month;
    private Long projectId;

    public Long getEnergyId() {
        return energyId;
    }

    public void setEnergyId(Long energyId) {
        this.energyId = energyId;
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

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
