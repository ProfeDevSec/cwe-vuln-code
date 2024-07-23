package com.example.bank.config;

import com.example.bank.filter.RateLimitingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private RateLimitingFilter rateLimitingFilter;

    @PostConstruct
    public void addFilters() {
        // Register the rate limiting filter
    }
}
