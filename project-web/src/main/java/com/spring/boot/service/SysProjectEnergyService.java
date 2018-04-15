package com.spring.boot.service;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysProjectEnergyService {
    /**
     * 新增信息
     * @param companyId
     * @param year
     * @param month
     * @param projectUnfinishedTotal
     * @param projectFinishedTotal
     * @param monthConsumptionElectricity
     * @param monthConsumptionWater
     * @param fileInfo
     * @return
     */
    Map<String,Object> addSysProjectEnergy(Long companyId,Integer year,Integer month,Integer projectUnfinishedTotal,Integer projectFinishedTotal,Integer monthConsumptionElectricity,Integer monthConsumptionWater,String fileInfo);

    /**
     * 更新信息
     * @param detailsId
     * @param companyId
     * @param year
     * @param month
     * @param projectUnfinishedTotal
     * @param projectFinishedTotal
     * @param monthConsumptionElectricity
     * @param monthConsumptionWater
     * @param fileInfo
     * @return
     */
    Map<String,Object> updateSysProjectEnergy(Long detailsId,Long companyId,Integer year,Integer month,Integer projectUnfinishedTotal,Integer projectFinishedTotal,Integer monthConsumptionElectricity,Integer monthConsumptionWater,String fileInfo);

    /**
     * 删除信息
     * @param detailsId
     * @return
     */
    Map<String,Object> deleteSysProjectEnergy(Long detailsId);
    /**
     * 根据主键id查找信息
     * @param detailsId
     * @return
     */
    Map<String,Object> findSysProjectEnergyById(Long detailsId);

    /**
     * 查找列表信息
     * @param companyId
     * @param year
     * @return
     */
    Map<String,Object> sysProjectEnergyList(Long companyId, Integer year);
    /**
     * 查找报表统计信息
     * @param companyId
     * @return
     */
    Map<String,Object> sysProjectEnergyAnalysis(Long companyId);


}
