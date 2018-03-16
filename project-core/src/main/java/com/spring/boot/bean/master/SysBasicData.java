package com.spring.boot.bean.master;

/**
 * 基础数据实体类
 * Created by Administrator on 2018/3/16.
 */
public class SysBasicData {

    private long basicId;
    private long companyId;
    private int year;
    private int month;
    private double constructionArea;
    private double chargeArea;
    private int cityNumber;
    private int projectNumber;
    private int houseNumber;
    private int acceptHouseNumber;
    private int forSaleHouseNumber;
    private int decorateHouseNumber;
    private int parkingSpace;
    private int forSaleParkingSpace;
    private int salesDistribution;
    private int statusCode;

    public long getBasicId() {
        return basicId;
    }

    public void setBasicId(long basicId) {
        this.basicId = basicId;
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

    public double getConstructionArea() {
        return constructionArea;
    }

    public void setConstructionArea(double constructionArea) {
        this.constructionArea = constructionArea;
    }

    public double getChargeArea() {
        return chargeArea;
    }

    public void setChargeArea(double chargeArea) {
        this.chargeArea = chargeArea;
    }

    public int getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(int cityNumber) {
        this.cityNumber = cityNumber;
    }

    public int getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(int projectNumber) {
        this.projectNumber = projectNumber;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getAcceptHouseNumber() {
        return acceptHouseNumber;
    }

    public void setAcceptHouseNumber(int acceptHouseNumber) {
        this.acceptHouseNumber = acceptHouseNumber;
    }

    public int getForSaleHouseNumber() {
        return forSaleHouseNumber;
    }

    public void setForSaleHouseNumber(int forSaleHouseNumber) {
        this.forSaleHouseNumber = forSaleHouseNumber;
    }

    public int getDecorateHouseNumber() {
        return decorateHouseNumber;
    }

    public void setDecorateHouseNumber(int decorateHouseNumber) {
        this.decorateHouseNumber = decorateHouseNumber;
    }

    public int getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(int parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public int getForSaleParkingSpace() {
        return forSaleParkingSpace;
    }

    public void setForSaleParkingSpace(int forSaleParkingSpace) {
        this.forSaleParkingSpace = forSaleParkingSpace;
    }

    public int getSalesDistribution() {
        return salesDistribution;
    }

    public void setSalesDistribution(int salesDistribution) {
        this.salesDistribution = salesDistribution;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
