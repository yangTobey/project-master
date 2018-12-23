package com.spring.boot.entity;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * Created by Yang on 2018/12/22.
 */
public class SysBasicDataEntity {
    /**
     * 主键
     */
    private Long basicId;
    /**
     * 年份
     */
    //@NotNull(message = "登录账号不能为空！")
    //@Size(min = 6,max = 18,message = "登录账号长度不符合要求！")
    @Pattern(regexp = "[0-9]*",message = "年份格式不正确，或者存在非法字符")
    //https://www.aliyun.com/jiaocheng/243606.html，时间校验
    private Integer year;

    /**
     * 月份
     */
    //@NotNull(message = "登录密码不能为空")
    @Range(min=1, max=12, message = "月份格式错误")
    private Integer month;

    /**
     * 建筑面积
     */
    /*@NotNull(message = "公司id格式不正确，或者不符合常理！！！")
    @Min(value = 0,message = "最小为0")*/
    @Range(min = 1,max = Integer.MAX_VALUE,message = "公司id格式不正确，或者不符合常理！！")
    //@DecimalMin(value = "0.01",message = "单价最低为0.1")
    private Double constructionArea;

    /**
     * 建筑收费面积
     */
    @NotNull(message = "角色id格式不正确，或者不符合常理！！！")
    private Double chargeArea;
    /**
     * 城市数量
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "城市数量格式不正确，或者不符合常理！！")
    private Integer cityNumber;

    /**
     * 项目数量
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "项目数量格式不正确，或者不符合常理！！")
    private Integer projectNumber;

    /**
     * 房屋数量
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "房屋数量格式不正确，或者不符合常理！！")
    private Integer houseNumber;
    /**
     * 已收房屋数量
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "已收房屋数量格式不正确，或者不符合常理！！")
    private Integer acceptHouseNumber;
    /**
     * 待售房屋数量（空置）
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "待售房屋数量（空置格式不正确，或者不符合常理！！")
    private Integer forSaleHouseNumber;
    /**
     * 装修房屋数量
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "装修房屋数量格式不正确，或者不符合常理！！")
    private Integer decorateHouseNumber;
    /**
     * 停车位总数量
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "停车位总数量格式不正确，或者不符合常理！！")
    private Integer parkingSpace;
    /**
     * 待售车位数量
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "待售车位数量格式不正确，或者不符合常理！！")
    private Integer forSaleParkingSpace;
    /**
     * 销配
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "销配数量格式不正确，或者不符合常理！！")
    private Integer salesDistribution;
    /**
     * 公司id
     */
    @Range(min = 1,max = Integer.MAX_VALUE,message = "公司id格式不正确，或者不符合常理！！")
    private Long companyId;
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
}
