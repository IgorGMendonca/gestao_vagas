package com.example.igorgabriel.gestao_vagas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Autowired
    private SecurityCandidateFilter securityCandidateFilter;

    /*
     * @Bean is a method-level annotation and a direct analog of the XML <bean/>
     * element.
     * The annotation supports most of the attributes offered by <bean/>,
     * such as: init-method, destroy-method, autowiring mode, dependency check,
     * singleton or prototype, lazy init, and scope.
     */

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> {
            auth.requestMatchers("/candidates/").permitAll()
                    .requestMatchers("/company/").permitAll()
                    .requestMatchers("/candidates/auth").permitAll()
                    .requestMatchers("/company/auth").permitAll();

            auth.anyRequest().authenticated();
        })
                .addFilterBefore(securityCandidateFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(securityFilter, BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
