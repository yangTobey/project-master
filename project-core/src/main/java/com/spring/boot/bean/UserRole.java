package com.spring.boot.bean;

/**
 * Created by Administrator on 2018/2/5.
 */
public class UserRole {
    private long id;
    private long userId;
    private long roleId;
    private String rolePerms;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRolePerms() {
        return rolePerms;
    }

    public void setRolePerms(String rolePerms) {
        this.rolePerms = rolePerms;
    }
}
