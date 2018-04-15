package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysEnergy;
import com.spring.boot.bean.master.SysProject;
import com.spring.boot.bean.master.SysProjectEnergy;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysProjectEnergyBusinessService {
    /**
     * 根据公司id、年份、月份查找系统记录
     * @return
     */
    SysProjectEnergy findSysProjectEnergyRecord(Long companyId,Integer year,Integer month);
    /**
     * 添加工程能耗基础信息
     * @param sysProjectEnergy
     * @return
     */
    int addSysProjectEnergy(SysProjectEnergy sysProjectEnergy);
    /**
     * 添加工程能耗基础附件信息
     * @param sysProjectEnergyFile
     * @return
     */
    int addSysProjectEnergyFile(SysProjectEnergyFile sysProjectEnergyFile);

    /**
     * 更新工程能耗信息
     * @param map
     * @return
     */
    int updateSysProjectEnergy(Map<String,Object> map);
    /**
     * 更新工程能耗基础附件信息
     * @param map
     * @return
     */
    int updateSysProjectEnergyFile(Map<String,Object> map);


    int deleteSysProjectEnergy(Long detailsId);

    /**
     * 根据主键id查找信息
     * @param detailsId
     * @return
     */
    SysProjectEnergy findSysProjectEnergyById(Long detailsId);


}
