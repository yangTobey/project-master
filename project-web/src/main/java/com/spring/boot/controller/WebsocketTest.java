package com.spring.boot.controller;

import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.util.JsonUtils;
import com.spring.boot.util.R;
import com.spring.boot.websocket.PropertyWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/24.
 */
@RestController
@RequestMapping("/websocketTest")
public class WebsocketTest {
    @Autowired
    private SysDataAnalysisService sysyDataAnalysisService;
    @RequestMapping(value = "/websocketInfo", method = RequestMethod.GET)
    public R websocket() {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            System.out.println("websocket进来了！！");
            result.put("key", "operationResult,I am coming,my dear!");
            Map<String, Object> map = sysyDataAnalysisService.sysPropertyDataAnalysis();
            PropertyWebSocket.sendInfo(JsonUtils.obj2JsonString(map));
        } catch (IOException e) {
            result.put("operationResult", true);
        }
        return R.ok(result);
    }

}
