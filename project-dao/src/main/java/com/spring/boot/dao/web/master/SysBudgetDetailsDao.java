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
     * 报表统计详细信息(当区域为全国时，调用该接口)
     *
     * @return
     */
    SysBudgetDetails sysBudgetDetailsAnalysis(Map<String, Object> map);
    /**
     * 预算报表统计详细信息（当区域选择一个小区时，调用该接口）
     *
     * @return
     */
    SysBudgetDetails sysBudgetAnalysisByCompanyId(Map<String, Object> map);
    /**
     * 根据年份和月份查询数据库记录
     *
     * @return
     */
    SysBudgetDetails findRecordByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month,@Param("companyId") Long companyId);
    /**
     * 月度应收账款报表统计详细信息(柱状图)
     *
     * @return
     */
    List<SysBudgetDetails> sysBudgetDetailsIncomeForMonth(Map<String, Object> map);
    /**
     * 根据id查找信息
     * @param map
     * @return
     */
    SysBudgetDetails findSysBudgetDetailsById(Map<String, Object> map);
    /**
     * 预算执行列表
     *
     * @return
     */
    List<SysBudgetDetails> sysBudgetDetailsList(Map<String, Object> map);
    /**
     * 预算执行列表总条数
     *
     * @return
     */
    int sysBudgetDetailsListTotal(Map<String, Object> map);
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
     * 新增执行预算信息
     * @param sysBudgetDetails 实体信息
     * @return
     */
    int addSysBudgetDetails(SysBudgetDetails sysBudgetDetails);
    /**
     * 更新执行预算信息
     * @param sysBudgetDetails 实体信息
     * @return
     */
    int updateSysBudgetDetails(SysBudgetDetails sysBudgetDetails);
    /**
     * 删除执行预算信息
     * @param budgetId 主键id
     * @return
     */
    int deleteSysBudgetDetails(Long budgetId);
}
