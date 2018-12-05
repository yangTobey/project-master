package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.SysBasicDataFile;
import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.dao.web.master.SysBasicDataDao;
import com.spring.boot.dao.web.master.SysCompanyDao;
import com.spring.boot.service.web.SysBasicDataBusinessService;
import com.spring.boot.service.web.SysCompanyBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysBasicDataBusinessServiceImpl implements SysBasicDataBusinessService {
    @Autowired
    private SysBasicDataDao sysBasicDataDao;

    @Override
    public SysBasicDataEntity sysBasicDataAnalysisData(Map<String, Object> map) {
        return sysBasicDataDao.sysBasicDataAnalysisData(map);
    }

    @Override
    public SysBasicData sysBasicDataRecord(Long companyId, Integer year, Integer month) {
        return sysBasicDataDao.sysBasicDataRecord(companyId,year,month);
    }

    @Override
    public List<SysBasicDataEntity> sysBasicDataAnalysisList(Map<String, Object> map) {
        return sysBasicDataDao.sysBasicDataAnalysisList(map);
    }

    @Override
    public int sysBasicDataAnalysisListTotal(Map<String, Object> map) {
        return sysBasicDataDao.sysBasicDataAnalysisListTotal(map);
    }

    @Override
    public int addSysBasicData(SysBasicData sysBasicData) {
        return sysBasicDataDao.addSysBasicData(sysBasicData);
    }

    @Override
    public int updateSysBasicData(Map<String, Object> map) {
        return sysBasicDataDao.updateSysBasicData(map);
    }

    @Override
    public int deleteSysBasicData(Map<String, Object> map) {
        return sysBasicDataDao.deleteSysBasicData(map);
    }

    @Override
    public SysBasicDataEntity findSysBasicDataById(Map<String, Object> map) {
        return sysBasicDataDao.findSysBasicDataById(map);
    }

    @Override
    public int addSysBasicDataFile(SysBasicDataFile sysBasicDataFile) {
        return sysBasicDataDao.addSysBasicDataFile(sysBasicDataFile);
    }

    @Override
    public List<SysBasicDataFile> findSysBasicDataFileByBasicId(Map<String, Object> map) {
        return sysBasicDataDao.findSysBasicDataFileByBasicId(map);
    }
}
