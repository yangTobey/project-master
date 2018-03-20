package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.dao.web.master.SysQualityManageDao;
import com.spring.boot.service.web.SysCompanyBusinessService;
import com.spring.boot.service.web.SysQualityManageBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysQualityManageBusinessServiceImpl implements SysQualityManageBusinessService {
    @Autowired
    private SysQualityManageDao sysQualityManageDao;

    @Override
    public List<SysQualityManage> getSysQualityManageList(Map<String, Object> map) {
        return sysQualityManageDao.queryList(map);
    }

    @Override
    public int getSysQualityManageListTotal(Map<String, Object> map) {
        return sysQualityManageDao.getSysQualityManageListTotal(map);
    }


    @Override
    public int addSysQualityManage(Map<String, Object> map) {
        return sysQualityManageDao.addSysQualityManage(map);
    }

    @Override
    public int updateSysQualityManage(Map<String, Object> map) {
        return sysQualityManageDao.updateSysQualityManage(map);
    }

    @Override
    public int deleteSysQualityManageById(Map<String, Object> map) {
        return sysQualityManageDao.deleteSysQualityManageById(map);
    }

    @Override
    public SysQualityManage findSysQualityManageById(Map<String, Object> map) {
        return sysQualityManageDao.findSysQualityManageById(map);
    }
}
