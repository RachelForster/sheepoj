package com.sheep.sheepoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.sheep.sheepoj.dao")
public class SheepojApplication {

    public static void main(String[] args) {
        SpringApplication.run(SheepojApplication.class, args);
    }

}
