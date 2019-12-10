package com.gw.domain.hr.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zoujialiang
 */
@Configuration
public class DataSourceSqlServerConfig {

    @Bean(name = "dsSqlserver")
    @ConfigurationProperties("spring.datasource.sqlserver")
    DataSource dsSqlserver() {
        return DataSourceBuilder.create().build();
    }
}
