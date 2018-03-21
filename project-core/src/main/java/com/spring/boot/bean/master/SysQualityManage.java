package com.spring.boot.bean.master;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/20.
 */
public class SysQualityManage {
    private long qualityId;
    private long companyId;
    private int year;
    private int month;
    private int qualityCheck;
    private int qualityCheckPass;
    private int qualityCheckFail;
    private int securityEvent;
    private int qualityCheckUnmodified;
    private int statusCode;
    private Timestamp createTime;

    public long getQualityId() {
        return qualityId;
    }

    public void setQualityId(long qualityId) {
        this.qualityId = qualityId;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getQualityCheck() {
        return qualityCheck;
    }

    public void setQualityCheck(int qualityCheck) {
        this.qualityCheck = qualityCheck;
    }

    public int getQualityCheckPass() {
        return qualityCheckPass;
    }

    public void setQualityCheckPass(int qualityCheckPass) {
        this.qualityCheckPass = qualityCheckPass;
    }

    public int getQualityCheckFail() {
        return qualityCheckFail;
    }

    public void setQualityCheckFail(int qualityCheckFail) {
        this.qualityCheckFail = qualityCheckFail;
    }

    public int getSecurityEvent() {
        return securityEvent;
    }

    public void setSecurityEvent(int securityEvent) {
        this.securityEvent = securityEvent;
    }

    public int getQualityCheckUnmodified() {
        return qualityCheckUnmodified;
    }

    public void setQualityCheckUnmodified(int qualityCheckUnmodified) {
        this.qualityCheckUnmodified = qualityCheckUnmodified;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
