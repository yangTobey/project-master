package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.bean.master.entity.SysQualityManageEntity;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.dao.web.master.SysQualityManageDao;
import com.spring.boot.dao.web.master.SysQualityManageFileDao;
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
    public SysQualityManageEntity sysQualityManageAnalysisForYear(Map<String, Object> map) {
        return sysQualityManageDao.sysQualityManageAnalysisForYear(map);
    }

    @Override
    public SysQualityManageEntity sysQualityManageAnalysisForMonth(Map<String, Object> map) {
        return sysQualityManageDao.sysQualityManageAnalysisForMonth(map);
    }

    @Override
    public List<SysQualityManage> sysQualityManageAnalysisList(Map<String, Object> map) {
        return sysQualityManageDao.sysQualityManageAnalysisList(map);
    }

    @Override
    public List<SysQualityManage> getSysQualityManageList(Map<String, Object> map) {
        return sysQualityManageDao.queryList(map);
    }

    @Override
    public int getSysQualityManageListTotal(Map<String, Object> map) {
        return sysQualityManageDao.getSysQualityManageListTotal(map);
    }


    @Override
    public int addSysQualityManage(SysQualityManage sysQualityManage) {
        return sysQualityManageDao.addSysQualityManage(sysQualityManage);
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

    @Override
    public List<SysQualityManageFile> findSysQualityManageFileById(Long qualityId) {
        return sysQualityManageDao.findSysQualityManageFileById(qualityId);
    }

    @Override
    public int addSysQualityManageFile(SysQualityManageFile sysQualityManageFile) {
        return sysQualityManageDao.addSysQualityManageFile(sysQualityManageFile);
    }
}
