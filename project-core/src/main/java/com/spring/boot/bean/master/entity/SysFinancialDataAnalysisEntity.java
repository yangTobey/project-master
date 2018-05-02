package com.spring.boot.bean.master.entity;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysBudgetDetails;
import com.spring.boot.bean.master.SysChargeDetails;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 财务大屏数据展示实体类
 * Created by xiaoyang on 2018/4/24.
 */
public class SysFinancialDataAnalysisEntity implements Serializable {

    /********************************收费情况统计信息************************/
   // private SysChargeDetails sysChargeDetails;

    //收费任务额
    private Double chargeMoney;
    //当期收费
    private Double chargeMoneyNow;
    //清欠任务额
    private Double chargeDebt;
    //清欠回款
    private Double chargeDebtReturn;
    //当期收缴率
    private Double chargeMoneyScale;
    //清欠收缴率
    private Double chargeDebtScale;
    //一年当期收缴率（针对大屏数据展示页面）
    private Map<Integer,Double> yearChargeMoneyScale;
    //一年清欠收缴率（针对大屏数据展示页面）
    private Map<Integer,Double> yearChargeDebtScale;




    /********************************应收账款统计信息************************/
    //private Map<String, Object> sysAccountsReceivableMap;

    //private Map<Integer,Object> sysAccountsReceivableMonth;
    //private Map<Integer,Object> sysAccountsReceivable;


    //业主应收款
    private Double receivableAccountsOwner;
    //业主已收款
    private Double completeAccountsOwner;
    //礼券减免（已收）
    private Double completeCoupon;
    //礼券减免（应收）
    private Double receivableCoupon;
    //空置（已收）
    private Double completeVacancy;
    //空置（应收）
    private Double receivableVacancy;
    //%3补贴款（已收）
    private Double completeSubsidy;
    //%3补贴款（应收）
    private Double receivableSubsidy;
    //销售配合（已收)
    private Double completeSales;
    //销售配合（应收)
    private Double receivableSales;
    //开办费（应收）
    private Double receivableOpen;
    //开办费（已收）
    private Double completeOpen;
    //物业补贴（已收）
    private Double completePropertySubsidy;
    //物业补贴（应收）
    private Double receivablePropertySubsidy;
    //地产应付款（已收）
    private Double completeHouseOther;
    //地产应付款（应收）
    private Double receivableHouseOther;
    //地产已付款总数
    private Double completeHouse;
    //地产应付款总数
    private Double receivableHouse;
    //礼券减免收缴率
    private Double couponScale;
    //空置收缴率
    private Double vacancyScale;
    //%3补贴收缴率
    private Double subsidyScale;
    //销售配合收缴率
    private Double salesScale;
    //开办费收缴率
    private Double openScale;
    //物业补贴收缴率
    private Double propertySubsidyScale;
    //其他地产收缴率
    private Double houseOtherScale;
    //业主应收款统计信息
    private Map<Integer,Object> receivableAccounts;
    //业主已收款统计信息
    private Map<Integer,Object> completeAccounts;




    /********************************执行预算统计信息************************/

    //预算收入
    private Double budgetIncome;
    //实际收入
    private Double realIncome;
    //预算利润
    private Double budgetProfits;
    //实际利润
    private Double realProfits;
    //预算支出
    private Double budgetExpenses;
    //人员费用
    private Double personnelCost;
    //行政费用
    private Double administrativeCost;
    //物料消耗费
    private Double materialCost;
    //能源消耗
    private Double energyCost;
    //设备消耗费用
    private Double equipmentCost;
    //清洁费
    private Double cleaningCost;
    //绿化消耗费用
    private Double afforestCost;
    //秩序维护费用
    private Double orderMaintenanceCost;
    //社区活动费用
    private Double communityActivitiesCost;
    //其他费用
    private Double otherCost;
    //实际总支出
    private Double realExpensesTotal;

    //实际收入,柱状图（年）
    private Map<Integer,Object> budgetIncomeMap;
    //预算收入,柱状图(年)
    private Map<Integer,Object> realIncomeMap;

    //实际支出,柱状图（年）
    Map<Integer, Object> realExpensesTotalMap ;
    //预算支出,柱状图（年）
    Map<Integer, Object> budgetExpensesMap ;
    //实际利润,柱状图（年）
    Map<Integer, Object> realProfitsMap;

    //实际利润环比
    Map<Integer, Object> realProfitsScaleMap ;


    public Double getChargeMoney() {
        return chargeMoney;
    }

    public void setChargeMoney(Double chargeMoney) {
        this.chargeMoney = chargeMoney;
    }

    public Double getChargeMoneyNow() {
        return chargeMoneyNow;
    }

    public void setChargeMoneyNow(Double chargeMoneyNow) {
        this.chargeMoneyNow = chargeMoneyNow;
    }

    public Double getChargeDebt() {
        return chargeDebt;
    }

    public void setChargeDebt(Double chargeDebt) {
        this.chargeDebt = chargeDebt;
    }

    public Double getChargeDebtReturn() {
        return chargeDebtReturn;
    }

    public void setChargeDebtReturn(Double chargeDebtReturn) {
        this.chargeDebtReturn = chargeDebtReturn;
    }

    public Double getChargeMoneyScale() {
        return chargeMoneyScale;
    }

    public void setChargeMoneyScale(Double chargeMoneyScale) {
        this.chargeMoneyScale = chargeMoneyScale;
    }

    public Double getChargeDebtScale() {
        return chargeDebtScale;
    }

    public void setChargeDebtScale(Double chargeDebtScale) {
        this.chargeDebtScale = chargeDebtScale;
    }

    public Map<Integer, Double> getYearChargeMoneyScale() {
        return yearChargeMoneyScale;
    }

