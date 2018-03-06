package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysMenuRole;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysMenuRoleService {

    List<SysMenuRole> findMenuRoleInfoByRoleId(long roleId);
}
