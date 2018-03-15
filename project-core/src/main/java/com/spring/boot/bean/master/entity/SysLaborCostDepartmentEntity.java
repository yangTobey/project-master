package com.spring.boot.bean.master.entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/12.
 */
public class SysLaborCostDepartmentEntity {


    private long laborCostDetailsId;
    private int employeeTotal;
    private int headcountTotal;
    private int entryTotal;
    private int demissionTotal;
    private int laborCostTotal;
    private int departmentType;



    public long getLaborCostDetailsId() {
        return laborCostDetailsId;
    }

    public void setLaborCostDetailsId(long laborCostDetailsId) {
        this.laborCostDetailsId = laborCostDetailsId;
    }

    public int getEmployeeTotal() {
        return employeeTotal;
    }

    public void setEmployeeTotal(int employeeTotal) {
        this.employeeTotal = employeeTotal;
    }

    public int getHeadcountTotal() {
        return headcountTotal;
    }

    public void setHeadcountTotal(int headcountTotal) {
        this.headcountTotal = headcountTotal;
    }

    public int getEntryTotal() {
        return entryTotal;
    }

    public void setEntryTotal(int entryTotal) {
        this.entryTotal = entryTotal;
    }

    public int getDemissionTotal() {
        return demissionTotal;
    }

    public void setDemissionTotal(int demissionTotal) {
        this.demissionTotal = demissionTotal;
    }

    public int getLaborCostTotal() {
        return laborCostTotal;
    }

    public void setLaborCostTotal(int laborCostTotal) {
        this.laborCostTotal = laborCostTotal;
    }

    public int getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(int departmentType) {
        this.departmentType = departmentType;
    }
}