    public void setYearChargeMoneyScale(Map<Integer, Double> yearChargeMoneyScale) {
        this.yearChargeMoneyScale = yearChargeMoneyScale;
    }

    public Map<Integer, Double> getYearChargeDebtScale() {
        return yearChargeDebtScale;
    }

    public void setYearChargeDebtScale(Map<Integer, Double> yearChargeDebtScale) {
        this.yearChargeDebtScale = yearChargeDebtScale;
    }

    public Double getReceivableAccountsOwner() {
        return receivableAccountsOwner;
    }

    public void setReceivableAccountsOwner(Double receivableAccountsOwner) {
        this.receivableAccountsOwner = receivableAccountsOwner;
    }

    public Double getCompleteAccountsOwner() {
        return completeAccountsOwner;
    }

    public void setCompleteAccountsOwner(Double completeAccountsOwner) {
        this.completeAccountsOwner = completeAccountsOwner;
    }

    public Double getCompleteCoupon() {
        return completeCoupon;
    }

    public void setCompleteCoupon(Double completeCoupon) {
        this.completeCoupon = completeCoupon;
    }

    public Double getReceivableCoupon() {
        return receivableCoupon;
    }

    public void setReceivableCoupon(Double receivableCoupon) {
        this.receivableCoupon = receivableCoupon;
    }

    public Double getCompleteVacancy() {
        return completeVacancy;
    }

    public void setCompleteVacancy(Double completeVacancy) {
        this.completeVacancy = completeVacancy;
    }

    public Double getReceivableVacancy() {
        return receivableVacancy;
    }

    public void setReceivableVacancy(Double receivableVacancy) {
        this.receivableVacancy = receivableVacancy;
    }

    public Double getCompleteSubsidy() {
        return completeSubsidy;
    }

    public void setCompleteSubsidy(Double completeSubsidy) {
        this.completeSubsidy = completeSubsidy;
    }

    public Double getReceivableSubsidy() {
        return receivableSubsidy;
    }

    public void setReceivableSubsidy(Double receivableSubsidy) {
        this.receivableSubsidy = receivableSubsidy;
    }

    public Double getCompleteSales() {
        return completeSales;
    }

    public void setCompleteSales(Double completeSales) {
        this.completeSales = completeSales;
    }

    public Double getReceivableSales() {
        return receivableSales;
    }

    public void setReceivableSales(Double receivableSales) {
        this.receivableSales = receivableSales;
    }

    public Double getReceivableOpen() {
        return receivableOpen;
    }

    public void setReceivableOpen(Double receivableOpen) {
        this.receivableOpen = receivableOpen;
    }

    public Double getCompleteOpen() {
        return completeOpen;
    }

    public void setCompleteOpen(Double completeOpen) {
        this.completeOpen = completeOpen;
    }

    public Double getCompletePropertySubsidy() {
        return completePropertySubsidy;
    }

    public void setCompletePropertySubsidy(Double completePropertySubsidy) {
        this.completePropertySubsidy = completePropertySubsidy;
    }

    public Double getReceivablePropertySubsidy() {
        return receivablePropertySubsidy;
    }

    public void setReceivablePropertySubsidy(Double receivablePropertySubsidy) {
        this.receivablePropertySubsidy = receivablePropertySubsidy;
    }

    public Double getCompleteHouseOther() {
        return completeHouseOther;
    }

    public void setCompleteHouseOther(Double completeHouseOther) {
        this.completeHouseOther = completeHouseOther;
    }

    public Double getReceivableHouseOther() {
        return receivableHouseOther;
    }

    public void setReceivableHouseOther(Double receivableHouseOther) {
        this.receivableHouseOther = receivableHouseOther;
    }

    public Double getCompleteHouse() {
        return completeHouse;
    }

    public void setCompleteHouse(Double completeHouse) {
        this.completeHouse = completeHouse;
    }

    public Double getReceivableHouse() {
        return receivableHouse;
    }

    public void setReceivableHouse(Double receivableHouse) {
        this.receivableHouse = receivableHouse;
    }

    public Double getCouponScale() {
        return couponScale;
    }

    public void setCouponScale(Double couponScale) {
        this.couponScale = couponScale;
    }

    public Double getVacancyScale() {
        return vacancyScale;
    }

    public void setVacancyScale(Double vacancyScale) {
        this.vacancyScale = vacancyScale;
    }

    public Double getSubsidyScale() {
        return subsidyScale;
    }

    public void setSubsidyScale(Double subsidyScale) {
        this.subsidyScale = subsidyScale;
    }

    public Double getSalesScale() {
        return salesScale;
    }

    public void setSalesScale(Double salesScale) {
        this.salesScale = salesScale;
    }

    public Double getOpenScale() {
        return openScale;
    }

    public void setOpenScale(Double openScale) {
        this.openScale = openScale;
    }

    public Double getPropertySubsidyScale() {
        return propertySubsidyScale;
    }

    public void setPropertySubsidyScale(Double propertySubsidyScale) {
        this.propertySubsidyScale = propertySubsidyScale;
    }

    public Double getHouseOtherScale() {
        return houseOtherScale;
    }

    public void setHouseOtherScale(Double houseOtherScale) {
        this.houseOtherScale = houseOtherScale;
    }

    public Map<Integer, Object> getReceivableAccounts() {
        return receivableAccounts;
    }

    public void setReceivableAccounts(Map<Integer, Object> receivableAccounts) {
        this.receivableAccounts = receivableAccounts;
    }

    public Map<Integer, Object> getCompleteAccounts() {
        return completeAccounts;
    }

    public void setCompleteAccounts(Map<Integer, Object> completeAccounts) {
        this.completeAccounts = completeAccounts;
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
}


