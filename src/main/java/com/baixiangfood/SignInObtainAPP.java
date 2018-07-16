package com.baixiangfood;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAutoConfiguration
@SpringBootApplication
@MapperScan("com.baixiangfood.mapper")
//开启定时任务
@EnableScheduling
public class SignInObtainAPP {

    public static void main(String[] args) {
        SpringApplication.run(SignInObtainAPP.class, args);
    }

}
