package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.bean.master.SysUserCompany;
import com.spring.boot.dao.web.master.SysUserCompanyDao;
import com.spring.boot.dao.web.master.SysUserDao;
import com.spring.boot.service.web.SysUserBusinessService;
import com.spring.boot.service.web.SysUserCompanyBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysUserCompanyBusinessServiceImpl implements SysUserCompanyBusinessService {
    @Autowired
    private SysUserCompanyDao sysUserCompanyDao;

    @Override
    public List<SysUserCompany> sysUserCompany(Long userId) {
        return sysUserCompanyDao.sysUserCompany(userId);
    }

    @Override
    public int saveSysUserCompany(SysUserCompany sysUserCompany) {
        return sysUserCompanyDao.saveSysUserCompany(sysUserCompany);
    }

    @Override
    public int deleteSysUserCompany(Long userId) {
        return sysUserCompanyDao.deleteSysUserCompany(userId);
    }

    @Override
    public SysUserCompany sysUserCompanyAuthority(Long userId,Long companyId) {
        return sysUserCompanyDao.sysUserCompanyAuthority(userId,companyId);
    }
}
