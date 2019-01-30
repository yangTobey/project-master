package com.spring.boot.entity;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by Yang on 2018/12/22.
 * 工程能耗实体，用于新增或者修改信息时作为参数实体
 */
public class SysProjectEnergyInfoEntity {
    /**
     * 主键
     */
    private Long projectId;
    /**
     * 工程遗留问题数量
     */
    @NotNull(message = "工程遗留问题数量不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "工程遗留问题数量格式不正确，或者不符合常理！！")
    private Integer projectUnfinishedTotal;
    /**
     * 已处理工程遗留问题数量
     */
    @NotNull(message = "已处理工程遗留问题数量不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "已处理工程遗留问题数量格式不正确，或者不符合常理！！")
    private Integer projectFinishedTotal;
    /**
     * 公司id
     */
    @NotNull(message = "公司id不能为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "公司id格式不正确，或者不符合常理！！")
    private Long companyId;
    /**
     * 年份
     */
    @NotNull(message = "年份不能为空！")
    @Range(message = "年份格式不正确，或者不符合常理！！")
    private Integer year;
    /**
     * 月份
     */
    @NotNull(message = "月份不能为空")
    @Range(min=1, max=12, message = "月份格式错误")
    private Integer month;
    /**
     * 月度耗电量（度）:
     */
    @NotNull(message = "月度耗电量（度）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "月度耗电量（度）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "月度耗电量（度）格式不正确，只能保留两位小数！！")
    private Double monthConsumptionElectricity;
    /**
     * 月度耗水量（吨）
     */
    @NotNull(message = "月度耗水量（吨）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "月度耗水量（吨）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "月度耗水量（吨）格式不正确，只能保留两位小数！！")
    private Double monthConsumptionWater;
    /**
     * 工程遗留问题数量文件
     */
    private String unfinishedFileInfo;
    /**
     * 已处理工程遗留问题数量文件
     */
    private String finishedFileInfo;

    /**
     * 往来文件
     */
    private String previousFileInfo;
    /**
     * 往来文件信息
     */
    private String previousFileMes;
    /**
     * 验收文件
     */
    private String acceptFileInfo;
    /**
     * 验收文件信息
     */
    private String acceptFileMes;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getProjectUnfinishedTotal() {
        return projectUnfinishedTotal;
    }

    public void setProjectUnfinishedTotal(Integer projectUnfinishedTotal) {
        this.projectUnfinishedTotal = projectUnfinishedTotal;
    }

    public Integer getProjectFinishedTotal() {
        return projectFinishedTotal;
    }

    public void setProjectFinishedTotal(Integer projectFinishedTotal) {
        this.projectFinishedTotal = projectFinishedTotal;
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

    public Double getMonthConsumptionElectricity() {
        return monthConsumptionElectricity;
    }

    public void setMonthConsumptionElectricity(Double monthConsumptionElectricity) {
        this.monthConsumptionElectricity = monthConsumptionElectricity;
    }

    public Double getMonthConsumptionWater() {
        return monthConsumptionWater;
    }

    public void setMonthConsumptionWater(Double monthConsumptionWater) {
        this.monthConsumptionWater = monthConsumptionWater;
    }

    public String getUnfinishedFileInfo() {
        return unfinishedFileInfo;
    }

    public void setUnfinishedFileInfo(String unfinishedFileInfo) {
        this.unfinishedFileInfo = unfinishedFileInfo;
    }

    public String getFinishedFileInfo() {
        return finishedFileInfo;
    }

    public void setFinishedFileInfo(String finishedFileInfo) {
        this.finishedFileInfo = finishedFileInfo;
    }

    public String getPreviousFileInfo() {
        return previousFileInfo;
    }

    public void setPreviousFileInfo(String previousFileInfo) {
        this.previousFileInfo = previousFileInfo;
    }

    public String getAcceptFileInfo() {
        return acceptFileInfo;
    }

    public void setAcceptFileInfo(String acceptFileInfo) {
        this.acceptFileInfo = acceptFileInfo;
    }

    public String getPreviousFileMes() {
        return previousFileMes;
    }

    public void setPreviousFileMes(String previousFileMes) {
        this.previousFileMes = previousFileMes;
    }

    public String getAcceptFileMes() {
        return acceptFileMes;
    }

    public void setAcceptFileMes(String acceptFileMes) {
        this.acceptFileMes = acceptFileMes;
    }
}
