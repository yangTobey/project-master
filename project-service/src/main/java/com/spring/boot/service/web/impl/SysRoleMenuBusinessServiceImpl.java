package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysRoleMenu;
import com.spring.boot.dao.web.master.SysRoleMenuDao;
import com.spring.boot.service.web.SysRoleMenuBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class SysRoleMenuBusinessServiceImpl implements SysRoleMenuBusinessService {
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Override
    public List<SysRoleMenu> findRoleMenuInfoByRoleId(Long roleId) {
        return sysRoleMenuDao.findRoleMenuInfoByRoleId(roleId);
    }

    @Override
    public List<Long> getMenuIdByRoleId(Long roleId) {
        return sysRoleMenuDao.getMenuIdByRoleId(roleId);
    }

    @Override
    public SysRoleMenu findRoleMenuByMenuId(Long menuId,Long roleId) {
        return sysRoleMenuDao.findRoleMenuByMenuId(menuId,roleId);
    }
}
