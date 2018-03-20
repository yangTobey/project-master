package com.spring.boot.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 *
 */
public interface SysQualityManageService {
    /**
     * 获取列表数据
     * @return
     */
    Map<String,Object> getSysQualityManageList(int limit, int offset);

    /**
     * 新增质量管理信息
     * @param companyId
     * @param year
     * @param month
     * @param qualityCheck
     * @param qualityCheckPass
     * @param qualityCheckFail
     * @param securityEvent
     * @param qualityCheckUnmodified
     * @return
     */
    int addSysQualityManage(long companyId, int year, int month,int qualityCheck,int qualityCheckPass,int qualityCheckFail,int securityEvent,int qualityCheckUnmodified);

    /**
     * 更新信息
     * @param qualityId
     * @param companyId
     * @param year
     * @param month
     * @param qualityCheck
     * @param qualityCheckPass
     * @param qualityCheckFail
     * @param securityEvent
     * @param qualityCheckUnmodified
     * @return
     */
    int updateSysQualityManage(long qualityId,long companyId, int year, int month,int qualityCheck,int qualityCheckPass,int qualityCheckFail,int securityEvent,int qualityCheckUnmodified);
    /**
     * 删除信息
     * @param qualityId id
     * @return
     */
    int deleteSysQualityManageById(long qualityId);
    /**
     * 根据公司id获取公司数据
     * @return
     */
    Map<String,Object> findSysQualityManageById(long companyId);
}
