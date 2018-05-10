package com.spring.boot.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by xiaoyang on 2018/1/25.
 *
 */
public interface SysMenuService {
    /**
     * 获取公司列表数据
     * @return
     */
    Map<String,Object> getSysMenu();
    /**
     * 获取系统目录和菜单，用于新增菜单或者更新菜单信息
     * @return
     */
    Map<String,Object> queryCatalogAndMenu(String type);
    /**
     * 获取系统菜单和功能按钮
     * @return
     */
    Map<String,Object> getSysModule(String type,Long roleId);
    /**
     * 获取菜单列表数据
     * @return
     */
    Map<String,Object> getSysMenuList(Integer limit, Integer offset);

    /**
     * 新增菜单信息
     * @param menuName 菜单名称
     * @param menuUrl 菜单地址
     * @param menuPerms 菜单标识权限名称
     * @param icon 菜单图标
     * @param parentId 上级id
     * @param sort 菜单排序号
     * @param menuType 菜单类型（1：c菜单，2：按钮）
     * @param remark 备注信息
     * @return
     */
    Map<String,Object> addSysMenu(String menuName, String menuUrl, String menuPerms, String icon, Long parentId, String sort, Integer menuType, String remark);

    /**
     * 更新菜单信息
     * @param menuName 菜单名称
     * @param menuUrl 菜单地址
     * @param menuPerms 菜单标识权限名称
     * @param icon 菜单图标
     * @param parentId 上级id
     * @param sort 菜单排序号
     * @param menuType 菜单类型（1：c菜单，2：按钮）
     * @param remark 备注信息
     * @param menuId 备注信息
     * @return
     */
    Map<String,Object> updateSysMenu(String menuName, String menuUrl, String menuPerms, String icon, Long parentId, String sort, Integer menuType, String remark,Long menuId);
    /**
     * 删除菜单信息
     * @param menuId 菜单id
     * @return
     */
    Map<String,Object> deleteSysMenuById(Long menuId);
    /**
     * 根据id获取菜单数据
     * @return
     */
    Map<String,Object> findSysMenuById(Long menuId);
}
