package com.spring.boot.bean.master.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.zip.DeflaterOutputStream;

/**
 * Created by Administrator on 2018/3/12.
 */
public class SysLaborCostDetailsEntity {

    private Long  laborCostId;
    private Integer year;
    private Integer month;
    private Long companyId;
    private Date createTime;
    private Integer statusCode;

    private Long laborCostDetailsId;
    private Integer employeeTotal;
    private Integer headcountTotal;
    private Integer entryTotal;
    private Integer demissionTotal;
    private Double laborCostTotal;
    private Integer departmentType;
    private String companyName;

    private Double averageLaborCost;
    //人工支出占比
    private Double sysLaborCostScale;
    //上月人工支出占比
    private Double sysLaborCostLastMonthScale;
    //缺编率
    private Double sysEmployeeScale;
    //流失率
    private Double sysDemissionScale;

    //常态物业成本占比
    private Double propertyLaborCostScale;
    //电商成本占比
    private Double eBusinessScale;
    //销配成本占比
    private Double saleLaborCostScale;

    public Long getLaborCostId() {
        return laborCostId;
    }

    public void setLaborCostId(Long laborCostId) {
        this.laborCostId = laborCostId;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Long getLaborCostDetailsId() {
        return laborCostDetailsId;
    }

    public void setLaborCostDetailsId(Long laborCostDetailsId) {
        this.laborCostDetailsId = laborCostDetailsId;
    }

    public Integer getEmployeeTotal() {
        return employeeTotal;
    }

    public void setEmployeeTotal(Integer employeeTotal) {
        this.employeeTotal = employeeTotal;
    }

    public Integer getHeadcountTotal() {
        return headcountTotal;
    }

    public void setHeadcountTotal(Integer headcountTotal) {
        this.headcountTotal = headcountTotal;
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

    public Double getLaborCostTotal() {
        return laborCostTotal;
    }

    public void setLaborCostTotal(Double laborCostTotal) {
        this.laborCostTotal = laborCostTotal;
    }

    public Integer getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(Integer departmentType) {
        this.departmentType = departmentType;
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

    public Double getSysEmployeeScale() {
        return sysEmployeeScale;
    }

    public void setSysEmployeeScale(Double sysEmployeeScale) {
        this.sysEmployeeScale = sysEmployeeScale;
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

    public Double getAverageLaborCost() {
        return averageLaborCost;
    }

    public void setAverageLaborCost(Double averageLaborCost) {
        this.averageLaborCost = averageLaborCost;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
