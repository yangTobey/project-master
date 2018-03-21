package com.spring.boot.bean.master.entity;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/3/20.
 */
public class SysQualityManageEntity {
    private long qualityId;
    private int qualityCheck;
    private int qualityCheckPass;
    private int qualityCheckFail;
    private int securityEvent;
    private int qualityCheckUnmodified;
    //检查合格率
    private double qualityCheckPassScale;
    //整改合格率
    private double modifiedPassScale;
    //一月份合格率
    private double checkPassScaleJan;
    //二月份合格率
    private double checkPassScaleFeb;
    //三月份合格率
    private double checkPassScaleMar;
    //四月份合格率
    private double checkPassScaleApr;
    //五月份合格率
    private double checkPassScaleMay;
    //六月份合格率
    private double checkPassScaleJune;
    //七月份合格率
    private double checkPassScaleJuly;
    //八月份合格率
    private double checkPassScaleAug;
    //九月份合格率
    private double checkPassScaleSept;
    //十月份合格率
    private double checkPassScaleOct;
    //十一月份合格率
    private double checkPassScaleNov;
    //十二月份合格率
    private double checkPassScaleDec;

    //一月份整改合格率
    private double modifiedPassScaleJan;
    //二月份整改合格率
    private double modifiedPassScaleFeb;
    //三月份整改合格率
    private double modifiedPassScaleMar;
    //四月份整改合格率
    private double modifiedPassScaleApr;
    //五月份整改合格率
    private double modifiedPassScaleMay;
    //六月份整改合格率
    private double modifiedPassScaleJune;
    //七月份整改合格率
    private double modifiedPassScaleJuly;
    //八月份整改合格率
    private double modifiedPassScaleAug;
    //九月份整改合格率
    private double modifiedPassScaleSept;
    //十月份整改合格率
    private double modifiedPassScaleOct;
    //十一月份整改合格率
    private double modifiedPassScaleNov;
    //十二月份整改合格率
    private double modifiedPassScaleDec;


    public long getQualityId() {
        return qualityId;
    }

