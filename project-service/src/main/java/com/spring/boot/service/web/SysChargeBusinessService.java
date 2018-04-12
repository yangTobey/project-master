package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysChargeDetails;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysChargeBusinessService {
    /**
     * 收费情况报表统计详细信息
     *
     * @return
     */
    SysChargeDetails sysChargeDetails(Map<String, Object> map);
    /**
     * 根据主键id查找信息
     *
     * @return
     */
    SysChargeDetails findSysChargeDetailsById(Long chargeId);

    /**
     * 根据年份跟周数查找信息
     *
     * @return
     */
    SysChargeDetails findSysChargeDetailsByWeekOfYear(Integer year,Integer weekOfYear,Long companyId);

    /**
     * 新增系统收费详细信息
     */
    int addSysCharge(SysChargeDetails sysChargeDetails);
    /**
     * 更新系统收费详细信息
     */
    int updateSysCharge(Map<String, Object> map);


    /**
     * Created by Administrator on 2018/1/25.
     */
    interface SysBudgetDetailsBusinessService {
        /**
         * 月度应收账款报表统计详细信息
         *
         * @return
         */
        SysAccountsReceivable sysAccountsReceivableAnalysis(Map<String, Object> map);
        /**
         * 根据年份和月份查询数据库记录
         *
         * @return
         */
        SysAccountsReceivable findRecordByYearAndMonth(Integer year, Integer month);
        /**
         * 月度应收账款报表统计详细信息(柱状图)
         *
         * @return
         */
        List<SysAccountsReceivable> sysAccountsReceivableAnalysisForMonth(List<Long> sysUserCompanyIds);
        /**
         * 获取数据所有月份
         *
         * @return
         */
        String sysAccountsReceivableMonths(Map<String, Object> map);
        /**
         * 新增月度应收账款报表统计详细信息
         *
         * @return
         */
        int addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable);
        /**
         * 更新月度应收账款报表统计详细信息
         *
         * @return
         */
        int updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable);


    }
}
