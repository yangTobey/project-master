package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysProjectEnergy;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.dao.web.master.SysBasicDataDao;
import com.spring.boot.service.web.SysBasicDataBusinessService;
import com.spring.boot.service.web.SysProjectEnergyBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysProjectEnergyBusinessServiceImpl implements SysProjectEnergyBusinessService {
    @Autowired
    private SysBasicDataDao sysBasicDataDao;

    @Override
    public int addSysProjectEnergy() {
        return 0;
    }

    @Override
    public int updateSysProjectEnergy() {
        return 0;
    }

    @Override
    public int deleteSysProjectEnergy() {
        return 0;
    }

    @Override
    public SysProjectEnergy findSysProjectEnergyById() {
        return null;
    }
}
