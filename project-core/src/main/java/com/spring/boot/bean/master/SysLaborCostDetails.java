package com.spring.boot.bean.master;

/**
 * Created by Administrator on 2018/3/12.
 */
public class SysLaborCostDetails {
    private Long detailsId;
    private Integer employeeTotal;
    private Integer headcountTotal;
    private Integer entryTotal;
    private Integer demissionTotal;
    private Double laborCostTotal;
    private Long laborCostId;
    private Integer departmentType;

    private Integer payPeopleTotal;
    private Integer beginMonthPeople;
    private Integer monthDeploy;

    public Long getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(Long detailsId) {
        this.detailsId = detailsId;
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

    public Long getLaborCostId() {
        return laborCostId;
    }

    public void setLaborCostId(Long laborCostId) {
        this.laborCostId = laborCostId;
    }

    public Integer getDepartmentType() {
        return departmentType;
    }

    public void setDepartmentType(Integer departmentType) {
        this.departmentType = departmentType;
    }

    public Integer getPayPeopleTotal() {
        return payPeopleTotal;
    }

    public void setPayPeopleTotal(Integer payPeopleTotal) {
        this.payPeopleTotal = payPeopleTotal;
    }

    public Integer getBeginMonthPeople() {
        return beginMonthPeople;
    }

    public void setBeginMonthPeople(Integer beginMonthPeople) {
        this.beginMonthPeople = beginMonthPeople;
    }

    public Integer getMonthDeploy() {
        return monthDeploy;
    }

    public void setMonthDeploy(Integer monthDeploy) {
        this.monthDeploy = monthDeploy;
    }
}
