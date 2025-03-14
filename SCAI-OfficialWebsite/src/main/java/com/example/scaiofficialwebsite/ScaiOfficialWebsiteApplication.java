package com.example.scaiofficialwebsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@MapperScan("com.example.scaiofficialwebsite.demos.mapper")
@SpringBootApplication
//        (exclude = {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy(exposeProxy = true) // 设置代理对象暴露
public class ScaiOfficialWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaiOfficialWebsiteApplication.class, args);
    }

}
