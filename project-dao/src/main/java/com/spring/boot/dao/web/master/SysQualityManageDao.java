package com.spring.boot.dao.web.master;

import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.SysQualityManage;
import com.spring.boot.bean.master.entity.SysQualityManageEntity;
import com.spring.boot.dao.BaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysQualityManageDao extends BaseDao<SysQualityManage> {
    /**
     * 获取品质管理年度报表数据
     * @return
     */
    SysQualityManageEntity sysQualityManageAnalysisForYear(Map<String, Object> map);
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
     * @param map
     * @return
     */
    int addSysQualityManage(Map<String, Object> map);

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
}
