package com.spring.boot.service;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysDepartmentService {
    /**
     * 添加部门
     * @param departmentName
     * @param companyId
     * @return
     */
    int addDepartment(String departmentName,String companyId);

    /**
     * 更新部门信息
     * @param departmentName
     * @param companyId
     * @return
     */
    int updateDepartmentInfo(String departmentId,String departmentName,String companyId);
    /**
     * 删除
     * @param departmentId
     * @return
     */
    int deleteDepartment(String departmentId);

}
