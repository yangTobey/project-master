package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysDepartment;
import com.spring.boot.dao.BaseDao;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysDepartmentDao extends BaseDao<SysDepartment>{

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
    int deleteSysDepartment(Map<String, Object> map);


}
