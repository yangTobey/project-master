package com.spring.boot.bean.master.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.boot.bean.master.SysBasicDataFile;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * 基础数据
 * Created by Administrator on 2018/3/16.
 */
public class SysBasicDataEntity implements Serializable {

    private static final long serialVersionUID = 7117841154335878387L;

    private Long basicId;
    private Long companyId;
    private Integer year;
    private Integer month;
    private Double constructionArea;
    private Double chargeArea;
    private Integer cityNumber;
    private Integer projectNumber;
    private Integer houseNumber;
    private Integer acceptHouseNumber;
    private Integer forSaleHouseNumber;
    private Integer decorateHouseNumber;
    private Integer parkingSpace;
    private Integer forSaleParkingSpace;
    private Integer salesDistribution;
    private Integer statusCode;
    private Timestamp createTime;
    private Integer subsidiaryCount;//分公司总数
    List<SysBasicDataFile> fileList;

    private Double forSaleHouseScale;//房屋空置率(转化后保留两位小数)
    //已售房屋数（入住）率(转化后保留两位小数)
    private Double acceptHouseNumberScale;
    private Double decorateHouseScale;//房屋装修率(转化后保留两位小数)
    private Double forSaleParkingSpaceScale;//车位空置率(转化后保留两位小数)
    private String companyName;

    public Long getBasicId() {
        return basicId;
    }

    public void setBasicId(Long basicId) {
        this.basicId = basicId;
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

    public Double getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(Double constructionArea) {
        this.constructionArea = constructionArea;
    }

    public Double getChargeArea() {
        return chargeArea;
    }

    public void setChargeArea(Double chargeArea) {
        this.chargeArea = chargeArea;
    }

    public Integer getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(Integer cityNumber) {
        this.cityNumber = cityNumber;
    }

    public Integer getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(Integer projectNumber) {
        this.projectNumber = projectNumber;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Integer getAcceptHouseNumber() {
        return acceptHouseNumber;
    }

    public void setAcceptHouseNumber(Integer acceptHouseNumber) {
        this.acceptHouseNumber = acceptHouseNumber;
    }

    public Integer getForSaleHouseNumber() {
        return forSaleHouseNumber;
    }

    public void setForSaleHouseNumber(Integer forSaleHouseNumber) {
        this.forSaleHouseNumber = forSaleHouseNumber;
    }

    public Integer getDecorateHouseNumber() {
        return decorateHouseNumber;
    }

    public void setDecorateHouseNumber(Integer decorateHouseNumber) {
        this.decorateHouseNumber = decorateHouseNumber;
    }

    public Integer getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(Integer parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Integer getForSaleParkingSpace() {
        return forSaleParkingSpace;
    }

    public void setForSaleParkingSpace(Integer forSaleParkingSpace) {
        this.forSaleParkingSpace = forSaleParkingSpace;
    }

    public Integer getSalesDistribution() {
        return salesDistribution;
    }

    public void setSalesDistribution(Integer salesDistribution) {
        this.salesDistribution = salesDistribution;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getSubsidiaryCount() {
        return subsidiaryCount;
    }

    public void setSubsidiaryCount(Integer subsidiaryCount) {
        this.subsidiaryCount = subsidiaryCount;
    }

    public Double getAcceptHouseNumberScale() {
        return acceptHouseNumberScale;
    }

    public void setAcceptHouseNumberScale(Double acceptHouseNumberScale) {
        this.acceptHouseNumberScale = acceptHouseNumberScale;
    }

    public Double getForSaleHouseScale() {
        return forSaleHouseScale;
    }

    public void setForSaleHouseScale(Double forSaleHouseScale) {
        this.forSaleHouseScale = forSaleHouseScale;
    }

    public Double getDecorateHouseScale() {
        return decorateHouseScale;
    }

    public void setDecorateHouseScale(Double decorateHouseScale) {
        this.decorateHouseScale = decorateHouseScale;
    }

    public Double getForSaleParkingSpaceScale() {
        return forSaleParkingSpaceScale;
    }

    public void setForSaleParkingSpaceScale(Double forSaleParkingSpaceScale) {
        this.forSaleParkingSpaceScale = forSaleParkingSpaceScale;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<SysBasicDataFile> getFileList() {
        return fileList;
    }

    public void setFileList(List<SysBasicDataFile> fileList) {
        this.fileList = fileList;
    }
}


