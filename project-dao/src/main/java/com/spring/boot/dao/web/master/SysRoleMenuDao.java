package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysRoleMenu;
import com.spring.boot.dao.BaseDao;

import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysRoleMenuDao extends BaseDao<SysRoleMenu>{
    List<SysRoleMenu> findRoleMenuInfoByRoleId(Long roleId);

}
