package com.spring.boot.service;

import com.spring.boot.entity.SysLaborCostDetailsAddEntity;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysLaborCostService {
    /**
     * 查询人员成本信息（根据操作类型查询信息，数据分析图表数据或者列表数据）
     * @param companyId 公司id
     * @return
     */
    Map<String,Object> getSysLaborCostAnalysis(Long companyId, Integer year, Integer month);
    /**
     * 查询人员成本信息（根据操作类型查询信息，数据分析图表数据或者列表数据）
     * @param companyId 公司id
     * @return
     */
    Map<String,Object> getSysLaborCostList(Integer limit,Integer offset,Long companyId,Integer year,Integer month);


    /**
     * 新增人员成本信息
     * @param companyId
     * @param year
     * @param month
     * @param propertyLaborCost
     * @param propertyHeadcountTotal
     * @param propertyEmployeeTotal
     * @param propertyEntryTotal
     * @param propertyDemissionTotal
     * @param eBusinessLaborCost
     * @param eBusinessHeadcountTotal
     * @param eBusinessEmployeeTotal
     * @param eBusinessEntryTotal
     * @param eBusinessDemissionTotal
     * @param saleLaborCost
     * @param saleHeadcountTotal
     * @param saleEmployeeTotal
     * @param saleEntryTotal
     * @param saleDemissionTotal
     * @return
     */
    Map<String,Object> addSysLaborCost(SysLaborCostDetailsAddEntity sysLaborCostDetailsEntity);

    /**
     * 更新人员成本信息
     * @param laborCostId 人员成本主表id
     * @param companyId
     * @param year
     * @param month
     * @param propertyLaborCost
     * @param propertyHeadcountTotal
     * @param propertyEmployeeTotal
     * @param propertyEntryTotal
     * @param propertyDemissionTotal
     * @param eBusinessLaborCost
     * @param eBusinessHeadcountTotal
     * @param eBusinessEmployeeTotal
     * @param eBusinessEntryTotal
     * @param eBusinessDemissionTotal
     * @param saleLaborCost
     * @param saleHeadcountTotal
     * @param saleEmployeeTotal
     * @param saleEntryTotal
     * @param saleDemissionTotal
     * @return
     */
    Map<String,Object> updateSysLaborCostInfo(SysLaborCostDetailsAddEntity sysLaborCostDetailsEntity);
    /**
     * 删除人员成本信息
     * @param laborCostId 人员成本主表id
     * @return
     */
    Map<String,Object> deleteSysLaborCostInfo(Long laborCostId);
    /**
     * 根据人员成本id查找详细信息
     * @param laborCostId 人员成本主表id
     * @return
     */
    Map<String,Object> findSysLaborCostById(Long laborCostId);

    /**
     * 将统计信息存储到redis缓存中
     */
    void setDateToRedis();
}
