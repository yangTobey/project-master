package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysUser;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.dao.web.master.SysUserDao;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysUserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysCompanyBusinessServiceImpl implements SysCompanyBusinessService {
    @Autowired
    private SysCompanyDao sysCompanyDao;

    @Override
    public int addCompany(Map<String, Object> map) {
        return sysCompanyDao.addCompany(map);
    }

    @Override
    public int updateCompanyInfo(Map<String, Object> map) {
        return sysCompanyDao.updateCompanyInfo(map);
    }

    @Override
    public int deleteCompanyInfo(Map<String, Object> map) {
        return sysCompanyDao.deleteCompanyInfo(map);
    }
}
