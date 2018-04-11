package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/20.
 */
public class SysQualityManage {
    private Long qualityId;
    private Long companyId;
    private Integer year;
    private Integer month;
    private Integer qualityCheck;
    private Integer qualityCheckPass;
    private Integer qualityCheckFail;
    private Integer securityEvent;
    private Integer qualityCheckUnmodified;
    private Integer statusCode;
    private Date createTime;

    private String companyName;

    private Integer fileNum;

    public Long getQualityId() {
        return qualityId;
    }

    public void setQualityId(Long qualityId) {
        this.qualityId = qualityId;
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

    public Integer getQualityCheck() {
        return qualityCheck;
    }

    public void setQualityCheck(Integer qualityCheck) {
        this.qualityCheck = qualityCheck;
    }

    public Integer getQualityCheckPass() {
        return qualityCheckPass;
    }

    public void setQualityCheckPass(Integer qualityCheckPass) {
        this.qualityCheckPass = qualityCheckPass;
    }

    public Integer getQualityCheckFail() {
        return qualityCheckFail;
    }

    public void setQualityCheckFail(Integer qualityCheckFail) {
        this.qualityCheckFail = qualityCheckFail;
    }

    public Integer getSecurityEvent() {
        return securityEvent;
    }

    public void setSecurityEvent(Integer securityEvent) {
        this.securityEvent = securityEvent;
    }

    public Integer getQualityCheckUnmodified() {
        return qualityCheckUnmodified;
    }

    public void setQualityCheckUnmodified(Integer qualityCheckUnmodified) {
        this.qualityCheckUnmodified = qualityCheckUnmodified;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getFileNum() {
        return fileNum;
    }

    public void setFileNum(Integer fileNum) {
        this.fileNum = fileNum;
    }
}
