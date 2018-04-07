package com.spring.boot.service;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.bean.master.SysUser;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysRoleService {
    /**
     * 获取角色列表数据
     * @return
     */
    Map<String,Object> getSysRoleList(Integer limit,Integer offset);
    /**
     * 获取系统全部角色数据
     * @return
     */
    Map<String,Object> getAllSysRole();

    /**
     * 新增角色信息
     *
     * @param roleName  角色名称
     * @param remark     备注信息
     * @return
     */
    Map<String, Object> addSysRole(String roleName,String moduleId, String remark);

    /**
     * 更新角色信息
     *
     * @param roleId    角色id
     * @param roleName  角色名称
     * @param remark       备注信息
     * @return
     */
    Map<String, Object> updateSysRole(String roleId, String roleName, String remark);

    /**
     * 删除角色信息（只更新角色状态，不作删除处理）
     *
     * @param roleId 角色id
     * @return
     */
    Map<String, Object> deleteSysRole(String roleId);
}
