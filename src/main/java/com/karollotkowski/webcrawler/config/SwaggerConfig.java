package com.karollotkowski.webcrawler.config;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket api() {
    return new Docket(SWAGGER_2)
        .useDefaultResponseMessages(false)
        .apiInfo(apiInfo())
        .tags(new Tag("web-crawler-controller", "Web crawler methods"))
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.ant("/api/**"))
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Page map generator")
        .description("REST API for page map generation.")
        .build();
  }
}
