package com.example.igorgabriel.gestao_vagas.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    /*
     * @Bean is a method-level annotation and a direct analog of the XML <bean/> element. 
     * The annotation supports most of the attributes offered by <bean/>, 
     * such as: init-method, destroy-method, autowiring mode, dependency check, singleton or prototype, lazy init, and scope.
     */

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> {
            auth.requestMatchers("/candidates/").permitAll()
            .requestMatchers("/company/").permitAll();

            auth.anyRequest().authenticated();
        });

        return http.build();
    }
    
}
