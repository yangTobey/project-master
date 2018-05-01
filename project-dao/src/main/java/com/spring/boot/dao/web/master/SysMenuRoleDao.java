package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysRoleMenu;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysMenuRoleDao {
    List<SysRoleMenu> findMenuRoleInfoByRoleId(long roleId);
}
