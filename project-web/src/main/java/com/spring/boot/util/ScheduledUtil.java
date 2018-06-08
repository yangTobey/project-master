package com.spring.boot.util;

import com.spring.boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 系统定时任务管理工具类
 * Created by xiaoyang on 2018/5/16.
 */
@Component
public class ScheduledUtil {

    @Autowired
    private SysContractService sysContractService;
    @Autowired
    private SysBasicDataService sysBasicDataService;
    @Autowired
    private SysFinancialService sysFinancialService;
    @Autowired
    private SysLaborCostService sysLaborCostService;
    @Autowired
    private SysProjectEnergyService sysProjectEnergyService;
    @Autowired
    private SysQualityManageService sysQualityManageService;

    /**
     * 合同执行状态修改，定时任务，每30分钟执行一次
     *
     * @return
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void scheduledTask() {
        sysContractService.updateSysContractExpire();
    }

    /**
     * 存放基础信息到redis
     */
    @Scheduled(cron = "0 0 0 10 * ?")
    public void setBasicDataAnalysisDataToRedis() {
        System.out.println("基础信息定时器进来！");
        sysBasicDataService.setBasicDataAnalysisDataToRedis();
    }

    /**
     * 保存收费情况详细信息到redis缓存
     */
    @Scheduled(cron = "0 0 0 10 * ?")
    public void setSysChargeToRedis() {
        System.out.println("收费情况定时器进来！");
        sysFinancialService.setSysChargeToRedis();
    }

    /**
     * 保存应收账款详细信息到redis缓存
     */
    @Scheduled(cron = "0 0 0 10 * ?")
    public void setSysAccountsReceivableToRedis() {
        System.out.println("应收账款定时器进来！");
        sysFinancialService.setSysAccountsReceivableToRedis();
    }

    /**
     * 保存预算信息到redis缓存
     */
    @Scheduled(cron = "0 0 0 10 * ?")
    public void setSysBudgetDetailsToRedis() {
        System.out.println("预算信息定时器进来！");
        sysFinancialService.setSysBudgetDetailsToRedis();
    }

    /**
     * 将人员成本统计信息存储到redis缓存中
     */
    @Scheduled(cron = "0 0 0 10 * ?")
    public void setSysLaborCostDateToRedis() {
        System.out.println("人员成本定时器进来！");
        sysLaborCostService.setDateToRedis();
    }

    /**
     * 将工程能耗统计信息存储到redis缓存中
     */
    @Scheduled(cron = "0 0 0 10 * ?")
    public void setSysProjectEnergyDateToRedis() {
        System.out.println("工程能耗定时器进来！");
        sysProjectEnergyService.setDataToRedis();
    }

    /**
     * 将品质管理统计信息存储到redis缓存中
     */
    @Scheduled(cron = "0 0 0 10 * ?")
    public void setSysQualityManageDateToRedis() {
        System.out.println("品质管理定时器进来！");
        sysQualityManageService.setDataToRedis();
    }
}
