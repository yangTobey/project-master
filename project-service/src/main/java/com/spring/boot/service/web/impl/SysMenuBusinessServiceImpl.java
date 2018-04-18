package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.dao.web.master.SysMenuDao;
import com.spring.boot.service.web.SysMenuBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class SysMenuBusinessServiceImpl implements SysMenuBusinessService {
    @Autowired
    private SysMenuDao sysMenuDao;

    @Override
    public SysMenu findSysMenuInfoByMenuId(Long menuId) {
        return sysMenuDao.findSysMenuInfoByMenuId(menuId);
    }

    @Override
    public List<SysMenu> findMenuByParentId(Long parentId,Integer selectType) {
        return sysMenuDao.findMenuByParentId(parentId,selectType);
    }
}
