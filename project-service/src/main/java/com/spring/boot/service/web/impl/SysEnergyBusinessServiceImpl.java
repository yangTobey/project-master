package com.spring.boot.service.web.impl;

import com.spring.boot.bean.master.SysEnergy;
import com.spring.boot.dao.web.master.SysEnergyDao;
import com.spring.boot.service.web.SysEnergyBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
@Service
public class SysEnergyBusinessServiceImpl implements SysEnergyBusinessService {
    @Autowired
    private SysEnergyDao sysEnergyDao;

    @Override
    public int updateSysEnergy(Map<String, Object> map) {
        return sysEnergyDao.updateSysEnergy(map);
    }

    @Override
    public int addSysEergy(SysEnergy sysEnergy) {
        return sysEnergyDao.addSysEnergy(sysEnergy);
    }
}
