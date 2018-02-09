package com.spring.boot.dao.web;

import com.spring.boot.bean.MenuRole;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface MenuRoleDao {
    List<MenuRole> findMenuRoleInfoByRoleId(long roleId);
}
