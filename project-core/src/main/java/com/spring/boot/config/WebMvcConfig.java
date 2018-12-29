package com.spring.boot.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        //registry.addRedirectViewController("/","/swagger-ui.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        super.addViewControllers(registry);
    }

    /**
     * 该处主要作用为设置Swagger接口文档
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     * https://blog.csdn.net/pispower/article/details/77896344
     * https://blog.csdn.net/xqnode/article/details/81382160
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
    }

    /*@Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }*/
   /* @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setFeatures(SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.parseMediaType("text/plain;charset=utf-8"));
        mediaTypes.add(MediaType.parseMediaType("text/html;charset=utf-8"));
        mediaTypes.add(MediaType.parseMediaType("text/json;charset=utf-8"));
        mediaTypes.add(MediaType.parseMediaType("application/json;charset=utf-8"));
        //mediaTypes.add(MediaType.parseMediaType("application/x-www-form-urlencoded"));
        converter.setSupportedMediaTypes(mediaTypes);
        converters.add(converter);
    }*/
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
