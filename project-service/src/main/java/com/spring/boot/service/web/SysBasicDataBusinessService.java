package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.SysCompany;
import com.spring.boot.bean.master.entity.SysBasicDataEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysBasicDataBusinessService {

    /**
     * 获取公司基础数据分析统计分析信息
     *
     * @return
     */
    SysBasicDataEntity sysBasicDataAnalysisData(Map<String, Object> map);

    /**
     * 获取基础数据列表数据条数
     *
     * @return
     */
   int sysBasicDataAnalysisListTotal(Map<String, Object> map);
    /**
     * 获取基础数据列表数据
     *
     * @return
     */
    List<SysBasicDataEntity> sysBasicDataAnalysisList(Map<String, Object> map);

    /**
     * 新增信息
     *
     * @param map
     * @return
     */
    int addSysBasicData(Map<String, Object> map);

    /**
     * 更新信息
     *
     * @param map
     * @return
     */
    int updateSysBasicData(Map<String, Object> map);

    /**
     * 删除信息
     *
     * @param map
     * @return
     */
    int deleteSysBasicData(Map<String, Object> map);

    /**
     * 根据id查找基础数据信息
     *
     * @return
     */
    SysBasicDataEntity findSysBasicDataById(Map<String, Object> map);
}
