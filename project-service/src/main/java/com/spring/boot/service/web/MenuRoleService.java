package com.spring.boot.service.web;

import com.spring.boot.bean.master.MenuRole;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface MenuRoleService {

    List<MenuRole> findMenuRoleInfoByRoleId(long roleId);
}
