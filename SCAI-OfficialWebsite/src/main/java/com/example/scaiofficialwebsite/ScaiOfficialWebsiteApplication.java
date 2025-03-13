package com.example.scaiofficialwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ScaiOfficialWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaiOfficialWebsiteApplication.class, args);
    }

}
