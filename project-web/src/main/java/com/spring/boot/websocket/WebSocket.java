package com.spring.boot.websocket;

import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.service.SysRepairOrderService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * websocket消息推送管理类
 * Created by xiaoyang on 2018/4/25.
 */
public class WebSocket extends TextWebSocketHandler {
    private static final Logger logger = Logger.getLogger(WebSocket.class);
    @Autowired
    private SysDataAnalysisService sysDataAnalysisService;
    @Autowired
    private SysRepairOrderService sysRepairOrderService;

    /**
     * concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket对象。
     */
    private static final List<WebSocketSession> COUNTS = new ArrayList<>();


    /**
     * 关闭连接后
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        logger.info("websocket关闭成功！");
        super.afterConnectionClosed(session, status);
    }

    /**
     * 建立连接成功
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("websocket连接成功！");
        COUNTS.add(session);
        super.afterConnectionEstablished(session);
    }

    /**
     * 接收客户端信息（或接收浏览器发送信息）
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //物业大屏界面连接成功后请求信息
        if ("property".equals(message.getPayload())) {
            sysDataAnalysisService.sysPropertyDataAnalysis();
        } else if ("financial".equals(message.getPayload())) {
            //财务大屏界面连接成功后请求信息
            sysDataAnalysisService.sysFinancialDataAnalysis();
        }else if("repairOrder".equals(message.getPayload())){
            sysRepairOrderService.getRepairOrder();
        }
        //System.out.println(message.getPayload());
        //TextMessage msg = new TextMessage(message.getPayload());
        //session.sendMessage(msg);
    }

    /**
     * 向客户端或者浏览器发送信息
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        super.handleMessage(session, message);
    }

    /**
     * 建立连接握手失败或者出现错误
     *
     * @param session
     * @param exception
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        logger.info("websocket连接失败！");
        super.handleTransportError(session, exception);
        COUNTS.remove(session);
    }

    /**
     * 群发自定义消息
     *
     * @param message 信息内容
     * @throws IOException
     */
    public static void sendInfo(String message) throws IOException {
        //遍历记录的session，取出符合条件的session发送消息
        for (WebSocketSession socketSession : COUNTS) {
            try {
                if (socketSession.isOpen()) {
                    //最关键的一句，给客户端推送消息
                    socketSession.sendMessage(new TextMessage(message));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
