package com.spring.boot.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/1/25.
 */
public interface SysDataAnalysisService {
    /**
     * 获取物业界面分析统计数据
     *
     * @return
     */
    Map<String, Object> sysPropertyDataAnalysis();

    /**
     * 获取财务界面分析统计数据
     *
     * @return
     */
    Map<String, Object> sysFinancialDataAnalysis();

    /**
     * 获取物业大屏界面工单统计信息
     * @return
     */
    Map<String, Object> sysRepairOrder();

}
