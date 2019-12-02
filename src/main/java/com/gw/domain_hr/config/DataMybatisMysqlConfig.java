package com.gw.domain_hr.config;

import org.apache.ibatis.session.SqlSessionFactory;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "com.gw.domain_hr.mapper", sqlSessionFactoryRef = "sqlSessionFactoryMysql")
public class DataMybatisMysqlConfig {

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier("dsMysql")
    DataSource dsMysql;


    @Bean
    SqlSessionFactory sqlSessionFactoryMysql() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dsMysql);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        return factoryBean.getObject();
    }

    @Bean
    SqlSessionTemplate mysqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactoryMysql());
    }

    @Bean(name = "mysqlTransactionManager")
    @Primary
    public static DataSourceTransactionManager mysqlTransactionManager(@Qualifier("dsMysql") DataSource dsMysql) {
        return new DataSourceTransactionManager(dsMysql);
    }

}
