package com.gw.domain.hr.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author zoujialiang
 */
@Configuration
@MapperScan(value = "com.gw.domain.hr.mapperdata", sqlSessionFactoryRef = "sqlSessionFactorySqlserver")
public class DataMybatisSqlServerConfig {

    @Autowired
    private Environment env;

    @Resource
    static DataSource dsSqlserver;


    @Bean
    SqlSessionFactory sqlSessionFactorySqlserver() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dsSqlserver);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.sqlserver")));
        factoryBean.setConfigLocation(new DefaultResourceLoader().getResource(env.getProperty("mybatis.mybatis-locations")));
        return factoryBean.getObject();
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactorySqlserver());
    }

}
