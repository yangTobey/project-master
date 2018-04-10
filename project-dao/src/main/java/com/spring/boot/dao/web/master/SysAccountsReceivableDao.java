package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.bean.master.entity.SysReceivableAccountsOwnerEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysAccountsReceivableDao {
    /**
     * 报表统计详细信息
     *
     * @return
     */
    SysAccountsReceivable sysAccountsReceivableAnalysis(Map<String, Object> map);
    /**
     * 根据年份和月份查询数据库记录
     *
     * @return
     */
    SysAccountsReceivable findRecordByYearAndMonth(@Param("year")Integer year,@Param("month") Integer month,@Param("companyId")Long companyId);
    /**
     * 月度应收账款报表统计详细信息(柱状图)
     *
     * @return
     */
    List<SysAccountsReceivable> sysAccountsReceivableAnalysisForMonth(Map<String, Object> map);
    /**
     * 获取数据所有月份
     *
     * @return
     */
    String sysAccountsReceivableMonths(Map<String, Object> map);
    /**
     * 月度应收账款列表
     * @param map
     * @return
     */
    List<SysAccountsReceivable> sysAccountsReceivableList(Map<String, Object> map);
    /**
     * 新增月度应收账款列表总条数
     * @param map
     * @return
     */
    int sysAccountsReceivableListTotal(Map<String, Object> map);
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
    /**
     * 删除月度应收账款报表统计详细信息
     *
     * @return
     */
    int deleteSysAccountsReceivable(Long budgetId);
}
