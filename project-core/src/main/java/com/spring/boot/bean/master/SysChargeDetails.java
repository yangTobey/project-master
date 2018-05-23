package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysChargeDetails implements Serializable {

    private Long chargeId;
    private Double chargeMoney;
    private Double chargeMoneyNow;
    private Double chargeDebt;
    private Double chargeDebtReturn;
    private Date createTime;
    private Long companyId;
    private Integer statusCode;
    private Integer weekOfYear;
    private Integer year;
    private Integer month;
    //当期收缴率
    private Double chargeMoneyScale;
    //清欠收缴率
    private Double chargeDebtScale;
    private String companyName;

    //一年当期收缴率（针对大屏数据展示页面）
    private Map<Integer,Double> yearChargeMoneyScale;
    //一年清欠收缴率（针对大屏数据展示页面）
    private Map<Integer,Double> yearChargeDebtScale;

    public Long getChargeId() {
        return chargeId;
    }

    public void setChargeId(Long chargeId) {
        this.chargeId = chargeId;
    }

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
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

    public Integer getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(Integer weekOfYear) {
        this.weekOfYear = weekOfYear;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}
