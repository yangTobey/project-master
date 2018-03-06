package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysUser;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysRoleDao {
    /**
     * 新增角色信息
     *
     * @param map
     * @return
     */
    int addSysRole(Map<String, Object> map);

    /**
     * 更新角色信息
     *
     * @param map
     * @return
     */
    int updateSysRole(Map<String, Object> map);

    /**
     * 删除角色信息（只更新角色状态，不作删除处理）
     *
     * @param map
     * @return
     */
    int deleteSysRole(Map<String, Object> map);
}
