package com.spring.boot.bean.master.entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/20.
 */
public class SysQualityManageEntity {
    private Long qualityId;
    private Integer qualityCheck;
    private Integer qualityCheckPass;
    private Integer qualityCheckFail;
    private Integer securityEvent;
    private Integer qualityCheckUnmodified;
    //检查合格率
    private Double qualityCheckPassScale;
    //整改合格率
    private Double modifiedPassScale;
    //一月份合格率
    private Double checkPassScaleJan;
    //二月份合格率
    private Double checkPassScaleFeb;
    //三月份合格率
    private Double checkPassScaleMar;
    //四月份合格率
    private Double checkPassScaleApr;
    //五月份合格率
    private Double checkPassScaleMay;
    //六月份合格率
    private Double checkPassScaleJune;
    //七月份合格率
    private Double checkPassScaleJuly;
    //八月份合格率
    private Double checkPassScaleAug;
    //九月份合格率
    private Double checkPassScaleSept;
    //十月份合格率
    private Double checkPassScaleOct;
    //十一月份合格率
    private Double checkPassScaleNov;
    //十二月份合格率
    private Double checkPassScaleDec;

    //一月份整改合格率
    private Double modifiedPassScaleJan;
    //二月份整改合格率
    private Double modifiedPassScaleFeb;
    //三月份整改合格率
    private Double modifiedPassScaleMar;
    //四月份整改合格率
    private Double modifiedPassScaleApr;
    //五月份整改合格率
    private Double modifiedPassScaleMay;
    //六月份整改合格率
    private Double modifiedPassScaleJune;
    //七月份整改合格率
    private Double modifiedPassScaleJuly;
    //八月份整改合格率
    private Double modifiedPassScaleAug;
    //九月份整改合格率
    private Double modifiedPassScaleSept;
    //十月份整改合格率
    private Double modifiedPassScaleOct;
    //十一月份整改合格率
    private Double modifiedPassScaleNov;
    //十二月份整改合格率
    private Double modifiedPassScaleDec;

    public Long getQualityId() {
        return qualityId;
    }

