package com.gw.domain.hr.config;

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
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author zoujialiang
 */
@Configuration
@MapperScan(value = "com.gw.domain.hr.mapper", sqlSessionFactoryRef = "sqlSessionFactoryMysql")
public class DataMybatisMysqlConfig {

    @Autowired
    private Environment env;

    @Resource
    static DataSource dsMysql;


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
    public static DataSourceTransactionManager mysqlTransactionManager() {
        return new DataSourceTransactionManager(dsMysql);
    }

}
