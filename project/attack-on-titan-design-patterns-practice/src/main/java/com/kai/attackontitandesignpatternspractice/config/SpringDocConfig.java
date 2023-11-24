package com.kai.attackontitandesignpatternspractice.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "A attack on titan design patterns practice",
                version = "0.0",
                description = "版本: \n\n " +
                        "spring boot : 3.1.5\n\n" +
                        "springdoc-openapi-starter-webmvc-ui : 2.1.0\n\n" +
                        "Java : 17\n\n" +
                        "使用進擊的巨人的故事情節來練習設計模式\n\n"
        )
)
@Configuration
public class SpringDocConfig {

}
