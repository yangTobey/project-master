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
    Map<String,Object> getSysLaborCostAnalysis(Long companyId);
    /**
     * 查询人员成本信息（根据操作类型查询信息，数据分析图表数据或者列表数据）
     * @param companyId 公司id
     * @return
     */
    Map<String,Object> getSysLaborCostList(Integer limit,Integer offset,Long companyId,Integer year);


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
    Map<String,Object> addSysLaborCost(Long companyId, Integer year, Integer month,Double propertyLaborCost,Integer propertyHeadcountTotal,Integer propertyEmployeeTotal,Integer propertyEntryTotal,Integer propertyDemissionTotal,
                                       Double eBusinessLaborCost,Integer eBusinessHeadcountTotal,Integer eBusinessEmployeeTotal,Integer eBusinessEntryTotal,Integer eBusinessDemissionTotal,
                                       Double saleLaborCost,Integer saleHeadcountTotal,Integer saleEmployeeTotal,Integer saleEntryTotal,Integer saleDemissionTotal
            , Integer propertyPayPeopleTotal, Integer propertyBeginMonthPeople, Integer propertyMonthDeploy, Integer eBusinessPayPeopleTotal, Integer eBusinessBeginMonthPeople, Integer eBusinessMonthDeploy, Integer salePayPeopleTotal, Integer saleBeginMonthPeople, Integer saleMonthDeploy);

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
    Map<String,Object> updateSysLaborCostInfo(Long laborCostId,Long companyId, Integer year, Integer month,Double propertyLaborCost,Integer propertyHeadcountTotal,Integer propertyEmployeeTotal,Integer propertyEntryTotal,Integer propertyDemissionTotal,
                                              Double eBusinessLaborCost,Integer eBusinessHeadcountTotal,Integer eBusinessEmployeeTotal,Integer eBusinessEntryTotal,Integer eBusinessDemissionTotal,
                                              Double saleLaborCost,Integer saleHeadcountTotal,Integer saleEmployeeTotal,Integer saleEntryTotal,Integer saleDemissionTotal
            , Integer propertyPayPeopleTotal, Integer propertyBeginMonthPeople, Integer propertyMonthDeploy, Integer eBusinessPayPeopleTotal, Integer eBusinessBeginMonthPeople, Integer eBusinessMonthDeploy, Integer salePayPeopleTotal, Integer saleBeginMonthPeople, Integer saleMonthDeploy);
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
}
