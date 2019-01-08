package com.spring.boot.service;

import com.spring.boot.bean.master.SysQualityManage;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysQualityManageService {
    /**
     * 获取品质管理年度报表数据
     * @return
     */
    Map<String,Object> sysQualityManageAnalysis(Long companyId, Integer year, Integer month);
    /**
     * 获取列表数据
     * @return
     */
    Map<String,Object> getSysQualityManageList(long companyId,int year,int limit, int offset,Integer month);

    /**
     * 新增质量管理信息
     * @param companyId
     * @param year
     * @param month
     * @param qualityCheck
     * @param qualityCheckPass
     * @param securityEvent
     * @param qualityCheckUnmodified
     * @return
     */
    Map<String,Object> addSysQualityManage(SysQualityManage sysQualityManage);

    /**
     * 更新信息
     * @param qualityId
     * @param companyId
     * @param year
     * @param month
     * @param qualityCheck
     * @param qualityCheckPass
     * @param securityEvent
     * @param qualityCheckUnmodified
     * @return
     */
    Map<String,Object> updateSysQualityManage(SysQualityManage sysQualityManage);
    /**
     * 删除信息
     * @param qualityId id
     * @return
     */
    Map<String,Object> deleteSysQualityManageById(long qualityId);
    /**
     * 根据id获取公司数据
     * @return
     */
    Map<String,Object> findSysQualityManageById(Long qualityId);
    /**
     * 根据id获取附件文档信息
     * @return
     */
    Map<String,Object> findSysQualityManageFileById(long qualityId);

    /**
     * 将品质年度、月度统计报表信息放进redis缓存
     */
    void setDataToRedis();
}
