package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysChargeDao extends BaseDao<SysChargeDetails> {
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
    SysChargeDetails sysChargeDetailsForYear(Map<String, Object> map);
    /**
     * 根据年份跟周数查找信息
     *
     * @return
     */
    SysChargeDetails findSysChargeDetailsByWeekOfYear(@Param("year")Integer year,@Param("weekOfYear") Integer weekOfYear,@Param("companyId")Long companyId);
    /**
     * 根据主键id查找信息
     *
     * @return
     */
    SysChargeDetails findSysChargeDetailsById(Long chargeId);

    /**
     * 新增系统收费详细信息
     */
    int addSysCharge(SysChargeDetails sysChargeDetails);
}
