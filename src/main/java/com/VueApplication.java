package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.mapper")//这里写成com.demo会报错
public class VueApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueApplication.class, args);
    }
}
