package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysCompanyFile;
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
    public int getSysCompanyListTotal(Map<String, Object> map) {
        return sysCompanyDao.getSysCompanyListTotal(map);
    }

    @Override
    public int querySubsidiaryCount(Map<String, Object> map) {
        return sysCompanyDao.querySubsidiaryCount(map);
    }

    @Override
    public int addSysCompany(SysCompany sysCompany) {
        return sysCompanyDao.save(sysCompany);
    }

    @Override
    public int updateSysCompanyInfo(Map<String, Object> map) {
        return sysCompanyDao.update(map);
    }

    @Override
    public int deleteSysCompanyById(Map<String, Object> map) {
        return sysCompanyDao.delete(map);
    }

    @Override
    public SysCompany findSysCompanyByCompanyId(Map<String, Object> map) {
        return sysCompanyDao.findSysCompanyByCompanyId(map);
    }

    @Override
    public List<SysCompany> getAllSysCompany() {
        return sysCompanyDao.getAllSysCompany();
    }

    @Override
    public List<Long> getAllSysCompanyId() {
        return sysCompanyDao.getAllSysCompanyId();
    }

    @Override
    public int addSysCompanyFileInfo(SysCompanyFile sysCompanyFile) {
        return sysCompanyDao.addSysCompanyFileInfo(sysCompanyFile);
    }

    @Override
    public List<SysCompanyFile> findSysCompanyFileByCompanyId(Long companyId) {
        return sysCompanyDao.findSysCompanyFileByCompanyId(companyId);
    }
}
