package com.spring.boot.controller;

import com.spring.boot.service.SysMenuService;
import com.spring.boot.service.SysPropertyDataAnalysisService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import com.spring.boot.util.UtilHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 物业大屏数据分析统计控制类
 * Created by Administrator on 2018/1/25.
 */
@RestController
@RequestMapping("/sysPropertyData")
public class SysPropertyDataAnalysisController {
    private static final Logger logger = Logger.getLogger(SysPropertyDataAnalysisController.class);
    @Autowired
    private SysPropertyDataAnalysisService sysPropertyDataAnalysisService;
    /**
     * 获取分析统计数据
     *
     * @return
     */
    @RequestMapping(value = "/sysPropertyDataAnalysis", method = RequestMethod.GET)
    public R sysPropertyDataAnalysis() {
        Map<String, Object> map = sysPropertyDataAnalysisService.sysPropertyDataAnalysis();
        return R.ok().put(200, map,"获取成功！");
    }


}