    public void setQualityId(long qualityId) {
        this.qualityId = qualityId;
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

    public double getQualityCheckPassScale() {
        return qualityCheckPassScale;
    }

    public void setQualityCheckPassScale(double qualityCheckPassScale) {
        this.qualityCheckPassScale = qualityCheckPassScale;
    }

    public double getModifiedPassScale() {
        return modifiedPassScale;
    }

    public void setModifiedPassScale(double modifiedPassScale) {
        this.modifiedPassScale = modifiedPassScale;
    }

    public double getCheckPassScaleJan() {
        return checkPassScaleJan;
    }

    public void setCheckPassScaleJan(double checkPassScaleJan) {
        this.checkPassScaleJan = checkPassScaleJan;
    }

    public double getCheckPassScaleFeb() {
        return checkPassScaleFeb;
    }

    public void setCheckPassScaleFeb(double checkPassScaleFeb) {
        this.checkPassScaleFeb = checkPassScaleFeb;
    }

    public double getCheckPassScaleMar() {
        return checkPassScaleMar;
    }

    public void setCheckPassScaleMar(double checkPassScaleMar) {
        this.checkPassScaleMar = checkPassScaleMar;
    }

    public double getCheckPassScaleApr() {
        return checkPassScaleApr;
    }

    public void setCheckPassScaleApr(double checkPassScaleApr) {
        this.checkPassScaleApr = checkPassScaleApr;
    }

    public double getCheckPassScaleMay() {
        return checkPassScaleMay;
    }

    public void setCheckPassScaleMay(double checkPassScaleMay) {
        this.checkPassScaleMay = checkPassScaleMay;
    }

    public double getCheckPassScaleJune() {
        return checkPassScaleJune;
    }

    public void setCheckPassScaleJune(double checkPassScaleJune) {
        this.checkPassScaleJune = checkPassScaleJune;
    }

    public double getCheckPassScaleJuly() {
        return checkPassScaleJuly;
    }

    public void setCheckPassScaleJuly(double checkPassScaleJuly) {
        this.checkPassScaleJuly = checkPassScaleJuly;
    }

    public double getCheckPassScaleAug() {
        return checkPassScaleAug;
    }

    public void setCheckPassScaleAug(double checkPassScaleAug) {
        this.checkPassScaleAug = checkPassScaleAug;
    }

    public double getCheckPassScaleSept() {
        return checkPassScaleSept;
    }

    public void setCheckPassScaleSept(double checkPassScaleSept) {
        this.checkPassScaleSept = checkPassScaleSept;
    }

    public double getCheckPassScaleOct() {
        return checkPassScaleOct;
    }

    public void setCheckPassScaleOct(double checkPassScaleOct) {
        this.checkPassScaleOct = checkPassScaleOct;
    }

    public double getCheckPassScaleNov() {
        return checkPassScaleNov;
    }

    public void setCheckPassScaleNov(double checkPassScaleNov) {
        this.checkPassScaleNov = checkPassScaleNov;
    }

    public double getCheckPassScaleDec() {
        return checkPassScaleDec;
    }

    public void setCheckPassScaleDec(double checkPassScaleDec) {
        this.checkPassScaleDec = checkPassScaleDec;
    }

    public double getModifiedPassScaleJan() {
        return modifiedPassScaleJan;
    }

    public void setModifiedPassScaleJan(double modifiedPassScaleJan) {
        this.modifiedPassScaleJan = modifiedPassScaleJan;
    }

    public double getModifiedPassScaleFeb() {
        return modifiedPassScaleFeb;
    }

    public void setModifiedPassScaleFeb(double modifiedPassScaleFeb) {
        this.modifiedPassScaleFeb = modifiedPassScaleFeb;
    }

    public double getModifiedPassScaleMar() {
        return modifiedPassScaleMar;
    }

    public void setModifiedPassScaleMar(double modifiedPassScaleMar) {
        this.modifiedPassScaleMar = modifiedPassScaleMar;
    }

    public double getModifiedPassScaleApr() {
        return modifiedPassScaleApr;
    }

    public void setModifiedPassScaleApr(double modifiedPassScaleApr) {
        this.modifiedPassScaleApr = modifiedPassScaleApr;
    }

    public double getModifiedPassScaleMay() {
        return modifiedPassScaleMay;
    }

    public void setModifiedPassScaleMay(double modifiedPassScaleMay) {
        this.modifiedPassScaleMay = modifiedPassScaleMay;
    }

    public double getModifiedPassScaleJune() {
        return modifiedPassScaleJune;
    }

    public void setModifiedPassScaleJune(double modifiedPassScaleJune) {
        this.modifiedPassScaleJune = modifiedPassScaleJune;
    }

    public double getModifiedPassScaleJuly() {
        return modifiedPassScaleJuly;
    }

    public void setModifiedPassScaleJuly(double modifiedPassScaleJuly) {
        this.modifiedPassScaleJuly = modifiedPassScaleJuly;
    }

    public double getModifiedPassScaleAug() {
        return modifiedPassScaleAug;
    }

    public void setModifiedPassScaleAug(double modifiedPassScaleAug) {
        this.modifiedPassScaleAug = modifiedPassScaleAug;
    }

    public double getModifiedPassScaleSept() {
        return modifiedPassScaleSept;
    }

    public void setModifiedPassScaleSept(double modifiedPassScaleSept) {
        this.modifiedPassScaleSept = modifiedPassScaleSept;
    }

    public double getModifiedPassScaleOct() {
        return modifiedPassScaleOct;
    }

    public void setModifiedPassScaleOct(double modifiedPassScaleOct) {
        this.modifiedPassScaleOct = modifiedPassScaleOct;
    }

    public double getModifiedPassScaleNov() {
        return modifiedPassScaleNov;
    }

    public void setModifiedPassScaleNov(double modifiedPassScaleNov) {
        this.modifiedPassScaleNov = modifiedPassScaleNov;
    }

    public double getModifiedPassScaleDec() {
        return modifiedPassScaleDec;
    }

    public void setModifiedPassScaleDec(double modifiedPassScaleDec) {
        this.modifiedPassScaleDec = modifiedPassScaleDec;
    }
}
