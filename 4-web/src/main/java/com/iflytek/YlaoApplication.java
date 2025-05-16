package com.iflytek;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.iflytek.ylao.**.dao")
public class YlaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(YlaoApplication.class, args);
    }

}
