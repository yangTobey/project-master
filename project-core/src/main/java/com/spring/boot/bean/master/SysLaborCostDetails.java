package com.spring.boot.bean.master;

/**
 * Created by Administrator on 2018/3/12.
 */
public class SysLaborCostDetails {
    private long detailsId;
    private int employeeTotal;
    private int headcountTotal;
    private int entryTotal;
    private int demissionTotal;
    private int laborCostTotal;
    private long laborCostId;
    private int departmentType;

    public long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(long detailsId) {
        this.detailsId = detailsId;
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

    public long getLaborCostId() {
        return laborCostId;
    }

    public void setLaborCostId(long laborCostId) {
        this.laborCostId = laborCostId;
    }

    public int getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(int departmentType) {
        this.departmentType = departmentType;
    }
}
