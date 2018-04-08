package com.spring.boot.bean.master.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * 小业主应收款详细信息
 * Created by Administrator on 2018/3/16.
 */
public class SysReceivableAccountsOwnerEntity {
    //对应月份
    private Integer month;
    //对应数据所在年份所在月份列表
    private String months;

    //一月份应收款
    private Double receivableAccountsJan;
    //一月份已收款
    private Double completeAccountsJan;

    //二月份应收款
    private Double receivableAccountsFeb;
    //二月份已收款
    private Double completeAccountsFeb;

    //三月份应收款
    private Double receivableAccountsMar;
    //三月份已收款
    private Double completeAccountsMar;

    //四月份应收款
    private Double receivableAccountsApr;
    //四月份已收款
    private Double completeAccountsApr;

    //五月份应收款
    private Double receivableAccountsMay;
    //五月份已收款
    private Double completeAccountsMay;

    //六月份应收款
    private Double receivableAccountsJune;
    //六月份已收款
    private Double completeAccountsJune;

    //七月份应收款
    private Double receivableAccountsJuly;
    //七月份已收款
    private Double completeAccountsJuly;

    //八月份应收款
    private Double receivableAccountsAug;
    //八月份已收款
    private Double completeAccountsAug;

    //九月份应收款
    private Double receivableAccountsSept;
    //九月份已收款
    private Double completeAccountsSept;

    //十月份应收款
    private Double receivableAccountsOct;
    //十月份已收款
    private Double completeAccountsOct;

    //十一月份应收款
    private Double receivableAccountsNov;
    //十一月份已收款
    private Double completeAccountsNov;

    //十二月份应收款
    private Double receivableAccountsDec;
    //十二月份已收款
    private Double completeAccountsDec;

    public Double getReceivableAccountsJan() {
        return receivableAccountsJan;
    }

    public void setReceivableAccountsJan(Double receivableAccountsJan) {
        this.receivableAccountsJan = receivableAccountsJan;
    }

    public Double getCompleteAccountsJan() {
        return completeAccountsJan;
    }

    public void setCompleteAccountsJan(Double completeAccountsJan) {
        this.completeAccountsJan = completeAccountsJan;
    }

    public Double getReceivableAccountsFeb() {
        return receivableAccountsFeb;
    }

    public void setReceivableAccountsFeb(Double receivableAccountsFeb) {
        this.receivableAccountsFeb = receivableAccountsFeb;
    }

    public Double getCompleteAccountsFeb() {
        return completeAccountsFeb;
    }

    public void setCompleteAccountsFeb(Double completeAccountsFeb) {
        this.completeAccountsFeb = completeAccountsFeb;
    }

    public Double getReceivableAccountsMar() {
        return receivableAccountsMar;
    }

    public void setReceivableAccountsMar(Double receivableAccountsMar) {
        this.receivableAccountsMar = receivableAccountsMar;
    }

    public Double getCompleteAccountsMar() {
        return completeAccountsMar;
    }

    public void setCompleteAccountsMar(Double completeAccountsMar) {
        this.completeAccountsMar = completeAccountsMar;
    }

    public Double getReceivableAccountsApr() {
        return receivableAccountsApr;
    }

    public void setReceivableAccountsApr(Double receivableAccountsApr) {
        this.receivableAccountsApr = receivableAccountsApr;
    }

    public Double getCompleteAccountsApr() {
        return completeAccountsApr;
    }

    public void setCompleteAccountsApr(Double completeAccountsApr) {
        this.completeAccountsApr = completeAccountsApr;
    }

    public Double getReceivableAccountsMay() {
        return receivableAccountsMay;
    }

    public void setReceivableAccountsMay(Double receivableAccountsMay) {
        this.receivableAccountsMay = receivableAccountsMay;
    }

    public Double getCompleteAccountsMay() {
        return completeAccountsMay;
    }

    public void setCompleteAccountsMay(Double completeAccountsMay) {
        this.completeAccountsMay = completeAccountsMay;
    }

    public Double getReceivableAccountsJune() {
        return receivableAccountsJune;
    }

    public void setReceivableAccountsJune(Double receivableAccountsJune) {
        this.receivableAccountsJune = receivableAccountsJune;
    }

    public Double getCompleteAccountsJune() {
        return completeAccountsJune;
    }

    public void setCompleteAccountsJune(Double completeAccountsJune) {
        this.completeAccountsJune = completeAccountsJune;
    }

    public Double getReceivableAccountsJuly() {
        return receivableAccountsJuly;
    }

    public void setReceivableAccountsJuly(Double receivableAccountsJuly) {
        this.receivableAccountsJuly = receivableAccountsJuly;
    }

    public Double getCompleteAccountsJuly() {
        return completeAccountsJuly;
    }

    public void setCompleteAccountsJuly(Double completeAccountsJuly) {
        this.completeAccountsJuly = completeAccountsJuly;
    }

    public Double getReceivableAccountsAug() {
        return receivableAccountsAug;
    }

    public void setReceivableAccountsAug(Double receivableAccountsAug) {
        this.receivableAccountsAug = receivableAccountsAug;
    }

    public Double getCompleteAccountsAug() {
        return completeAccountsAug;
    }

    public void setCompleteAccountsAug(Double completeAccountsAug) {
        this.completeAccountsAug = completeAccountsAug;
    }

    public Double getReceivableAccountsSept() {
        return receivableAccountsSept;
    }

    public void setReceivableAccountsSept(Double receivableAccountsSept) {
        this.receivableAccountsSept = receivableAccountsSept;
    }

    public Double getCompleteAccountsSept() {
        return completeAccountsSept;
    }

    public void setCompleteAccountsSept(Double completeAccountsSept) {
        this.completeAccountsSept = completeAccountsSept;
    }

    public Double getReceivableAccountsOct() {
        return receivableAccountsOct;
    }

    public void setReceivableAccountsOct(Double receivableAccountsOct) {
        this.receivableAccountsOct = receivableAccountsOct;
    }

    public Double getCompleteAccountsOct() {
        return completeAccountsOct;
    }

    public void setCompleteAccountsOct(Double completeAccountsOct) {
        this.completeAccountsOct = completeAccountsOct;
    }

    public Double getReceivableAccountsNov() {
        return receivableAccountsNov;
    }

    public void setReceivableAccountsNov(Double receivableAccountsNov) {
        this.receivableAccountsNov = receivableAccountsNov;
    }

    public Double getCompleteAccountsNov() {
        return completeAccountsNov;
    }

    public void setCompleteAccountsNov(Double completeAccountsNov) {
        this.completeAccountsNov = completeAccountsNov;
    }

    public Double getReceivableAccountsDec() {
        return receivableAccountsDec;
    }

    public void setReceivableAccountsDec(Double receivableAccountsDec) {
        this.receivableAccountsDec = receivableAccountsDec;
    }

    public Double getCompleteAccountsDec() {
        return completeAccountsDec;
    }

    public void setCompleteAccountsDec(Double completeAccountsDec) {
        this.completeAccountsDec = completeAccountsDec;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }
}


