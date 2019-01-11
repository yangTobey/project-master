package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysBudgetDetails implements Serializable {
    private Long budgetId;
    private Long companyId;
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


    private Date createTime;
    private Integer statusCode;

    private String companyName;

    private Map<Integer,Object> budgetIncomeMap;
    private Map<Integer,Object> realIncomeMap;

    //实际支出
    Map<Integer, Object> realExpensesTotalMap ;
    //预算支出
    Map<Integer, Object> budgetExpensesMap ;
    //实际利润
    Map<Integer, Object> realProfitsMap;

    //实际利润环比
    Map<Integer, Object> realProfitsScaleMap ;
    private String monthsInfo;
    public SysBudgetDetails(){

    }

    public SysBudgetDetails(Double budgetIncome, Double realIncome, Double budgetProfits, Double realProfits, Double budgetExpenses, Double personnelCost, Double administrativeCost, Double materialCost, Double energyCost,
             Double equipmentCost, Double cleaningCost, Double afforestCost, Double orderMaintenanceCost, Double communityActivitiesCost, Double otherCost, Double realExpensesTotal){
        this.budgetIncome=budgetIncome;
        this.realIncome=realIncome;
        this.budgetProfits=budgetProfits;
        this.realProfits=realProfits;
        this.budgetExpenses=budgetExpenses;
        this.personnelCost=personnelCost;
        this.administrativeCost=administrativeCost;
        this.materialCost=materialCost;
        this.energyCost=energyCost;
        this.equipmentCost=equipmentCost;
        this.cleaningCost=cleaningCost;
        this.afforestCost=afforestCost;
        this.orderMaintenanceCost=orderMaintenanceCost;
        this.communityActivitiesCost=communityActivitiesCost;
        this.otherCost=otherCost;
        this.realExpensesTotal=realExpensesTotal;

    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
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

    public Map<Integer, Object> getBudgetIncomeMap() {
        return budgetIncomeMap;
    }

    public void setBudgetIncomeMap(Map<Integer, Object> budgetIncomeMap) {
        this.budgetIncomeMap = budgetIncomeMap;
    }

    public Map<Integer, Object> getRealIncomeMap() {
        return realIncomeMap;
    }

    public void setRealIncomeMap(Map<Integer, Object> realIncomeMap) {
        this.realIncomeMap = realIncomeMap;
    }

    public String getMonthsInfo() {
        return monthsInfo;
    }

    public void setMonthsInfo(String monthsInfo) {
        this.monthsInfo = monthsInfo;
    }

    public Map<Integer, Object> getRealExpensesTotalMap() {
        return realExpensesTotalMap;
    }

    public void setRealExpensesTotalMap(Map<Integer, Object> realExpensesTotalMap) {
        this.realExpensesTotalMap = realExpensesTotalMap;
    }

    public Map<Integer, Object> getBudgetExpensesMap() {
        return budgetExpensesMap;
    }

    public void setBudgetExpensesMap(Map<Integer, Object> budgetExpensesMap) {
        this.budgetExpensesMap = budgetExpensesMap;
    }

    public Map<Integer, Object> getRealProfitsMap() {
        return realProfitsMap;
    }

    public void setRealProfitsMap(Map<Integer, Object> realProfitsMap) {
        this.realProfitsMap = realProfitsMap;
    }

    public Map<Integer, Object> getRealProfitsScaleMap() {
        return realProfitsScaleMap;
    }

    public void setRealProfitsScaleMap(Map<Integer, Object> realProfitsScaleMap) {
        this.realProfitsScaleMap = realProfitsScaleMap;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
