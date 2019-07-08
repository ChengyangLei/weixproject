package com.huotn.weixproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@MapperScan("com.huotn.weixproject.mapper")
public class WeixprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixprojectApplication.class, args);
    }

}
