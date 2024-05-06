/*
 * SwaggerConfiguration.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Swagger configuration.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@Configuration
public class SwaggerConfiguration {

    /**
     * Configures Swagger UI.
     *
     * @return the Open API
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Acme Account Manager REST API")
                        .description("REST API for Acme Account Manager.")
                        .version("1.0"));
    }

}
