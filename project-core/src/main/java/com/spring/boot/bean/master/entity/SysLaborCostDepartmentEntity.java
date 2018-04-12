package com.spring.boot.bean.master.entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/12.
 */
public class SysLaborCostDepartmentEntity {


    private Long laborCostDetailsId;
    private Integer employeeTotal;
    private Integer headcountTotal;
    private Integer entryTotal;
    private Integer demissionTotal;
    private Double laborCostTotal;
    private Integer departmentType;

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
}
