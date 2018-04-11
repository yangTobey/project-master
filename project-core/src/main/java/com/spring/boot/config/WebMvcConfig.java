package com.spring.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2018/1/25.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {


    public static String LOGIN_USER = "loginUser";

    /**
     * 系统启动后访问首页设置
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("views/login/login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }
    /**
     * 允许CORS
     * @return
     */
    /*@Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(false);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("*//**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }*/
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*//**").allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST","PUT", "DELETE", "OPTIONS")
                .allowCredentials(false).maxAge(3600);
    }*/
    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*//**")
                .allowedOrigins("http://192.168.130.41")
                .allowedMethods("GET", "POST")
                .allowCredentials(false).maxAge(3600);
    }*/
}
