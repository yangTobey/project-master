package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.SysContractFile;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.dao.web.master.SysBasicDataDao;
import com.spring.boot.dao.web.master.SysFileDao;
import com.spring.boot.service.web.SysBasicDataBusinessService;
import com.spring.boot.service.web.SysFileBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysFileBusinessServiceImpl implements SysFileBusinessService {
    @Autowired
    private SysFileDao sysFileDao;

    @Override
    public SysQualityManageFile fileSysQualityManageFileById(Long fileId) {
        return sysFileDao.fileSysQualityManageFileById(fileId);
    }

    @Override
    public SysProjectEnergyFile fileSysProjectEnergyFileById(Long fileId) {
        return sysFileDao.fileSysProjectEnergyFileById(fileId);
    }

    @Override
    public SysContractFile fileSysContractFileById(Long fileId) {
        return sysFileDao.fileSysContractFileById(fileId);
    }

    @Override
    public int deleteSysQualityManageFileById(Long fileId) {
        return sysFileDao.deleteSysQualityManageFileById(fileId);
    }

    @Override
    public int deleteSysProjectEnergyFileById(Long fileId) {
        return sysFileDao.deleteSysProjectEnergyFileById(fileId);
    }

    @Override
    public int deleteSysContractFileById(Long fileId) {
        return sysFileDao.deleteSysContractFileById(fileId);
    }
}
