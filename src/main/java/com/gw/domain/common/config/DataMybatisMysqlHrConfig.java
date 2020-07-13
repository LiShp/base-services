package com.gw.domain.common.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * @author gwx
 */
@Configuration
@MapperScan(value = "com.gw.domain.hr.mapperhr", sqlSessionFactoryRef = "hrSessionFactory")
public class DataMybatisMysqlHrConfig {

    @Autowired
    private Environment env;


    @Bean(name = "hrDataSource")
    @Qualifier("hrDataSource")
    @ConfigurationProperties(prefix="spring.datasource.mysql-hr")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "hrSessionFactory")
    SqlSessionFactory sqlSessionFactoryMysqlHrFile(@Qualifier("hrDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.sql-hr-server")));
        factoryBean.setConfigLocation(new DefaultResourceLoader().getResource(env.getProperty("mybatis.mybatis-locations")));
        return factoryBean.getObject();
    }

    @Bean(name = "hrTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("hrDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "hrSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("hrSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
