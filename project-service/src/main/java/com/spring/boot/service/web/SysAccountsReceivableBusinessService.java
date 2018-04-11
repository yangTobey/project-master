package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysAccountsReceivable;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysAccountsReceivableBusinessService {
    /**
     * 月度应收账款报表统计详细信息
     * @param map
     * @return
     */
    SysAccountsReceivable sysAccountsReceivableAnalysis(Map<String, Object> map);

    /**
     *  根据年份和月份查询数据库记录
     * @param year
     * @param month
     * @return
     */
    SysAccountsReceivable findRecordByYearAndMonth(Integer year,Integer month,Long companyId);

    /**
     * 月度应收账款报表统计详细信息(柱状图)
     * @param map
     * @return
     */
    List<SysAccountsReceivable> sysAccountsReceivableAnalysisForMonth(Map<String, Object> map);
    /**
     * 月度应收账款列表
     * @param map
     * @return
     */
    List<SysAccountsReceivable> sysAccountsReceivableList(Map<String, Object> map);
    /**
     * 根据主键id查找信息
     * @param map
     * @return
     */
    SysAccountsReceivable findSysAccountsReceivableById(Map<String, Object> map);
    /**
     * 新增月度应收账款列表总条数
     * @param map
     * @return
     */
    int sysAccountsReceivableListTotal(Map<String, Object> map);

    /**
     * 获取数据所有月份
     * @param map
     * @return
     */
    String sysAccountsReceivableMonths(Map<String, Object> map);

    /**
     * 新增月度应收账款报表统计详细信息
     * @param sysAccountsReceivable
     * @return
     */
    int addSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable);

    /**
     * 更新月度应收账款报表统计详细信息
     * @param sysAccountsReceivable
     * @return
     */
    int updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable);

    /**
     * 删除月度应收账款报表统计详细信息
     * @param budgetId
     * @return
     */
    int deleteSysAccountsReceivable(Long budgetId);


}
