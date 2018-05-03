package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysRoleMenu;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysRoleMenuBusinessService {
    /**
     * 根据角色id查找菜单权限信息
     * @param roleId
     * @return
     */
    List<SysRoleMenu> findRoleMenuInfoByRoleId(Long roleId);

    /**
     * 根据角色id查找菜单权限信息，只取menuId
     * @param roleId
     * @return
     */
    List<Long> getMenuIdByRoleId(Long roleId);
}
