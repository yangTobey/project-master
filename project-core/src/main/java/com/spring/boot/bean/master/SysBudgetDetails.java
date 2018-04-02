package com.spring.boot.bean.master;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysBudgetDetails {
    private Long budgetId;
    private Integer year;
    private Integer month;
    private Double budgetIncome;
    private Double realIncome;
    private Double budgetProfits;
    private Double realProfits;
    private Double budgetExpenses;
    private Double personnelCost;
    private Double administrativeCost;
    private Double materialCost;
    private Double energyCost;
    private Double equipmentCost;
    private Double cleaningCost;
    private Double afforestCost;
    private Double orderMaintenanceCost;
    private Double communityActivitiesCost;
    private Double otherCost;
    private Double realExpensesTotal;
    private Timestamp createTime;
    private Integer statusCode;

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
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

    public Double getBudgetIncome() {
        return budgetIncome;
    }

    public void setBudgetIncome(Double budgetIncome) {
        this.budgetIncome = budgetIncome;
    }

    public Double getRealIncome() {
        return realIncome;
    }

    public void setRealIncome(Double realIncome) {
        this.realIncome = realIncome;
    }

    public Double getBudgetProfits() {
        return budgetProfits;
    }

    public void setBudgetProfits(Double budgetProfits) {
        this.budgetProfits = budgetProfits;
    }

    public Double getRealProfits() {
        return realProfits;
    }

    public void setRealProfits(Double realProfits) {
        this.realProfits = realProfits;
    }

    public Double getBudgetExpenses() {
        return budgetExpenses;
    }

    public void setBudgetExpenses(Double budgetExpenses) {
        this.budgetExpenses = budgetExpenses;
    }

    public Double getPersonnelCost() {
        return personnelCost;
    }

    public void setPersonnelCost(Double personnelCost) {
        this.personnelCost = personnelCost;
    }

    public Double getAdministrativeCost() {
        return administrativeCost;
    }

    public void setAdministrativeCost(Double administrativeCost) {
        this.administrativeCost = administrativeCost;
    }

    public Double getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(Double materialCost) {
        this.materialCost = materialCost;
    }

    public Double getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(Double energyCost) {
        this.energyCost = energyCost;
    }

    public Double getEquipmentCost() {
        return equipmentCost;
    }

    public void setEquipmentCost(Double equipmentCost) {
        this.equipmentCost = equipmentCost;
    }

    public Double getCleaningCost() {
        return cleaningCost;
    }

    public void setCleaningCost(Double cleaningCost) {
        this.cleaningCost = cleaningCost;
    }

    public Double getAfforestCost() {
        return afforestCost;
    }

    public void setAfforestCost(Double afforestCost) {
        this.afforestCost = afforestCost;
    }

    public Double getOrderMaintenanceCost() {
        return orderMaintenanceCost;
    }

    public void setOrderMaintenanceCost(Double orderMaintenanceCost) {
        this.orderMaintenanceCost = orderMaintenanceCost;
    }

    public Double getCommunityActivitiesCost() {
        return communityActivitiesCost;
    }

    public void setCommunityActivitiesCost(Double communityActivitiesCost) {
        this.communityActivitiesCost = communityActivitiesCost;
    }

    public Double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    public Double getRealExpensesTotal() {
        return realExpensesTotal;
    }

    public void setRealExpensesTotal(Double realExpensesTotal) {
        this.realExpensesTotal = realExpensesTotal;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
