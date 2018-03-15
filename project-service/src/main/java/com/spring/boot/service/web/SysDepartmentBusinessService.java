package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysDepartment;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysDepartmentBusinessService {
    /**
     * 获取部门列表数据
     * @return
     */
    List<SysDepartment> getSysDepartmentInfo(Map<String, Object> map);
    /**
     * 获取部门列表数据条数
     *
     * @return
     */
    int getSysDepartmentTotal(Map<String, Object> map);

    /**
     * 新增部门
     * @param map
     * @return
     */
    int addSysDepartment(Map<String, Object> map);
    /**
     * 更新部门
     * @param map
     * @return
     */
    int updateSysDepartmentInfo(Map<String, Object> map);

    /**
     * 删除部门
     * @param map
     * @return
     */
    int deleteSysDepartment(Map<String, Object> map);
}
