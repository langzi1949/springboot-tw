package com.zmglove.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的配置
 */

@Configuration
@EnableSwagger2
public class Swagger2 {


    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zmglove"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo getApiInfo(){
        return new ApiInfoBuilder()
                .title("SpringBoot-Swagger")
                .description("学习 Springboot 中集成 Swagger")
                .contact("CZH-ZMG")
                .version("1.0.0")
                .build();
    }


}
