package com.gw.domain_hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.gw.domain_hr.mapper")
@ComponentScan(basePackages = {"com.gw.domain_hr.*","com.gw.cloud.common.base.config"})
@EnableFeignClients
@EnableHystrix
@EnableScheduling
@EnableHystrixDashboard
@EnableTransactionManagement
@SpringCloudApplication
public class DomainHRApplication {

    public static void main(String[] args) {SpringApplication.run(DomainHRApplication.class, args);}

}



