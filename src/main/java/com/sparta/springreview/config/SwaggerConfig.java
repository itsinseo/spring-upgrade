package com.sparta.springreview.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("블로그 서비스 API")
                .version("v1")
                .description("Spring 복습용 블로그 서비스 API 입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
