package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysDepartment;
import com.spring.boot.bean.master.SysLaborCost;
import com.spring.boot.bean.master.SysLaborCostDetails;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysLaborCostBusinessService {

    /**
     * 获取人员成本列表数据
     * @return
     */
    List<SysLaborCost> getSysLaborCostList();

    /**
     * 新增人员成本
     * @param sysLaborCost
     * @return
     */
    int addSysLaborCost(SysLaborCost sysLaborCost);
    /**
     * 新增人员成本详细信息
     * @param sysLaborCostDetails
     * @return
     */
    int addSysLaborCostDetails(SysLaborCostDetails sysLaborCostDetails);
    /**
     * 更新人员成本
     * @param map
     * @return
     */
    int updateSysLaborCostInfo(Map<String, Object> map);

    /**
     * 删除人员成本
     * @param map
     * @return
     */
    int deleteSysLaborCostInfo(Map<String, Object> map);
}
