package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.bean.master.SysQualityManageFile;
import com.spring.boot.bean.master.entity.SysQualityManageEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysQualityManageBusinessService {
    /**
     * 获取品质管理年、月度报表数据
     * @return
     */
    SysQualityManageEntity sysQualityManageAnalysisForYear(Map<String, Object> map);

    /**
     * 根据公司id、年份、月份查找系统记录
     * @param companyId
     * @param year
     * @param month
     * @return
     */
    SysQualityManage sysQualityManageRecord(Long companyId,Integer year,Integer month);
    /**
     * 获取品质管理月度报表数据
     * @return
     */
    SysQualityManageEntity sysQualityManageAnalysisForMonth(Map<String, Object> map);
    /**
     * 根据年份和月份查找品质管理列表信息
     * @return
     */
    List<SysQualityManage> sysQualityManageAnalysisList(Map<String, Object> map);
    /**
     * 获取列表数据
     * @return
     */
    List<SysQualityManage> getSysQualityManageList(Map<String, Object> map);
    /**
     * 获取列表数据条数
     * @param map
     * @return
     */
    int getSysQualityManageListTotal(Map<String, Object> map);
    /**
     * 新增质量管理信息
     * @param sysQualityManage
     * @return
     */
    int addSysQualityManage(SysQualityManage sysQualityManage);
    /**
     * 新增质量管理附件文档信息
     * @param sysQualityManageFile
     * @return
     */
    int addSysQualityManageFile(SysQualityManageFile sysQualityManageFile);
    /**
     * 更新信息
     * @param map
     * @return
     */
    int updateSysQualityManage(Map<String, Object> map);
    /**
     * 删除信息
     * @param map
     * @return
     */
    int deleteSysQualityManageById(Map<String, Object> map);
    /**
     * 根据公司id获取公司数据
     * @return
     */
    SysQualityManage findSysQualityManageById(Map<String, Object> map);

    /**
     * 根据id获取附件文档信息
     * @return
     */
    List<SysQualityManageFile> findSysQualityManageFileById(Long qualityId);

    /**
     * 根据品质id删除文档附件信息（用于更新时操作）
     * @param qualityId
     * @return
     */
    int deleteSysQualityManageFileByQualityId(Long qualityId);
}
