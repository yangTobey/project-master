package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysContract;
import com.spring.boot.bean.master.SysContractFile;
import com.spring.boot.bean.master.SysContractType;
import com.spring.boot.bean.master.SysEnergy;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysEnergyBusinessService {
    /**
     * 添加能耗信息
     * @return
     */
    int updateSysEnergy(Map<String,Object> map);
    /**
     * 添加能耗信息
     * @return
     */
    int addSysEergy(SysEnergy sysEnergy);
}
