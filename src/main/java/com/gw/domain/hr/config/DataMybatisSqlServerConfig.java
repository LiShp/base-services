package com.gw.domain.hr.config;

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
@MapperScan(value = "com.gw.domain.hr.mapperdata", sqlSessionFactoryRef = "sqlServerSessionFactory")
public class DataMybatisSqlServerConfig {

    @Autowired
    private Environment env;


    @Bean(name = "sqlServerDataSource")
    @Qualifier("sqlServerDataSource")
    @ConfigurationProperties(prefix="spring.datasource.sqlserver")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "sqlServerSessionFactory")
    SqlSessionFactory sqlSessionFactoryMysqlHrFile(@Qualifier("sqlServerDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.sqlserver")));
        factoryBean.setConfigLocation(new DefaultResourceLoader().getResource(env.getProperty("mybatis.mybatis-locations")));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlServerTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("sqlServerDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlServerSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlServerSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
