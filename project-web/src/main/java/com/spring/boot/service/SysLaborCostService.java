package com.spring.boot.service;

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
    Map<String,Object> getSysLaborCostInfo(String companyId);
    /**
     * 查询人员成本信息（根据操作类型查询信息，数据分析图表数据或者列表数据）
     * @param companyId 公司id
     * @return
     */
    Map<String,Object> getSysLaborCostList(String companyId,int year);


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
    int addSysLaborCost(String companyId, String year, String month,String propertyLaborCost,String propertyHeadcountTotal,String propertyEmployeeTotal,String propertyEntryTotal,String propertyDemissionTotal,
                     String eBusinessLaborCost,String eBusinessHeadcountTotal,String eBusinessEmployeeTotal,String eBusinessEntryTotal,String eBusinessDemissionTotal,
                     String saleLaborCost,String saleHeadcountTotal,String saleEmployeeTotal,String saleEntryTotal,String saleDemissionTotal);

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
    int updateSysLaborCostInfo(String laborCostId,String companyId, String year, String month,String propertyLaborCost,String propertyHeadcountTotal,String propertyEmployeeTotal,String propertyEntryTotal,String propertyDemissionTotal,
                               String eBusinessLaborCost,String eBusinessHeadcountTotal,String eBusinessEmployeeTotal,String eBusinessEntryTotal,String eBusinessDemissionTotal,
                               String saleLaborCost,String saleHeadcountTotal,String saleEmployeeTotal,String saleEntryTotal,String saleDemissionTotal);
    /**
     * 删除人员成本信息
     * @param laborCostId 人员成本主表id
     * @return
     */
    int deleteSysLaborCostInfo(String laborCostId);
}
