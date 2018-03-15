package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysUser;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.dao.web.master.SysUserDao;
import com.spring.boot.service.web.SysCompanyBusinessService;
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
public class SysCompanyBusinessServiceImpl implements SysCompanyBusinessService {
    @Autowired
    private SysCompanyDao sysCompanyDao;

    @Override
    public List<SysCompany> getSysCompanyList(Map<String, Object> map) {
        return sysCompanyDao.queryList(map);
    }

    @Override
    public int addSysCompany(Map<String, Object> map) {
        return sysCompanyDao.save(map);
    }

    @Override
    public int updateSysCompanyInfo(Map<String, Object> map) {
        return sysCompanyDao.update(map);
    }

    @Override
    public int deleteSysCompanyInfo(Map<String, Object> map) {
        return sysCompanyDao.delete(map);
    }
}
