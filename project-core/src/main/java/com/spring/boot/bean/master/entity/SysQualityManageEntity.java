package com.spring.boot.bean.master.entity;

import java.sql.Timestamp;
import java.util.Map;

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

    //合格率(月)
    private  Map<Integer, Double> checkPassScaleMap;
    //不合格率(月)
    private  Map<Integer, Double> modifiedPassScaleMap;


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

    public Map<Integer, Double> getCheckPassScaleMap() {
        return checkPassScaleMap;
    }

    public void setCheckPassScaleMap(Map<Integer, Double> checkPassScaleMap) {
        this.checkPassScaleMap = checkPassScaleMap;
    }

    public Map<Integer, Double> getModifiedPassScaleMap() {
        return modifiedPassScaleMap;
    }

    public void setModifiedPassScaleMap(Map<Integer, Double> modifiedPassScaleMap) {
        this.modifiedPassScaleMap = modifiedPassScaleMap;
    }
}
