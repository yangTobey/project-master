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
     * 收费情况报表统计(年，为大屏财务数据展示服务)
     *
     * @return
     */
    List<SysChargeDetails> sysChargeDetailsForYear(Map<String, Object> map);
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


}
