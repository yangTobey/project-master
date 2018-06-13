package com.spring.boot.bean.master.entity;

/**
 * Created by Administrator on 2018/6/13.
 */
public class SysUserRoleEntity {
    //多个角色id组合，如1,2
    private String roleIds;
    //多个角色名称组合，如：a,b
    private String roleNames;

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }
}
