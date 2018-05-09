package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysMenu;

import java.util.List;
import java.util.Map;

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
    List<SysMenu> findMenuByParentId(Long parentId,Integer selectType);
    /**
     * 查找系统全部菜单和按钮（不包含目录）
     * @return
     */
    List<SysMenu> findMenuAndModule();

    /**
     * 获取菜单列表数据
     * @return
     */
    List<SysMenu> getSysMenuList(Map<String, Object> map);

    /**
     * 新增菜单信息
     * @param map
     * @return
     */
    int addSysMenu(Map<String, Object> map);
    /**
     * 更新菜单信息
     * @param map
     * @return
     */
    int updateSysMenu(Map<String, Object> map);
    /**
     * 删除菜单信息
     * @param menuId 菜单id
     * @return
     */
    int deleteSysMenuById(Long menuId);
    /**
     * 根据id获取菜单数据
     * @return
     */
    SysMenu findSysMenuById(Long menuId);
}
