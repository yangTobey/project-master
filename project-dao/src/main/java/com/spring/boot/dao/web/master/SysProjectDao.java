package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysEnergy;
import com.spring.boot.bean.master.SysProject;
import com.spring.boot.bean.master.SysProjectEnergy;
import com.spring.boot.bean.master.SysProjectEnergyFile;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;
import com.spring.boot.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysProjectDao extends BaseDao<SysProject> {

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
    int updateSysProject(Map<String, Object> map);
    /**
     * 根据公司id、年份、月份查找系统记录
     * * 注：如果mybatis需要使用if:test判断参数，需要在dao层加上@Param
     * @return
     */
    SysProject findSysProjectRecord(@Param("companyId")Long companyId, @Param("year")Integer year, @Param("month")Integer month);
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
     * 根据id查找附件信息
     * @param projectId
     * @return
     */
    List<SysProjectEnergyFile> findSysProjectEnergyFileById(Long projectId);
    /**
     * 报表年度统计信息
     * @param map
     * @return
     */
    SysProject sysProjectEnergyAnalysisForYear(Map<String, Object> map);
    /**
     * 报表月度（当月）统计信息
     * @param
     * @return
     */
    SysProject sysProjectEnergyByYearAndMonthAndCompanyId(@Param("year")Integer year, @Param("month")Integer month,@Param("sysUserCompanyIds")List<Long> sysUserCompanyIds);
    /**
     * 根据年份、公司id查找年度工程能耗分析信息
     * @param map
     * @return
     */
    List<SysProject> sysProjectEnergyAnalysisForMonth(Map<String, Object> map);

}
