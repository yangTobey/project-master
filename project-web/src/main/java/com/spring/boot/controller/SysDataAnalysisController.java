package com.spring.boot.controller;

import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.util.R;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 物业大屏数据分析统计控制类
 * Created by Administrator on 2018/1/25.
 */
@RestController
@RequestMapping("/sysPropertyData")
public class SysDataAnalysisController {
    private static final Logger logger = Logger.getLogger(SysDataAnalysisController.class);
    @Autowired
    private SysDataAnalysisService sysDataAnalysisService;

    /**
     * 获取物业界面分析统计数据
     *
     * @return
     */
    @RequestMapping(value = "/sysPropertyDataAnalysis", method = RequestMethod.POST)
    public R sysPropertyDataAnalysis() {
        Map<String, Object> map = sysDataAnalysisService.sysPropertyDataAnalysis();
        return R.ok().put(200, map, "获取成功！");
    }

    /**
     * 获取财务界面分析统计数据
     *
     * @return
     */
    @RequestMapping(value = "/sysFinancialDataAnalysis", method = RequestMethod.POST)
    public R sysFinancialDataAnalysis() {
        Map<String, Object> map = sysDataAnalysisService.sysFinancialDataAnalysis();
        return R.ok().put(200, map, "获取成功！");
    }

    /**
     * 获取物业大屏界面工单统计信息
     *
     * @return
     */
    @RequestMapping(value = "/sysRepairOrder", method = RequestMethod.POST)
    public R sysRepairOrder() {
        Map<String, Object> map = sysDataAnalysisService.sysRepairOrder();
        return R.ok().put(200, map, "获取成功！");
    }
}
