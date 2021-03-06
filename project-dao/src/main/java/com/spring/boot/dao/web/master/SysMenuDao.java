package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysMenuDao {
    /**
     * 根据菜单id查找菜单信息
     * @param menuId
     * @return
     */
    SysMenu findSysMenuInfoByMenuId(Long menuId);
    /**
     * 根据上级id查找菜单列表
     * @param parentId
     * @return
     */
    List<SysMenu> findMenuByParentId(@Param("parentId")Long parentId, @Param("selectType")Integer selectType);
    /**
     * 查找系统全部菜单和按钮（不包含目录）
     * @return
     */
    List<SysMenu> findMenuAndModule();
    /**
     * 获取系统目录和菜单，用于新增菜单或者更新菜单信息
     * @param menuType 新增菜单类型（0：目录，1：菜单，2：按钮）
     * 注：如sql需要利用参数作判断，需要添加@Param注解
     * @return
     */
    List<SysMenu> queryCatalogAndMenu(@Param("menuType")Integer menuType);
    /**
     * 获取菜单列表数据
     * @return
     */
    List<SysMenu> getSysMenuList(Map<String, Object> map);
    /**
     * 获取菜单列表数据总条数
     * @return
     */
    int getSysMenuListTotal(Map<String, Object> map);

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
