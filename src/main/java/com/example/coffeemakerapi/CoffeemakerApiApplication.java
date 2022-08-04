package com.example.coffeemakerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class CoffeemakerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeemakerApiApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.coffeemakerapi.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "My REST SimpleCoffeeMakerAPI",
                "Перед первым использованием необходимо выполнить проверку заполненности кофе-машины!\n " +
                        "|Контроллер управления кофе-машиной| >>> |Проверить заполненность кофе-машины|\n " +
                        "Не забывайте вовремя чистить кофе-машину!\n " +
                        "|Контроллер управления кофе-машиной| >>> |Почистить кофе-машину|\n " +
                        "Приятного пользования :)",
                "2.0.0 ULTRA",
                "",
                new Contact("Philipp", "com.example.coffeemakerapi",
                        "philipp121412@gmail.com"), "", "", new ArrayList<>()
        );
    }

}
