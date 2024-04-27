package com.learning.irctcbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.learning.irctcbackend.dao")
public class IrctcBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(IrctcBackendApplication.class, args);
    }

}
