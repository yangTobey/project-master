package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysMenu;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysMenuService {
    /**
     * 根据菜单id获取菜单信息
     * @param menuId 菜单id
     * @return
     */
    SysMenu findSysMenuInfoByMenuId(long menuId);
}
