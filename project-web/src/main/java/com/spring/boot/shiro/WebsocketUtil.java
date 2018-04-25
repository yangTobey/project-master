package com.spring.boot.shiro;

import com.spring.boot.service.SysDataAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by Administrator on 2018/4/25.
 */
@Component
public class WebsocketUtil {
    @Autowired
    private SysDataAnalysisService sysDataAnalysisService;

    public static WebsocketUtil websocketUtil;  // 关键2

    // 关键3
    @PostConstruct
    public void init() {
        websocketUtil = this;
        websocketUtil.sysDataAnalysisService = this.sysDataAnalysisService;
    }
    public  void sendMessage(String message){
        //物业大屏界面连接成功后请求信息
        if("property".equals(message)){
            sysDataAnalysisService.sysPropertyDataAnalysis();
        }else if("financial".equals(message)){
            //财务大屏界面连接成功后请求信息
            sysDataAnalysisService.sysFinancialDataAnalysis();
        }

    }
}
