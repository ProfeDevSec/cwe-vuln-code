package com.example.bank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().and()
            .authorizeRequests()
                .antMatchers("/transfers/create").authenticated()
                .anyRequest().permitAll()
            .and()
            .formLogin().and()
            .httpBasic();
        return http.build();
    }
}
