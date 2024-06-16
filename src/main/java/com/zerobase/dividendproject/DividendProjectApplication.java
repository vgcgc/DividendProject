package com.zerobase.dividendproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DividendProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DividendProjectApplication.class, args);
    }

}
