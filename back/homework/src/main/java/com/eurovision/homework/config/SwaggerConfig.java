package com.eurovision.homework.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.eurovision.homework.controller"))
        .paths(PathSelectors.any()).build().apiInfo(this.apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("Eurovision REST API", "Eurovision home exercise API.", "API TOS", "Terms of service",
        new Contact("Mario Díaz", "www.google.com", "mario.diaztajuelo@gmail.com"), "License of API", "API license URL",
        Collections.emptyList());
  }
}
