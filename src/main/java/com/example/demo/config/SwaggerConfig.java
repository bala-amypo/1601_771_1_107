package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Point Swagger to your actual backend origin during development
        return new OpenAPI()
            .servers(List.of(
                new Server().url("https://9125.pro604cr.amypo.ai/")
            ));
    }
}
