package com.gw.domain.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author zoujialiang
 */
@EnableSwagger2
@MapperScan(basePackages = {"com.gw.domain.hr.mapper", "com.gw.domain.hr.mapperhr", "com.gw.domain.hr.mapperhrfile"})
@ComponentScan(basePackages = {"com.gw.domain.hr.*","com.gw.cloud.common.base.config"})
@EnableScheduling
@EnableTransactionManagement
@SpringBootApplication
public class DomainHrApplication {

    public static void main(String[] args) {SpringApplication.run(DomainHrApplication.class, args);}

}



