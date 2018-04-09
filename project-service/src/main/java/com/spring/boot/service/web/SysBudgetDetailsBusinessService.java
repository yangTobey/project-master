package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysBudgetDetails;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysBudgetDetailsBusinessService {
    /**
     * 预算报表统计详细信息
     *
     * @return
     */
    SysBudgetDetails sysBudgetDetailsAnalysis(Map<String, Object> map);
    /**
     * 根据年份和月份查询数据库记录
     *
     * @return
     */
    SysAccountsReceivable findRecordByYearAndMonth(Integer year, Integer month);
    /**
     * 预算报表统计详细信息(柱状图)
     *
     * @return
     */
    List<SysBudgetDetails> sysBudgetDetailsIncomeForMonth(Map<String, Object> map);
    /**
     * 根据年份和月份，获取当前年上一年12月份对应的实际利润信息
     *
     * @return
     */
    SysBudgetDetails sysBudgetRealProfitsByMonth(Integer year, Integer month,List<Long> sysUserCompanyIds);
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
