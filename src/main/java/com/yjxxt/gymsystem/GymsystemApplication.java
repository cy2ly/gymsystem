package com.yjxxt.gymsystem;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yjxxt.gymsystem.mapper")
public class GymsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(GymsystemApplication.class, args);
    }

}
