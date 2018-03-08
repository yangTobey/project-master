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
    Map<String,Object> getSysDepartmentInfo();
    /**
     * 添加部门
     * @param departmentName
     * @param companyId
     * @return
     */
    int addSysDepartment(String departmentName,String companyId);

    /**
     * 更新部门信息
     * @param departmentName
     * @param companyId
     * @return
     */
    int updateSysDepartmentInfo(String departmentId,String departmentName,String companyId);
    /**
     * 删除
     * @param departmentId
     * @return
     */
    int deleteSysDepartment(String departmentId);

}
