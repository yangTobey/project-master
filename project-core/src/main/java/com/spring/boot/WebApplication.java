package com.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

/**
 * Spring Boot 应用启动类
 * <p>
 * Created by bysocket on 16/4/26.
 */
@SpringBootApplication  // Spring Boot 应用的标识
@MapperScan("com.spring.boot.dao")  // mapper 接口类扫描包配置
public class WebApplication extends SpringBootServletInitializer {

    /*注：外部tomcat启动，类需要继承SpringBootServletInitializer,实现SpringBootServletInitializer可以让spring-boot项目在web容器中运行 */

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplication.class);
    }

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(WebApplication.class, args);
    }
}
