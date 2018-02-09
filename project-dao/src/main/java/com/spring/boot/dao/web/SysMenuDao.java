package com.spring.boot.dao.web;

import com.spring.boot.bean.SysMenu;

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
}
