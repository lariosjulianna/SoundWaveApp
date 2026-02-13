package com.example.soundwave.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC configuration class
 *
 * This class configures Cross-Origin Resource Sharing (CORS)
 * Allows frontend applications to access backend APIs
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configure CORS mapping for the application
     *
     * Allows requests from the front end on localhost:5173
     * (Vite or other dev servers)
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:5173")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}

// in production, allowedOrigins is moved to application.yml
// use .allowCredentials(true) to use cookies or auth headers