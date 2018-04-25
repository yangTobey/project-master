package com.spring.boot.websocket;


import com.spring.boot.service.SysDataAnalysisService;
import com.spring.boot.shiro.WebsocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by Administrator on 2018/4/24.
 */
@Component
//@ServerEndpoint(value = "/websocket/{url}")
public class PropertyWebSocket {
    //@Autowired
    //private SysDataAnalysisService sysDataAnalysisService;
//    private SysDataAnalysisService sysDataAnalysisService=(SysDataAnalysisService) ContextLoader.getCurrentWebApplicationContext().getBean("sysDataAnalysisServiceImpl");

    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<PropertyWebSocket> webSocketSet = new CopyOnWriteArraySet<PropertyWebSocket>();

    /**
     * 与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        //加入set中
        webSocketSet.add(this);
        //在线数加1
        addOnlineCount();
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
        try {
            sendMessage("成功！");
        } catch (IOException e) {
            System.out.println("IO异常");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        //从set中删除
        webSocketSet.remove(this);
        //在线数减1
        subOnlineCount();
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        //物业大屏界面连接成功后请求信息
        if("property".equals(message)){
            //sysDataAnalysisService.sysPropertyDataAnalysis();
        }else if("financial".equals(message)){
            //财务大屏界面连接成功后请求信息
            //sysDataAnalysisService.sysFinancialDataAnalysis();
        }
        //WebsocketUtil websocketUtil=new WebsocketUtil();
        //websocketUtil.sendMessage(message);
        //群发消息
        /*for (PropertyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (PropertyWebSocket item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        PropertyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        PropertyWebSocket.onlineCount--;
    }
}
