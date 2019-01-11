package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysChargeDetails;
import com.spring.boot.bean.master.SysEnergy;
import com.spring.boot.bean.master.SysProject;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.dao.web.master.SysChargeDao;
import com.spring.boot.dao.web.master.SysProjectDao;
import com.spring.boot.service.web.SysChargeBusinessService;
import com.spring.boot.service.web.SysProjectBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysProjectBusinessServiceImpl implements SysProjectBusinessService {
    @Autowired
    private SysProjectDao sysProjectDao;

    @Override
    public int updateSysProject(Map<String, Object> map) {
        return sysProjectDao.updateSysProject(map);
    }

    @Override
    public int addSysProject(SysProject sysProject) {
        return sysProjectDao.addSysProject(sysProject);
    }

    @Override
    public SysProject findSysProjectRecord(Long companyId, Integer year, Integer month) {
        return sysProjectDao.findSysProjectRecord(companyId,year,month);
    }

    @Override
    public int deleteSysProject(Long projectId) {
        return sysProjectDao.deleteSysProject(projectId);
    }

    @Override
    public int addSysProjectEnergyFile(SysProjectEnergyFile sysProjectEnergyFile) {
        return sysProjectDao.addSysProjectEnergyFile(sysProjectEnergyFile);
    }


    @Override
    public SysProject findSysProjectEnergyById(Long projectId) {
        return sysProjectDao.findSysProjectEnergyById(projectId);
    }

    @Override
    public List<SysProject> sysProjectEnergyList(Map<String, Object> map) {
        return sysProjectDao.sysProjectEnergyList(map);
    }

    @Override
    public int sysProjectEnergyListTotal(Map<String, Object> map) {
        return sysProjectDao.sysProjectEnergyListTotal(map);
    }

    @Override
    public List<SysProjectEnergyFile> findSysProjectEnergyFileById(Long projectId) {
        return sysProjectDao.findSysProjectEnergyFileById(projectId);
    }

    @Override
    public SysProject sysProjectEnergyAnalysisForYear(Map<String, Object> map) {
        return sysProjectDao.sysProjectEnergyAnalysisForYear(map);
    }

    @Override
    public SysProject sysProjectEnergyByYearAndMonthAndCompanyId(Integer year, Integer month,List<Long> sysUserCompanyIds) {
        return sysProjectDao.sysProjectEnergyByYearAndMonthAndCompanyId(year,month,sysUserCompanyIds);
    }

    @Override
    public List<SysProject> sysProjectEnergyAnalysisForMonth(Map<String, Object> map) {
        return sysProjectDao.sysProjectEnergyAnalysisForMonth(map);
    }

    @Override
    public SysProject sysProjectUnfinishedForYear(Map<String, Object> map) {
        return sysProjectDao.sysProjectUnfinishedForYear(map);
    }
}
