package com.api.parkingcontrol.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
 
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Parking Spot Control")
                        .description("Simple API REST example")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Renan")
                                .url("https://linkedin.com/in/renanrodolfo")
                                .email("renaner123@gmail.com"))
                        .license(new License().name("License").url("https://github.com/git/git-scm.com/blob/main/MIT-LICENSE.txt"))
                );
    }
}