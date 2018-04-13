package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysProjectEnergy;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysProjectEnergyBusinessService {
    int addSysProjectEnergy();
    int updateSysProjectEnergy();
    int deleteSysProjectEnergy();
    SysProjectEnergy findSysProjectEnergyById();


}
