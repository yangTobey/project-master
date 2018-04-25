package com.spring.boot.config;

import com.spring.boot.websocket.WebSocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * websocket配置类
 * Created by xiaoyang on 2018/4/25.
 */
@Configuration
@EnableWebSocket
public class SocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //指定counterHandler处理路径为/websocket/{url} 的长连接请求
        registry.addHandler(webSocket(), "/websocket/{url}").setAllowedOrigins("*");
    }

    @Bean
    public WebSocket webSocket() {
        return new WebSocket();
    }
}
