package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysBudgetDetails;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysBudgetDetailsDao {
    /**
     * 报表统计详细信息
     *
     * @return
     */
    SysBudgetDetails sysBudgetDetailsAnalysis(Map<String, Object> map);
    /**
     * 根据年份和月份查询数据库记录
     *
     * @return
     */
    SysAccountsReceivable findRecordByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);
    /**
     * 月度应收账款报表统计详细信息(柱状图)
     *
     * @return
     */
    List<SysBudgetDetails> sysBudgetDetailsIncomeForMonth(Map<String, Object> map);
    /**
     * 根据年份和月份，获取当前年上一年12月份对应的实际利润信息
     *
     * @return
     */
    SysBudgetDetails sysBudgetRealProfitsByMonth(@Param("year") Integer year, @Param("month") Integer month,@Param("sysUserCompanyIds")List<Long> sysUserCompanyIds);
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
     * 新增月度应收账款报表统计详细信息
     *
     * @return
     */
    int updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable);
}
