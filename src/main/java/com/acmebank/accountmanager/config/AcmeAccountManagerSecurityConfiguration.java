/*
 * AcmeAccountManagerSecurityConfiguration.java
 *
 * Acme Account Manager is the account manager of Acme Bank.
 *
 * This class or interface is part of the Acme Account Manager project.
 * The class or interface must not be used outside of this context.
 */
package com.acmebank.accountmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 * The security configuration for the application.
 *
 * @author Tsang Yiu Kee Kay
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AcmeAccountManagerSecurityConfiguration {

    /**
     * Returns the security filter chain.
     * Configures how the endpoints are passing through the application.
     *
     * @param http the HTTP security
     * @return the security filter chain
     * @throws Exception when a problem occurs
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // disable CSRF
                .csrf(AbstractHttpConfigurer::disable)

                // allow embedded iframe for H2-console
                .headers(httpSecurityHeadersConfigurer ->
                        httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))

                // permit all section
                .authorizeHttpRequests((auth) -> auth.requestMatchers(
                        "/**" // index page
                        , "/api" // API index (swagger)
                        , "/api/v1/balances/**" // balances
                        , "/api/v1/transfer" // transfer
                ).permitAll())

                // session management
                .sessionManagement((sess) -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}