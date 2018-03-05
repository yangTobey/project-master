package com.spring.boot.dao.web.master;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface DepartmentDao {

    /**
     * 新增部门
     * @param map
     * @return
     */
    int addDepartment(Map<String, Object> map);
    /**
     * 更新部门信息
     * @param map
     * @return
     */
    int updateDepartmentInfo(Map<String, Object> map);
    /**
     * 删除
     * @param map
     * @return
     */
    int deleteDepartment(Map<String, Object> map);


}