    public void setQualityId(Long qualityId) {
        this.qualityId = qualityId;
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

    public Double getQualityCheckPassScale() {
        return qualityCheckPassScale;
    }

    public void setQualityCheckPassScale(Double qualityCheckPassScale) {
        this.qualityCheckPassScale = qualityCheckPassScale;
    }

    public Double getModifiedPassScale() {
        return modifiedPassScale;
    }

    public void setModifiedPassScale(Double modifiedPassScale) {
        this.modifiedPassScale = modifiedPassScale;
    }

    public Double getCheckPassScaleJan() {
        return checkPassScaleJan;
    }

    public void setCheckPassScaleJan(Double checkPassScaleJan) {
        this.checkPassScaleJan = checkPassScaleJan;
    }

    public Double getCheckPassScaleFeb() {
        return checkPassScaleFeb;
    }

    public void setCheckPassScaleFeb(Double checkPassScaleFeb) {
        this.checkPassScaleFeb = checkPassScaleFeb;
    }

    public Double getCheckPassScaleMar() {
        return checkPassScaleMar;
    }

    public void setCheckPassScaleMar(Double checkPassScaleMar) {
        this.checkPassScaleMar = checkPassScaleMar;
    }

    public Double getCheckPassScaleApr() {
        return checkPassScaleApr;
    }

    public void setCheckPassScaleApr(Double checkPassScaleApr) {
        this.checkPassScaleApr = checkPassScaleApr;
    }

    public Double getCheckPassScaleMay() {
        return checkPassScaleMay;
    }

    public void setCheckPassScaleMay(Double checkPassScaleMay) {
        this.checkPassScaleMay = checkPassScaleMay;
    }

    public Double getCheckPassScaleJune() {
        return checkPassScaleJune;
    }

    public void setCheckPassScaleJune(Double checkPassScaleJune) {
        this.checkPassScaleJune = checkPassScaleJune;
    }

    public Double getCheckPassScaleJuly() {
        return checkPassScaleJuly;
    }

    public void setCheckPassScaleJuly(Double checkPassScaleJuly) {
        this.checkPassScaleJuly = checkPassScaleJuly;
    }

    public Double getCheckPassScaleAug() {
        return checkPassScaleAug;
    }

    public void setCheckPassScaleAug(Double checkPassScaleAug) {
        this.checkPassScaleAug = checkPassScaleAug;
    }

    public Double getCheckPassScaleSept() {
        return checkPassScaleSept;
    }

    public void setCheckPassScaleSept(Double checkPassScaleSept) {
        this.checkPassScaleSept = checkPassScaleSept;
    }

    public Double getCheckPassScaleOct() {
        return checkPassScaleOct;
    }

    public void setCheckPassScaleOct(Double checkPassScaleOct) {
        this.checkPassScaleOct = checkPassScaleOct;
    }

    public Double getCheckPassScaleNov() {
        return checkPassScaleNov;
    }

    public void setCheckPassScaleNov(Double checkPassScaleNov) {
        this.checkPassScaleNov = checkPassScaleNov;
    }

    public Double getCheckPassScaleDec() {
        return checkPassScaleDec;
    }

    public void setCheckPassScaleDec(Double checkPassScaleDec) {
        this.checkPassScaleDec = checkPassScaleDec;
    }

    public Double getModifiedPassScaleJan() {
        return modifiedPassScaleJan;
    }

    public void setModifiedPassScaleJan(Double modifiedPassScaleJan) {
        this.modifiedPassScaleJan = modifiedPassScaleJan;
    }

    public Double getModifiedPassScaleFeb() {
        return modifiedPassScaleFeb;
    }

    public void setModifiedPassScaleFeb(Double modifiedPassScaleFeb) {
        this.modifiedPassScaleFeb = modifiedPassScaleFeb;
    }

    public Double getModifiedPassScaleMar() {
        return modifiedPassScaleMar;
    }

    public void setModifiedPassScaleMar(Double modifiedPassScaleMar) {
        this.modifiedPassScaleMar = modifiedPassScaleMar;
    }

    public Double getModifiedPassScaleApr() {
        return modifiedPassScaleApr;
    }

    public void setModifiedPassScaleApr(Double modifiedPassScaleApr) {
        this.modifiedPassScaleApr = modifiedPassScaleApr;
    }

    public Double getModifiedPassScaleMay() {
        return modifiedPassScaleMay;
    }

    public void setModifiedPassScaleMay(Double modifiedPassScaleMay) {
        this.modifiedPassScaleMay = modifiedPassScaleMay;
    }

    public Double getModifiedPassScaleJune() {
        return modifiedPassScaleJune;
    }

    public void setModifiedPassScaleJune(Double modifiedPassScaleJune) {
        this.modifiedPassScaleJune = modifiedPassScaleJune;
    }

    public Double getModifiedPassScaleJuly() {
        return modifiedPassScaleJuly;
    }

    public void setModifiedPassScaleJuly(Double modifiedPassScaleJuly) {
        this.modifiedPassScaleJuly = modifiedPassScaleJuly;
    }

    public Double getModifiedPassScaleAug() {
        return modifiedPassScaleAug;
    }

    public void setModifiedPassScaleAug(Double modifiedPassScaleAug) {
        this.modifiedPassScaleAug = modifiedPassScaleAug;
    }

    public Double getModifiedPassScaleSept() {
        return modifiedPassScaleSept;
    }

    public void setModifiedPassScaleSept(Double modifiedPassScaleSept) {
        this.modifiedPassScaleSept = modifiedPassScaleSept;
    }

    public Double getModifiedPassScaleOct() {
        return modifiedPassScaleOct;
    }

    public void setModifiedPassScaleOct(Double modifiedPassScaleOct) {
        this.modifiedPassScaleOct = modifiedPassScaleOct;
    }

    public Double getModifiedPassScaleNov() {
        return modifiedPassScaleNov;
    }

    public void setModifiedPassScaleNov(Double modifiedPassScaleNov) {
        this.modifiedPassScaleNov = modifiedPassScaleNov;
    }

    public Double getModifiedPassScaleDec() {
        return modifiedPassScaleDec;
    }

    public void setModifiedPassScaleDec(Double modifiedPassScaleDec) {
        this.modifiedPassScaleDec = modifiedPassScaleDec;
    }
}
