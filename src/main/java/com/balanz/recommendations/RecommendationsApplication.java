package com.balanz.recommendations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({ "com.balanz" })
public class RecommendationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecommendationsApplication.class, args);
    }

}
