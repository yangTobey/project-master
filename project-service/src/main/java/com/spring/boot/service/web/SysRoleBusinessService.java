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
    List<SysRole> getSysRoleList(Map<String, Object> map);
    /**
     * 获取角色列表数据条数
     *
     * @return
     */
    int getSysRoleListTotal();
    /**
     * 新增角色信息
     *
     * @param sysRole
     * @return
     */
    int addSysRole(SysRole sysRole);

    /**
     * 添加菜单角色信息
     * @param menuId
     * @param roleId
     * @return
     */
    int addSysRoleMenu(Long menuId,Long roleId);

    /**
     * 删除菜单权限信息
     * @param roleId
     * @return
     */
    int deleteSysRoleMenu(Long roleId);

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
    /**
     * 根据用户id查找用户权限详细信息
     * @param userId
     * @return
     */
    List<SysRole> findUserRoleByRoleId(Long userId);

}
