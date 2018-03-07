package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.bean.master.SysUser;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysRoleBusinessService {

    /**
     * 获取角色列表数据
     * @return
     */
    List<SysRole> getSysRoleList();
    /**
     * 新增角色信息
     *
     * @param sysRole
     * @return
     */
    int addSysRole(SysRole sysRole);

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
