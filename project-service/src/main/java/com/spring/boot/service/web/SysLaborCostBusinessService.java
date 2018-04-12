package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysDepartment;
import com.spring.boot.bean.master.SysLaborCost;
import com.spring.boot.bean.master.SysLaborCostDetails;
import com.spring.boot.bean.master.entity.SysLaborCostDepartmentEntity;
import com.spring.boot.bean.master.entity.SysLaborCostDetailsEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysLaborCostBusinessService {

    /**
     * 获取人工总成本
     * @return
     */
    SysLaborCostDetailsEntity  getSysLaborCostTotal(Map<String, Object> map);
    /**
     * 根据 物业、电商、销配部门类型获取当月人工总成本
     * @return
     */
    List<SysLaborCostDepartmentEntity>  getSysLaborCostDepartmentTotal(Map<String, Object> map);
    /**
     * 获取人员成本列表数据
     * @return
     */
    List<SysLaborCostDetailsEntity> getSysLaborCostList(Map<String, Object> map);

    /**
     * 获取人员成本列表数据总条数
     * @return
     */
    int getSysLaborCostListTotal(Map<String, Object> map);

    /**
     * 根据人员成本id查找对应的信息
     * @return
     */
    SysLaborCost findSysLaborCostByLaborCostId(Long laborCostId);
    /**
     * 根据人员成本id查找对应部门详细信息
     * @return
     */
    List<SysLaborCostDetails> findSysLaborCostDetailsByLaborCostId(Long laborCostId);
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
     * @param sysLaborCost
     * @return
     */
    int updateSysLaborCostInfo(SysLaborCost sysLaborCost);

    /**
     * 更新人员成本详情
     * @param sysLaborCostDetails
     * @return
     */
    int updateSysLaborCostDetailsInfo(SysLaborCostDetails sysLaborCostDetails);

    /**
     * 删除人员成本
     * @param map
     * @return
     */
    int deleteSysLaborCostInfo(Map<String, Object> map);
}
