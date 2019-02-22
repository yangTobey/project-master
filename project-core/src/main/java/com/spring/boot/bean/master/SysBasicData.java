package com.spring.boot.bean.master;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.boot.validation.IsNotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 基础数据实体类
 * Created by Administrator on 2018/3/16.
 */
@ApiModel(value="基础数据对象")
public class SysBasicData {
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long basicId;
    /**
     * 公司id
     */
    @ApiModelProperty(value = "公司id")
    @NotNull(message = "公司id不能为空")
    @Range(min = 1,max = Integer.MAX_VALUE,message = "公司id格式不正确，或者不符合常理！！")
    private Long companyId;
    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    @NotNull(message = "年份不能为空！")
    //@Size(min = 6,max = 18,message = "登录账号长度不符合要求！")
    @Range(message = "年份格式不正确，或者不符合常理！！")
    private Integer year;
    /**
     * 月份
     */
    @NotNull(message = "月份不能为空")
    @Range(min=1, max=12, message = "月份格式错误")
    private Integer month;
    /**
     * 建筑面积
     */
    /*@NotNull(message = "公司id格式不正确，或者不符合常理！！！")
    @Min(value = 0,message = "最小为0")*/
    @Range(min = 0,max = Integer.MAX_VALUE,message = "建筑面积格式不正确，或者不符合常理！！")
    @IsNotNull(message = "建筑面积格式不正确，只能保留两位小数，或者不符合常理(数值过大)！")
    private Double constructionArea;
    /**
     * 建筑收费面积
     */
    @Range(min = 0,max = Integer.MAX_VALUE,message = "建筑收费面积，或者不符合常理！！")
    @IsNotNull(message = "建筑收费面积格式不正确，只能保留两位小数，或者不符合常理(数值过大)！")
    private Double chargeArea;
    /**
     * 城市数量
     */
    @NotNull(message = "城市数量不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "城市数量格式不正确，或者不符合常理！！")
    private Integer cityNumber;
    /**
     * 项目数量
     */
    @NotNull(message = "项目数量不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "项目数量格式不正确，或者不符合常理！！")
    private Integer projectNumber;

    /**
     * 房屋数量
     */
    @NotNull(message = "房屋数量不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "房屋数量格式不正确，或者不符合常理！！")
    private Integer houseNumber;
    /**
     * 已收房屋数量
     */
    @NotNull(message = "已收房屋数量不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "已收房屋数量格式不正确，或者不符合常理！！")
    private Integer acceptHouseNumber;
    /**
     * 待售房屋数量（空置）
     */
    @NotNull(message = "待售房屋数量（空置）不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "待售房屋数量（空置格式不正确，或者不符合常理！！")
    private Integer forSaleHouseNumber;
    /**
     * 装修房屋数量
     */
    @NotNull(message = "装修房屋数量不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "装修房屋数量格式不正确，或者不符合常理！！")
    private Integer decorateHouseNumber;
    /**
     * 停车位总数量
     */
    @NotNull(message = "停车位总数量不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "停车位总数量格式不正确，或者不符合常理！！")
    private Integer parkingSpace;
    /**
     * 待售车位数量
     */
    @NotNull(message = "待售车位数量不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "待售车位数量格式不正确，或者不符合常理！！")
    private Integer forSaleParkingSpace;
    /**
     * 销配
     */
   /* @NotNull(message = "销配不能为空")
    @Range(min = 0,max = Integer.MAX_VALUE,message = "销配数量格式不正确，或者不符合常理！！")*/
    private Integer salesDistribution;
    /**
     * 状态码
     */
    private Integer statusCode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 车位文件
     */
    private String parkingSpaceFileInfo;
    /**
     * 销配文件
     */
    private String salesDistributionFileInfo;
    /**
     * 文件
     */
    private String constructionAreaFileInfo;
    /**
     * 前期数据文件
     */
    //@NotBlank(message = "前期数据文件不能为空！")
    private String earlyFileInfo;

    /**
     * 基础数据文件
     */
    private String basicFileInfo;
    /**
     * 项目名称
     */
    @NotBlank(message = "项目名称不能为空！")
    @Length(min = 0,max = 30,message = "项目名称长度必须要在{min}和{max}之间")
    private String projectName;
    /**
     * 销配名称
     */
    /*@NotBlank(message = "销配案场名称不能为空！")
    @Length(min = 0,max = 30,message = "销配案场名称长度必须要在{min}和{max}之间")*/
    //@Size(min=, max=) 验证对象（Array,Collection,Map,String）长度是否在给定的范围之内 
    private String salesDistributionName;

    /**
     * 备注
     */
    private String remark;

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
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getParkingSpaceFileInfo() {
        return parkingSpaceFileInfo;
    }

    public void setParkingSpaceFileInfo(String parkingSpaceFileInfo) {
        this.parkingSpaceFileInfo = parkingSpaceFileInfo;
    }

    public String getSalesDistributionFileInfo() {
        return salesDistributionFileInfo;
    }

    public void setSalesDistributionFileInfo(String salesDistributionFileInfo) {
        this.salesDistributionFileInfo = salesDistributionFileInfo;
    }

    public String getConstructionAreaFileInfo() {
        return constructionAreaFileInfo;
    }

    public void setConstructionAreaFileInfo(String constructionAreaFileInfo) {
        this.constructionAreaFileInfo = constructionAreaFileInfo;
    }

    public String getEarlyFileInfo() {
        return earlyFileInfo;
    }

    public void setEarlyFileInfo(String earlyFileInfo) {
        this.earlyFileInfo = earlyFileInfo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getSalesDistributionName() {
        return salesDistributionName;
    }

    public void setSalesDistributionName(String salesDistributionName) {
        this.salesDistributionName = salesDistributionName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBasicFileInfo() {
        return basicFileInfo;
    }

    public void setBasicFileInfo(String basicFileInfo) {
        this.basicFileInfo = basicFileInfo;
    }
}


