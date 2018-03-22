package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysMenu;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysMenuDao {
    /**
     * 根据菜单id查找菜单信息
     * @param menuId
     * @return
     */
    SysMenu findSysMenuInfoByMenuId(long menuId);
    /**
     * 根据上级id查找菜单列表
     * @param parentId
     * @return
     */
    List<SysMenu> findMenuByParentId(Long parentId);
}
