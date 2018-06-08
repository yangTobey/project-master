package com.spring.boot.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysBasicDataService {
    /**
     * 查询公司基础数据分析结果信息
     *
     * @param companyId 公司id（0:：全国）
     * @param year      年份
     * @param month     月份
     * @return
     */
    Map<String, Object> sysBasicDataAnalysisData(long companyId, int year, int month);

    /**
     * 查询公司基础数据列表信息
     *
     * @param limit  每页限制条数
     * @param offset 页码
     * @param year   年份
     * @return
     */
    Map<String, Object> sysBasicDataAnalysisList(long companyId,int limit, int offset, int year);

    /**
     * 新增基础数据信息
     *
     * @param year                年份
     * @param month               月份
     * @param constructionArea    建筑面积
     * @param chargeArea          建筑收费面积
     * @param cityNumber          城市数量
     * @param projectNumber       项目数量
     * @param houseNumber         房屋数量
     * @param acceptHouseNumber   已收房屋数量
     * @param forSaleHouseNumber  待售房屋数量（空置）
     * @param decorateHouseNumber 装修房屋数量
     * @param parkingSpace        停车位总数量
     * @param forSaleParkingSpace 待售车位数量
     * @param salesDistribution   销售分配（销配）
     * @param companyId           公司id
     * @return
     */
    Map<String, Object> addSysBasicData(Integer year, Integer month, Double constructionArea, Double chargeArea, Integer cityNumber, Integer projectNumber, Integer houseNumber, Integer acceptHouseNumber
            , Integer forSaleHouseNumber, Integer decorateHouseNumber, Integer parkingSpace, Integer forSaleParkingSpace, Integer salesDistribution, Long companyId);

    /**
     * 更新信息
     *
     * @param basicId             主键id
     * @param year                年份
     * @param month               月份
     * @param constructionArea    建筑面积
     * @param chargeArea          建筑收费面积
     * @param cityNumber          城市数量
     * @param projectNumber       项目数量
     * @param houseNumber         房屋数量
     * @param acceptHouseNumber   已收房屋数量
     * @param forSaleHouseNumber  待售房屋数量（空置）
     * @param decorateHouseNumber 装修房屋数量
     * @param parkingSpace        停车位总数量
     * @param forSaleParkingSpace 待售车位数量
     * @param salesDistribution   销售分配（销配）
     * @param companyId           公司id
     * @return
     */
    Map<String, Object> updateSysBasicData(Long basicId, Integer year, Integer month, Double constructionArea, Double chargeArea, Integer cityNumber, Integer projectNumber, Integer houseNumber, Integer acceptHouseNumber
            , Integer forSaleHouseNumber, Integer decorateHouseNumber, Integer parkingSpace, Integer forSaleParkingSpace, Integer salesDistribution, Long companyId);

    /**
     * 删除基础数据信息
     *
     * @param basicId 主键id
     * @return
     */
    Map<String, Object> deleteSysBasicData(int basicId);
    /**
     * 根据id查找基础数据信息
     *
     * @param basicId 主键id
     * @return
     */
    Map<String, Object> findSysBasicDataById(int basicId);

    /**
     * 存放基础信息到redis
     */
    void setBasicDataAnalysisDataToRedis();
}
