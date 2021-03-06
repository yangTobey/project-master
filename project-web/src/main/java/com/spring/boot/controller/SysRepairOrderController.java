package com.spring.boot.controller;

import com.spring.boot.service.SysRepairOrderService;
import com.spring.boot.service.SysUserService;
import com.spring.boot.util.R;
import com.spring.boot.util.ShiroUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by xiaoyang on 2018/4/23.
 */
@Component
public class SysRepairOrderController {

    @Autowired
    private SysRepairOrderService sysRepairOrderService;

    /**
     * 获取瑞加+系统的工单统计信息
     * @return
     */
    //@Scheduled(cron = "0/30 * * * * *")
    public void getRepairOrder(){
        sysRepairOrderService.getRepairOrder();

    }
}
