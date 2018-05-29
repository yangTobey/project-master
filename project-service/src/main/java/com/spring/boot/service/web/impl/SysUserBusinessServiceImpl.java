package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.bean.master.SysUserCompany;
import com.spring.boot.dao.web.master.SysUserDao;
import com.spring.boot.service.web.SysUserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysUserBusinessServiceImpl implements SysUserBusinessService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<SysUser> sysUserList(Map<String, Object> map) {
        return sysUserDao.sysUserList(map);
    }

    @Override
    public int sysUserTotal() {
        return sysUserDao.sysUserTotal();
    }

    @Override
    public SysUser findByUserId(Map<String, Object> map) {
        return sysUserDao.findByUserId(map);
    }

    @Override
    public SysUser sysUserInfo(Long userId) {
        return sysUserDao.sysUserInfo(userId);
    }

    @Override
    public SysUser findByUserAccount(Map<String, Object> map) {
        return sysUserDao.findByUserAccount(map);
    }

    @Override
    public int updatePassword(Map<String, Object> map) {
        return sysUserDao.updatePassword(map);
    }

    @Override
    public int resetSysUserPassword(Map<String, Object> map) {
        return sysUserDao.resetSysUserPassword(map);
    }

    @Override
    public int addUser(SysUser sysUser) {
        return sysUserDao.addUser(sysUser);
    }

    @Override
    public int updateUserInfo(Map<String, Object> map) {
        return sysUserDao.updateUserInfo(map);
    }

    @Override
    public int deleteUser(Map<String, Object> map) {
        return sysUserDao.deleteUser(map);
    }

    @Override
    public List<Long> queryUserAllMenuId(Long userId,Integer selectType) {
        return sysUserDao.queryUserAllMenuId(userId,selectType);
    }

    @Override
    public List<SysUser> findSysUserByCompanyId(Long companyId) {
        return sysUserDao.findSysUserByCompanyId(companyId);
    }

    @Override
    public List<SysUser> findSysUserByDepartmentd(Long departmentId) {
        return sysUserDao.findSysUserByDepartmentd(departmentId);
    }

    @Override
    public List<Long> queryUserAllModuleId(Long userId) {
        return null;
    }
}
