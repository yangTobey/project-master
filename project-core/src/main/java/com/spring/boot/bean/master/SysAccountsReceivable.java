package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.boot.validation.IsNotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2018/3/2.
 */
public class SysAccountsReceivable implements Serializable {
    private static final long serialVersionUID = -4315712891689302261L;
    private Long accountsId;
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
    @NotNull(message = "月份不能为空！")
    @Range(min=1, max=12, message = "月份格式错误")
    private Integer month;
    /**
     * 业主应收款
     */
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "业主应收款格式不正确，或者不符合常理！！")
    @IsNotNull(message = "业主应收款格式不正确，只能保留两位小数，或者不符合常理(数值过大)！")
    private Double receivableAccountsOwner;

    /**
     * 业主已收款
     */
    @NotNull(message = "业主已收款不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "业主已收款格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "业主已收款格式不正确，或者不符合常理！！")
    private Double completeAccountsOwner;

    /**
     * 礼券减免（已收）
     */
    @NotNull(message = "礼券减免（已收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "礼券减免（已收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "礼券减免（已收）格式不正确，或者不符合常理！！")
    private Double completeCoupon;
    /**
     * 礼券减免（应收）
     */
    @NotNull(message = "礼券减免（应收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "礼券减免（应收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "礼券减免（应收）格式不正确，或者不符合常理！！")
    private Double receivableCoupon;
    /**
     * 空置（已收）
     */
    @NotNull(message = "空置（已收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "空置（已收）格式不正确，数值过大或者过小")
    @Digits(integer =Integer.MAX_VALUE,fraction = 2,message = "空置（已收）格式不正确，或者不符合常理！！")
    private Double completeVacancy;
    /**
     * 空置（应收）
     */
    @NotNull(message = "空置（应收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "空置（应收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "空置（应收）格式不正确，或者不符合常理！！")
    private Double receivableVacancy;
    /**
     * %3补贴款（已收）
     */
    @NotNull(message = "%3补贴款（已收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "%3补贴款（已收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "%3补贴款（已收）格式不正确，或者不符合常理！！")
    private Double completeSubsidy;
    /**
     * %3补贴款（应收）
     */
    @NotNull(message = "%3补贴款（应收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "%3补贴款（应收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "%3补贴款（应收）格式不正确，或者不符合常理！！")
    private Double receivableSubsidy;
    /**
     * 销售配合（已收)
     */
    @NotNull(message = "销售配合（已收)不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "销售配合（已收)格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "销售配合（已收)格式不正确，或者不符合常理！！")
    private Double completeSales;
    /**
     * 销售配合（应收)
     */
    @NotNull(message = "销售配合（应收)不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "销售配合（应收)格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "销售配合（应收)格式不正确，或者不符合常理！！")
    private Double receivableSales;
    /**
     * 开办费（应收）
     */
    @NotNull(message = "开办费（应收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "开办费（应收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "开办费（应收）格式不正确，或者不符合常理！！")
    private Double receivableOpen;
    /**
     * 开办费（已收）
     */
    @NotNull(message = "开办费（已收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "开办费（已收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "开办费（已收）格式不正确，或者不符合常理！！")
    private Double completeOpen;
    /**
     * 物业补贴（已收）
     */
    @NotNull(message = "物业补贴（已收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "物业补贴（已收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "物业补贴（已收）格式不正确，或者不符合常理！！")
    private Double completePropertySubsidy;
    /**
     * 物业补贴（应收）
     */
    @NotNull(message = "物业补贴（应收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "物业补贴（应收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "物业补贴（应收）格式不正确，或者不符合常理！！")
    private Double receivablePropertySubsidy;
    /**
     * 其他地产应付款（已收）
     */
    @NotNull(message = "其他地产应付款（已收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "其他地产应付款（已收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "其他地产应付款（已收）格式不正确，或者不符合常理！！")
    private Double completeHouseOther;
    /**
     * 其他地产应付款（应收）
     */
    @NotNull(message = "其他地产应付款（应收）不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "其他地产应付款（应收）格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "其他地产应付款（应收）格式不正确，或者不符合常理！！")
    private Double receivableHouseOther;
    /**
     * 地产已收款总数
     */
    @NotNull(message = "地产已收款总数不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "地产已收款总数格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "地产已收款总数格式不正确，或者不符合常理！！")
    private Double completeHouse;
    /**
     * 地产应收款总数
     */
    @NotNull(message = "地产应收款总数不能为空！")
    @Range(min = Integer.MIN_VALUE,max = Integer.MAX_VALUE,message = "地产应收款总数格式不正确，数值过大或者过小")
    @Digits(integer = Integer.MAX_VALUE,fraction = 2,message = "地产应收款总数格式不正确，或者不符合常理！！")
    private Double receivableHouse;

    private Date createTime;
    private Integer statusCode;
    @NotBlank(message = "公司名称不能为空")
    private String companyName;

    //礼券减免收缴率
    private Double couponScale;
    //空置收缴率
    private Double vacancyScale;
    //%3补贴收缴率
    private Double subsidyScale;
    //销售配合收缴率
    private Double salesScale;
    //开办费收缴率
    private Double openScale;
    //物业补贴收缴率
    private Double propertySubsidyScale;
    //其他地产收缴率
    private Double houseOtherScale;

    public SysAccountsReceivable(){

    }
    public SysAccountsReceivable(Double receivableAccountsOwner,Double completeAccountsOwner,Double completeCoupon,Double receivableCoupon,Double completeVacancy,
                                 Double receivableVacancy,Double completeSubsidy,Double receivableSubsidy,Double completeSales,Double receivableSales,Double receivableOpen,
                                 Double completeOpen,Double completePropertySubsidy,Double receivablePropertySubsidy,Double completeHouseOther,Double receivableHouseOther,
                                 Double completeHouse,Double receivableHouse){
        this.receivableAccountsOwner=receivableAccountsOwner;
        this.completeAccountsOwner=completeAccountsOwner;
        this.completeCoupon=completeCoupon;
        this.receivableCoupon=receivableCoupon;
        this.completeVacancy=completeVacancy;
        this.receivableVacancy=receivableVacancy;
        this.completeSubsidy=completeSubsidy;
        this.receivableSubsidy=receivableSubsidy;
        this.completeSales=completeSales;
        this.receivableSales=receivableSales;
        this.receivableOpen=receivableOpen;
        this.completeOpen=completeOpen;
        this.completePropertySubsidy=completePropertySubsidy;
        this.receivablePropertySubsidy=receivablePropertySubsidy;
        this.completeHouseOther=completeHouseOther;
        this.receivableHouseOther=receivableHouseOther;
        this.completeHouse=completeHouse;
        this.receivableHouse=receivableHouse;

    }

    public Long getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(Long accountsId) {
        this.accountsId = accountsId;
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

    public Double getReceivableAccountsOwner() {
        return receivableAccountsOwner;
    }

    public void setReceivableAccountsOwner(Double receivableAccountsOwner) {
        this.receivableAccountsOwner = receivableAccountsOwner;
    }

    public Double getCompleteAccountsOwner() {
        return completeAccountsOwner;
    }

    public void setCompleteAccountsOwner(Double completeAccountsOwner) {
        this.completeAccountsOwner = completeAccountsOwner;
    }

    public Double getCompleteCoupon() {
        return completeCoupon;
    }

    public void setCompleteCoupon(Double completeCoupon) {
        this.completeCoupon = completeCoupon;
    }

    public Double getReceivableCoupon() {
        return receivableCoupon;
    }

    public void setReceivableCoupon(Double receivableCoupon) {
        this.receivableCoupon = receivableCoupon;
    }

    public Double getCompleteVacancy() {
        return completeVacancy;
    }

    public void setCompleteVacancy(Double completeVacancy) {
        this.completeVacancy = completeVacancy;
    }

    public Double getReceivableVacancy() {
        return receivableVacancy;
    }

    public void setReceivableVacancy(Double receivableVacancy) {
        this.receivableVacancy = receivableVacancy;
    }

    public Double getCompleteSubsidy() {
        return completeSubsidy;
    }

    public void setCompleteSubsidy(Double completeSubsidy) {
        this.completeSubsidy = completeSubsidy;
    }

    public Double getReceivableSubsidy() {
        return receivableSubsidy;
    }

    public void setReceivableSubsidy(Double receivableSubsidy) {
        this.receivableSubsidy = receivableSubsidy;
    }

    public Double getCompleteSales() {
        return completeSales;
    }

    public void setCompleteSales(Double completeSales) {
        this.completeSales = completeSales;
    }

    public Double getReceivableSales() {
        return receivableSales;
    }

    public void setReceivableSales(Double receivableSales) {
        this.receivableSales = receivableSales;
    }

    public Double getReceivableOpen() {
        return receivableOpen;
    }

    public void setReceivableOpen(Double receivableOpen) {
        this.receivableOpen = receivableOpen;
    }

    public Double getCompleteOpen() {
        return completeOpen;
    }

    public void setCompleteOpen(Double completeOpen) {
        this.completeOpen = completeOpen;
    }

    public Double getCompletePropertySubsidy() {
        return completePropertySubsidy;
    }

    public void setCompletePropertySubsidy(Double completePropertySubsidy) {
        this.completePropertySubsidy = completePropertySubsidy;
    }

    public Double getReceivablePropertySubsidy() {
        return receivablePropertySubsidy;
    }

    public void setReceivablePropertySubsidy(Double receivablePropertySubsidy) {
        this.receivablePropertySubsidy = receivablePropertySubsidy;
    }

    public Double getCompleteHouseOther() {
        return completeHouseOther;
    }

    public void setCompleteHouseOther(Double completeHouseOther) {
        this.completeHouseOther = completeHouseOther;
    }

    public Double getReceivableHouseOther() {
        return receivableHouseOther;
    }

    public void setReceivableHouseOther(Double receivableHouseOther) {
        this.receivableHouseOther = receivableHouseOther;
    }

    public Double getCompleteHouse() {
        return completeHouse;
    }

    public void setCompleteHouse(Double completeHouse) {
        this.completeHouse = completeHouse;
    }

    public Double getReceivableHouse() {
        return receivableHouse;
    }

    public void setReceivableHouse(Double receivableHouse) {
        this.receivableHouse = receivableHouse;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Double getCouponScale() {
        return couponScale;
    }

    public void setCouponScale(Double couponScale) {
        this.couponScale = couponScale;
    }

    public Double getVacancyScale() {
        return vacancyScale;
    }

    public void setVacancyScale(Double vacancyScale) {
        this.vacancyScale = vacancyScale;
    }

    public Double getSubsidyScale() {
        return subsidyScale;
    }

    public void setSubsidyScale(Double subsidyScale) {
        this.subsidyScale = subsidyScale;
    }

    public Double getSalesScale() {
        return salesScale;
    }

    public void setSalesScale(Double salesScale) {
        this.salesScale = salesScale;
    }

    public Double getOpenScale() {
        return openScale;
    }

    public void setOpenScale(Double openScale) {
        this.openScale = openScale;
    }

    public Double getPropertySubsidyScale() {
        return propertySubsidyScale;
    }

    public void setPropertySubsidyScale(Double propertySubsidyScale) {
        this.propertySubsidyScale = propertySubsidyScale;
    }

    public Double getHouseOtherScale() {
        return houseOtherScale;
    }

    public void setHouseOtherScale(Double houseOtherScale) {
        this.houseOtherScale = houseOtherScale;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
