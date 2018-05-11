package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysMenu;
import com.spring.boot.dao.web.master.SysMenuDao;
import com.spring.boot.service.web.SysMenuBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<SysMenu> findMenuAndModule() {
        return sysMenuDao.findMenuAndModule();
    }

    @Override
    public List<SysMenu> getSysMenuList(Map<String, Object> map) {
        return sysMenuDao.getSysMenuList(map);
    }

    @Override
    public List<SysMenu> queryCatalogAndMenu(Integer menuType) {
        return sysMenuDao.queryCatalogAndMenu(menuType);
    }

    @Override
    public int getSysMenuListTotal(Map<String, Object> map) {
        return sysMenuDao.getSysMenuListTotal(map);
    }

    @Override
    public int addSysMenu(Map<String, Object> map) {
        return sysMenuDao.addSysMenu(map);
    }

    @Override
    public int updateSysMenu(Map<String, Object> map) {
        return sysMenuDao.updateSysMenu(map);
    }

    @Override
    public int deleteSysMenuById(Long menuId) {
        return sysMenuDao.deleteSysMenuById(menuId);
    }

    @Override
    public SysMenu findSysMenuById(Long menuId) {
        return sysMenuDao.findSysMenuById(menuId);
    }
}
