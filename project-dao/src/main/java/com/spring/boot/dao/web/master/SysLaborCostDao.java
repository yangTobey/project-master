package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysLaborCost;
import com.spring.boot.bean.master.SysLaborCostDetails;
import com.spring.boot.dao.BaseDao;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysLaborCostDao extends BaseDao<SysLaborCost> {

    /**
     * 新增人员成本
     *
     * @param sysLaborCost
     * @return
     */
    int addSysLaborCost(SysLaborCost sysLaborCost);
    /**
     * 新增人员成本详情信息
     *
     * @param sysLaborCostDetails
     * @return
     */
    int addSysLaborCostDetails(SysLaborCostDetails sysLaborCostDetails);

    /**
     * 更新人员成本信息
     *
     * @param map
     * @return
     */
    int updateSysLaborCostInfo(Map<String, Object> map);

    /**
     * 删除人员成本信息
     *
     * @param map
     * @return
     */
    int deleteSysLaborCostInfo(Map<String, Object> map);
}
