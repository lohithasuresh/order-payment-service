package com.scaler.ecommerce.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI orderPaymentOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Payment Service API")
                        .description("APIs for managing customers, orders and payments")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Lohitha Suresh")
                                .email("support@example.com")));
    }
}
