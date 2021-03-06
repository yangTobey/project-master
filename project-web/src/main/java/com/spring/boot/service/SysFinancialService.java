package com.spring.boot.service;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysBudgetDetails;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysFinancialService {
    /**
     * 收费情况报表统计详细信息
     *
     * @return
     */
    Map<String, Object> sysChargeDetails(Long companyId);
    /**
     * 根据主键id查找信息
     *
     * @return
     */
    Map<String, Object> findSysChargeDetailsById(Long chargeId);

    /**
     * 根据公司id、年份查找当前系统需要添加的周数记录
     * @param companyId
     * @return
     */
    Map<String, Object> sysChargeAddWeekOfYearByCompanyId(Long companyId);
    /**
     * 新增收费请款详细信息
     *
     * @return
     */
    Map<String, Object> addSysCharge(Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn,Integer year,Integer weekOfYear);
    /**
     * 更新收费请款详细信息
     *
     * @return
     */
    Map<String, Object> updateSysCharge(Long chargeId,Long companyId, Double chargeMoney, Double chargeMoneyNow, Double chargeDebt, Double chargeDebtReturn);
    /**
     * 月度应收账款报表统计详细信息
     *
     * @return
     */
    Map<String, Object> sysAccountsReceivableAnalysis(Long companyId, Integer year, Integer month);
    /**
     * 根据主键id查找数据
     * @param accountsId 主键id
     * @return
     */
    Map<String, Object> findSysAccountsReceivableById(Long accountsId);

    /**
     * 应收账款列表信息
     * @param companyId
     * @param year
     * @param limit
     * @param offset
     * @return
     */
    Map<String, Object> sysAccountsReceivableList(Long companyId,Integer year,Integer limit,Integer offset,Integer month);
    /**
     * 新增月度应收账款报表统计详细信息
     *
     * @return
     */
    Map<String, Object> addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable);
    /**
     * 更新月度应收账款报表统计详细信息
     *
     * @return
     */
    Map<String, Object> updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable);
    /**
     * 删除月度应收账款报表统计详细信息
     *
     * @return
     */
    Map<String, Object> deleteSysAccountsReceivable(Long accountsId);

    /**
     * 预算报表分析
     * @param companyId 公司id
     * @return
     */
    Map<String, Object> sysBudgetDetailsAnalysis(Long companyId, Integer year, Integer month);
    /**
     * 预算执行列表信息
     * @param companyId 公司id
     * @param year 年份
     * @return
     */
    Map<String, Object> sysBudgetDetailsList(Long companyId,Integer year,Integer limit,Integer offset,Integer month);
    /**
     * 根据主键id查找数据
     * @param budgetId 主键id
     * @return
     */
    Map<String, Object> findSysBudgetDetailsById(Long budgetId);

    /**
     * 新增执行预算信息
     * @param sysBudgetDetails 实体信息
     * @return
     */
    Map<String, Object> addSysBudgetDetails(SysBudgetDetails sysBudgetDetails);
    /**
     * 更新执行预算信息
     * @param sysBudgetDetails 实体信息
     * @return
     */
    Map<String, Object> updateSysBudgetDetails(SysBudgetDetails sysBudgetDetails);
    /**
     * 删除执行预算信息
     * @param budgetId 主键id
     * @return
     */
    Map<String, Object> deleteSysBudgetDetails(Long budgetId);

    /**
     * 保存收费情况详细信息到redis缓存
     */
    void setSysChargeToRedis();

    /**
     * 保存应收账款到redis缓存
     */
    void setSysAccountsReceivableToRedis();

    /**
     * 保存预算信息到redis缓存
     */
    void setSysBudgetDetailsToRedis();



}
