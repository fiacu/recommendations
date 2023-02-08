package com.balanz.recommendations.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class ApiConfig {
    @Bean
    public OpenAPI balanzOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Balanz Market Data API")
                        .description("Market data server application")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Balanz")
                                .url("http://www.balanz.com")));
    }
}