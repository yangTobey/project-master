package com.spring.boot.service.web;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface DepartmentBusinessService {

    /**
     * 新增部门
     * @param map
     * @return
     */
    int addDepartment(Map<String, Object> map);
    /**
     * 更新部门
     * @param map
     * @return
     */
    int updateDepartmentInfo(Map<String, Object> map);

    /**
     * 删除部门
     * @param map
     * @return
     */
    int deleteDepartment(Map<String, Object> map);
}
