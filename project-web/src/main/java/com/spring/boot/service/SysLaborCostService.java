package com.spring.boot.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysLaborCostService {
    /**
     * 获取人员成本列表数据
     * @return
     */
    Map<String,Object> getSysLaborCostList();

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
     * @param companyId 公司id
     * @param companyName 公司名称
     * @param companyPhone 公司联系电话
     * @param companyAddress 公司地址
     * @return
     */
    int updateSysLaborCostInfo(String companyId, String companyName, String companyPhone, String companyAddress);
    /**
     * 删除人员成本信息
     * @param companyId 公司id
     * @return
     */
    int deleteSysLaborCostInfo(String companyId);
}
