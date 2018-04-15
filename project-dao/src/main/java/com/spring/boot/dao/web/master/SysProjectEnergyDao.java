package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysEnergy;
import com.spring.boot.bean.master.SysProject;
import com.spring.boot.bean.master.SysProjectEnergy;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysProjectEnergyDao extends BaseDao<SysBasicDataEntity> {
    /**
     * 根据公司id、年份、月份查找系统记录
     * @return
     */
    SysProjectEnergy findSysProjectEnergyRecord(Long companyId,Integer year,Integer month);
    /**
     * 添加工程能耗基础附件信息
     * @param sysProjectEnergyFile
     * @return
     */
    int addSysProjectEnergyFile(SysProjectEnergyFile sysProjectEnergyFile);

    /**
     * 添加工程能耗基础信息
     * @param sysProjectEnergy
     * @return
     */
    int addSysProjectEnergy(SysProjectEnergy sysProjectEnergy);
    /**
     * 添加工程信息
     * @param sysProject
     * @return
     */
    int addSysProject(SysProject sysProject);
    /**
     * 添加能耗信息
     * @return
     */
    int addSysEergy(SysEnergy sysEnergy);
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
    /**
     * 添加能耗信息
     * @return
     */
    int updateSysEnergy(Map<String,Object> map);
    /**
     * 添加工程信息
     * @param map
     * @return
     */
    int updateSysProject(Map<String,Object> map);
    /**
     * 根据主键id查找信息
     * @param detailsId
     * @return
     */
    SysProjectEnergy findSysProjectEnergyById(Long detailsId);
}
