package com.spring.boot.util;

import com.spring.boot.service.SysContractService;
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
    /**
     * 定时任务，每一分钟执行一次
     * @return
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void ScheduledTask(){
        sysContractService.updateSysContractExpire();
    }
}
