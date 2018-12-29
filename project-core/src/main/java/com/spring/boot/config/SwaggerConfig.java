package com.spring.boot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by Administrator on 2018/12/29.
 * 在线接口文档配置
 * http://www.importnew.com/29514.html
 * https://blog.csdn.net/struggling_rong/article/details/79376338
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig  /*extends WebMvcConfigurationSupport*/ {

    //是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置
    @Value(value = "${swagger.enabled}")
    Boolean swaggerEnabled;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(swaggerEnabled).select()
                // 扫描的路径包，暴露指定包下的接口说明，可以指定多个包
                .apis(RequestHandlerSelectors.basePackage("com.spring.boot.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("数据云平台在线接口文档")
                .description("作者：小善")
                // 作者信息
                //.contact(new Contact("oKong", "https://blog.lqdev.cn/", "499452441@qq.com"))
                .version("1.0.0")
                .build();
    }
    /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     * @param registry
     */
    /*@Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*//**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
    }*/
    //https://blog.csdn.net/xqnode/article/details/81382160
   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*")
                .addResourceLocations("classpath:/static*");
        registry.addResourceHandler("*")
                .addResourceLocations("classpath:/templates*");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars*")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }*/
    //https://www.cnblogs.com/luoluocaihong/p/7106276.html Spring Boot自动配置本身不会自动把/swagger-ui.html这个路径映射到对应的目录META-INF/resources/下面。我们加上这个映射即可。
    //https://stackoverflow.com/questions/53532983/no-mapping-found-for-http-request-with-uri-exp-swagger-ui-html-in-dispatchers
    //https://www.jianshu.com/p/04dd5ff82dad
}
