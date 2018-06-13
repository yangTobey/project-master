package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.bean.master.SysUserRole;
import com.spring.boot.bean.master.entity.SysUserRoleEntity;
import com.spring.boot.dao.web.master.SysUserRoleDao;
import com.spring.boot.service.web.SysUserRoleBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
@Service
public class SysUserRoleBusinessServiceImpl implements SysUserRoleBusinessService {
    @Autowired
    private SysUserRoleDao sysUserRoleDao;

    @Override
    public List<SysUserRole> findRoleByUserId(long userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        return sysUserRoleDao.findRoleByUserId(map);
    }

    @Override
    public int addUserRole(SysUserRole sysUserRole) {
        return sysUserRoleDao.addUserRole(sysUserRole);
    }

    @Override
    public int deleteUserRoleByUserId(Long userId) {
        return sysUserRoleDao.deleteUserRoleByUserId(userId);
    }

    @Override
    public List<SysUserRole> findRoleByRoleId(Long roleId) {
        return sysUserRoleDao.findRoleByRoleId(roleId);
    }



    @Override
    public SysUserRoleEntity findUserRoleNameByRoleId(Long userId) {
        return sysUserRoleDao.findUserRoleNameByRoleId(userId);
    }
}
