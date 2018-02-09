package com.spring.boot.service.web.impl;

import com.spring.boot.bean.SysMenu;
import com.spring.boot.dao.web.SysMenuDao;
import com.spring.boot.service.web.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class SysMenuServiceImpl implements SysMenuService{
    @Autowired
    private SysMenuDao sysMenuDao;
    @Override
    public SysMenu findSysMenuInfoByMenuId(long menuId) {
        return sysMenuDao.findSysMenuInfoByMenuId(menuId);
    }
}
