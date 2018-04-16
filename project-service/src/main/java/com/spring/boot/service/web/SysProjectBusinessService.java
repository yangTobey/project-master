package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysProject;
import com.spring.boot.bean.master.SysProjectEnergy;
import com.spring.boot.bean.master.SysProjectEnergyFile;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/6.
 */
public interface SysProjectBusinessService {
    /**
     * 添加工程信息
     * @param sysProject
     * @return
     */
    int addSysProject(SysProject sysProject);
    /**
     * 添加工程信息
     * @param map
     * @return
     */
    int updateSysProject(Map<String,Object> map);
    /**
     * 根据公司id、年份、月份查找系统记录
     * @return
     */
    SysProject findSysProjectRecord(Long companyId, Integer year, Integer month);

    /**
     * 删除信息
     * @param projectId
     * @return
     */
    int deleteSysProject(Long projectId);
    /**
     * 添加工程能耗基础附件信息
     * @param sysProjectEnergyFile
     * @return
     */
    int addSysProjectEnergyFile(SysProjectEnergyFile sysProjectEnergyFile);

    /**
     * 根据主键id查找信息
     * @param projectId
     * @return
     */
    SysProject findSysProjectEnergyById(Long projectId);
    /**
     * 根据id查找附件信息
     * @param projectId
     * @return
     */
    List<SysProjectEnergyFile> findSysProjectEnergyFileById(Long projectId);
    /**
     * 查找列表信息
     * @param map
     * @return
     */
    List<SysProject> sysProjectEnergyList(Map<String, Object> map);
    /**
     * 列表条数
     * @param map
     * @return
     */
    int sysProjectEnergyListTotal(Map<String, Object> map);
    /**
     * 报表统计信息
     * @param map
     * @return
     */
    SysProject sysProjectEnergyAnalysis(Map<String, Object> map);
}
