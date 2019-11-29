package com.gw.domain_hr.config;

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

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.gw.domain_hr.mapperdata", sqlSessionFactoryRef = "sqlSessionFactorySqlserver")
public class DataMybatisSqlServerConfig {

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("dsSqlserver")
    DataSource dsSqlserver;


    @Bean
    SqlSessionFactory sqlSessionFactorySqlserver() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dsSqlserver);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.sqlserver")));
        factoryBean.setConfigLocation(new DefaultResourceLoader().getResource(env.getProperty("mybatis.aa")));
        return factoryBean.getObject();
    }

    @Bean
    SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactorySqlserver());
    }
}
