package com.spring.boot.bean.master.entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/12.
 */
public class SysLaborCostDetailsEntity {

    private long laborCostId;
    private String year;
    private String month;
    private long companyId;
    private Timestamp createTime;
    private int statusCode;

    private long laborCostDetailsId;
    private int employeeTotal;
    private int headcountTotal;
    private int entryTotal;
    private int demissionTotal;
    private int laborCostTotal;
    private int departmentType;

    public long getLaborCostId() {
        return laborCostId;
    }

    public void setLaborCostId(long laborCostId) {
        this.laborCostId = laborCostId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

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
