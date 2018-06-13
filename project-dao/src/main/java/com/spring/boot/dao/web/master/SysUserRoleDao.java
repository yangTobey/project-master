package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.bean.master.SysUserRole;
import com.spring.boot.bean.master.entity.SysUserRoleEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysUserRoleDao {
    /**
     * 根据用户id查找用户权限列表
     * @param map 参数map集合（userId）
     * @return
     */
    List<SysUserRole> findRoleByUserId(Map<String, Object> map);
    /**
     * 新增用户角色信息
     *
     * @param sysUserRole
     * @return
     */
    int addUserRole(SysUserRole sysUserRole);
    /**
     * 根据公司id删除角色信息
     *
     * @param userId
     * @return
     */
    int deleteUserRoleByUserId(Long userId);
    /**
     * 根据角色id查找用户权限列表信息（用于删除时做判断）
     * @param roleId
     * @return
     */
    List<SysUserRole> findRoleByRoleId(Long roleId);

    /**
     * 根据用户id查找用户权限角色名称详细信息（用于在查询用户列表时，获取用户多个角色信息）
     * @param userId
     * @return
     */
    SysUserRoleEntity findUserRoleNameByRoleId(Long userId);
}
