package com.gw.domain_hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.gw.domain_hr.mapper")
@ComponentScan(basePackages = {"com.gw.domain_hr.*","com.gw.cloud.common.base.config"})
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@SpringCloudApplication
public class Domain_hrApplication {

    public static void main(String[] args) {SpringApplication.run(Domain_hrApplication.class, args);}

}



