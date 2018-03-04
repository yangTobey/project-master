package com.spring.boot.service;

import com.spring.boot.bean.master.User;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface DepartmentService {
    /**
     * 添加部门
     * @param department
     * @param companyId
     * @return
     */
    int addDepartment(String department,String companyId);

}
