package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysRoleMenu;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysRoleMenuBusinessService {

    List<SysRoleMenu> findRoleMenuInfoByRoleId(Long roleId);
}
