package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysRoleDao extends BaseDao<SysRole>{
    /**
     * 获取角色列表数据
     * @return
     */
     /*List<SysRole> getSysRoleList();*/
    /**
     * 新增角色信息
     *
     * @param map
     * @return
     */
    /*int addSysRole(Map<String, Object> map);

    *//**
     * 更新角色信息
     *
     * @param map
     * @return
     *//*
    int updateSysRole(Map<String, Object> map);

    *//**
     * 删除角色信息（只更新角色状态，不作删除处理）
     *
     * @param map
     * @return
     *//*
    int deleteSysRole(Map<String, Object> map);*/
    /**
     * 获取角色列表数据条数
     *
     * @return
     */
    int getSysRoleListTotal();

    /**
     * 添加菜单角色信息
     * @param menuId
     * @param roleId
     * @return
     */
    int addSysRoleMenu(@Param("menuId")Long menuId,@Param("roleId") Long roleId);
    /**
     * 删除菜单权限信息
     * @param roleId
     * @return
     */
    int deleteSysRoleMenu(Long roleId);
    /**
     * 根据用户id查找用户权限详细信息
     * @param userId
     * @return
     */
    List<SysRole> findUserRoleByRoleId(Long userId);
}
