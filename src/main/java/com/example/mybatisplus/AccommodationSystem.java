package com.example.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan({"com.example.mybatisplus.mapper"})
public class AccommodationSystem {

    public static void main(String[] args) {
        SpringApplication.run(AccommodationSystem.class, args);
    }

}
