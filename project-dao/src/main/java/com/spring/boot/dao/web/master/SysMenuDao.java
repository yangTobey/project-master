package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysMenu;
import org.apache.ibatis.annotations.Param;

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
    List<SysMenu> findMenuByParentId(@Param("parentId")Long parentId, @Param("selectType")Integer selectType);
    /**
     * 查找系统全部菜单和按钮（不包含目录）
     * @return
     */
    List<SysMenu> findMenuAndModule();
}
