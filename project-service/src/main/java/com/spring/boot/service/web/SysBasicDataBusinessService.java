package com.spring.boot.service.web;

import com.spring.boot.bean.master.SysBasicData;
import com.spring.boot.bean.master.SysCompany;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysBasicDataBusinessService {

    /**
     * 获取公司列表数据
     *
     * @return
     */
    List<SysBasicData> sysBasicDataAnalysisData(Map<String, Object> map);

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
    List<SysBasicData> sysBasicDataAnalysisList(Map<String, Object> map);

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
}
