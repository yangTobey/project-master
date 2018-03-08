package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysRole;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.dao.web.master.SysRoleDao;
import com.spring.boot.dao.web.master.SysUserDao;
import com.spring.boot.service.web.SysRoleBusinessService;
import com.spring.boot.service.web.SysUserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysRoleBusinessServiceImpl implements SysRoleBusinessService {
    @Autowired
    private SysRoleDao sysRoleDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<SysRole> getSysRoleList() {
        return sysRoleDao.queryList();
    }

    @Override
    public int addSysRole(SysRole sysRole) {
        return sysRoleDao.save(sysRole);
    }

    @Override
    public int updateSysRole(Map<String, Object> map) {
        return sysRoleDao.update(map);
    }

    @Override
    public int deleteSysRole(Map<String, Object> map) {
        return sysRoleDao.delete(map);
    }
}
