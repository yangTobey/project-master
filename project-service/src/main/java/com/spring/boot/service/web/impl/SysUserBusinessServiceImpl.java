package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysUser;
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
    public SysUser findByUserId(Map<String, Object> map) {
        return sysUserDao.findByUserId(map);
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
    public int addUser(Map<String, Object> map) {
        return sysUserDao.addUser(map);
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
    public List<Long> queryUserAllMenuId(Long userId) {
        return sysUserDao.queryUserAllMenuId(userId);
    }
}
