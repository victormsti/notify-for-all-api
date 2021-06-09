package com.notifyforall.api.config.swagger;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.notifyforall.api.config.security.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket getApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build()
                .pathMapping("/")
                .securityContexts(Lists.newArrayList(securityContext()))
                .securitySchemes(Lists.newArrayList(apiKey()))
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        Contact contact = new Contact("Victor Matheus", "https://www.github.com/victormsti", "victormsti@gmail.com");

        return new ApiInfo(
                "Notify For All",
                "API Service for Notifications using Web Push, E-mail and SMS",
                "1.0",
                null,
                contact,
                "Apache License Version 2.0",
                "",
                new ArrayList<>()
        );
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", JWTAuthorizationFilter.HEADER_STRING, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/api/.*"))
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");

        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        return Lists.newArrayList(
                new SecurityReference("JWT", authorizationScopes));
    }

}