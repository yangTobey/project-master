package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysAccountsReceivable implements Serializable {
    private Long accountsId;
    private Long companyId;
    private Integer year;
    private Integer month;
    private Double receivableAccountsOwner;
    private Double completeAccountsOwner;
    private Double completeCoupon;
    private Double receivableCoupon;
    private Double completeVacancy;
    private Double receivableVacancy;
    private Double completeSubsidy;
    private Double receivableSubsidy;
    private Double completeSales;
    private Double receivableSales;
    private Double receivableOpen;
    private Double completeOpen;
    private Double completePropertySubsidy;
    private Double receivablePropertySubsidy;
    private Double completeHouseOther;
    private Double receivableHouseOther;
    private Double completeHouse;
    private Double receivableHouse;
    private Date createTime;
    private Integer statusCode;
    private String companyName;

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

    public Long getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(Long accountsId) {
        this.accountsId = accountsId;
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
