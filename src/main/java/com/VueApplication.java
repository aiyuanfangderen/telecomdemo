package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo")
public class VueApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueApplication.class, args);
    }
}
