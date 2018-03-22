package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysMenu;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysMenuBusinessService {
    /**
     * 根据菜单id获取菜单信息
     *
     * @param menuId 菜单id
     * @return
     */
    SysMenu findSysMenuInfoByMenuId(Long menuId);

    /**
     * 根据上级id查找菜单列表
     * @param parentId
     * @return
     */
    List<SysMenu> findMenuByParentId(Long parentId);
}
