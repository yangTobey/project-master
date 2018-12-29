package com.spring.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
// 扫描 Mapper 接口并容器管理https://www.cnblogs.com/carrychan/p/9401471.html  https://blog.csdn.net/qq_37142346/article/details/78488452  https://www.cnblogs.com/java-zhao/p/5413845.html
//https://www.cnblogs.com/java-zhao/p/5413845.html
/**
 * DataSource类型需要引入javax.sql.DataSource；当系统中有多个数据源时，必须有一个数据源为主数据源，使用@Primary修饰。
 * https://blog.csdn.net/acquaintanceship/article/details/75350653
 */
@MapperScan(basePackages = MasterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    @Resource
    private Environment environment;

    // 精确到 master 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.spring.boot.dao.web.master";
    static final String MAPPER_LOCATION = "classpath:mapper/master/*.xml";

    @Value("${master.datasource.url}")
    private String url;

    @Value("${master.datasource.username}")
    private String user;

    @Value("${master.datasource.password}")
    private String password;

    @Value("${master.datasource.driver-class-name}")
    private String driverClass;

    @Bean(name = "masterDataSource")
    @Primary //https://blog.csdn.net/lz710117239/article/details/81192761  https://www.imooc.com/article/43065
    public DataSource masterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        //configuration
        dataSource.setInitialSize(Integer.parseInt(environment.getProperty("spring.datasource.initialSize")));
        dataSource.setMinIdle(Integer.parseInt(environment.getProperty("spring.datasource.minIdle")));
        dataSource.setMaxActive(Integer.parseInt(environment.getProperty("spring.datasource.maxActive")));
        dataSource.setMaxWait(Integer.parseInt(environment.getProperty("spring.datasource.maxWait")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(environment.getProperty("spring.datasource.timeBetweenEvictionRunsMillis")));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(environment.getProperty("spring.datasource.minEvictableIdleTimeMillis")));
        dataSource.setValidationQuery(environment.getProperty("spring.datasource.validationQuery"));
        dataSource.setTestWhileIdle(Boolean.parseBoolean(environment.getProperty("spring.datasource.testWhileIdle")));
        dataSource.setTestOnBorrow(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnBorrow")));
        dataSource.setTestOnReturn(Boolean.parseBoolean(environment.getProperty("spring.datasource.testOnReturn")));
        dataSource.setPoolPreparedStatements(Boolean.parseBoolean(environment.getProperty("spring.datasource.poolPreparedStatements")));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(environment.getProperty("spring.datasource.maxPoolPreparedStatementPerConnectionSize")));
        try {
            dataSource.setFilters(environment.getProperty("spring.datasource.filters"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.setConnectionProperties(environment.getProperty("spring.datasource.connectionProperties"));
        return dataSource;
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(masterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new StatViewServlet());
        registrationBean.setUrlMappings(Arrays.asList("/druid/*"));
        //设置初始化参数
        Map<String,String> initMap = new HashMap<>();
        initMap.put("loginUsername","admin");
        initMap.put("loginPassword","admin@A123");
        initMap.put("allow","");
        initMap.put("deny","192.168.110.76");
        registrationBean.setInitParameters(initMap);
        return registrationBean;
    }
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new WebStatFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/*"));
        //设置初始化参数
        Map<String,String> initMap = new HashMap<>();
        initMap.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        registrationBean.setInitParameters(initMap);
        return registrationBean;
    }
    //原文：https://blog.csdn.net/J080624/article/details/80812051
    //https://blog.csdn.net/u011943534/article/details/82260311

}
