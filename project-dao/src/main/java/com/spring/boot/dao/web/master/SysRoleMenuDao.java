package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysRoleMenu;
import com.spring.boot.dao.BaseDao;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu>{
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
