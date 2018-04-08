package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysAccountsReceivable;
import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.bean.master.entity.SysReceivableAccountsOwnerEntity;

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
     * 新增月度应收账款报表统计详细信息
     *
     * @return
     */
    int updateSysAccountsReceivable(SysAccountsReceivable sysAccountsReceivable);
}
