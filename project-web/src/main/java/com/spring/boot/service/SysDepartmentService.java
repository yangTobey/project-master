package com.spring.boot.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysDepartmentService {
    /**
     * 获取部门列表数据
     * @return
     */
    Map<String,Object> getSysDepartmentInfo(Integer limit,Integer offset);
    /**
     * 获取系统全部部门
     * @return
     */
    Map<String,Object> getAllSysDepartment();
    /**
     * 添加部门
     * @param departmentName
     * @param companyId
     * @return
     */
    Map<String, Object> addSysDepartment(String departmentName,Long companyId);

    /**
     * 更新部门信息
     * @param departmentName
     * @param companyId
     * @return
     */
    Map<String, Object> updateSysDepartmentInfo(Long departmentId,String departmentName,Long companyId);
    /**
     * 删除
     * @param departmentId
     * @return
     */
    Map<String, Object> deleteSysDepartment(Long departmentId);

}
