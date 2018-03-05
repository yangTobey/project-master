package com.spring.boot.service;

import com.spring.boot.bean.master.User;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface DepartmentService {
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
