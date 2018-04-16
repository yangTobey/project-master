package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysEnergy;
import com.spring.boot.bean.master.SysProject;
import com.spring.boot.bean.master.SysProjectEnergy;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.dao.web.master.SysProjectEnergyDao;
import com.spring.boot.service.web.SysProjectEnergyBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysProjectEnergyBusinessServiceImpl implements SysProjectEnergyBusinessService {
    @Autowired
    private SysProjectEnergyDao sysProjectEnergyDao;

    @Override
    public SysProjectEnergy findSysProjectEnergyRecord(Long companyId, Integer year, Integer month) {
        return sysProjectEnergyDao.findSysProjectEnergyRecord(companyId,year,month);
    }

    @Override
    public int addSysProjectEnergy(SysProjectEnergy sysProjectEnergy) {
        return sysProjectEnergyDao.addSysProjectEnergy(sysProjectEnergy);
    }




    @Override
    public int updateSysProjectEnergy(Map<String, Object> map) {
        return sysProjectEnergyDao.updateSysProjectEnergy(map);
    }

    @Override
    public int updateSysProjectEnergyFile(Map<String, Object> map) {
        return sysProjectEnergyDao.updateSysProjectEnergyFile(map);
    }



    @Override
    public int deleteSysProjectEnergy(Long projectId) {
        return sysProjectEnergyDao.delete(projectId);
    }

    @Override
    public SysProjectEnergy findSysProjectEnergyById(Long projectId) {
        return null;
    }
}
