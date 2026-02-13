package com.example.soundwave.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/** Configuration class exposes a RestTemplate bean
 *
 * RestTemplate is used to make HTTP requests to external services
 * @Bean allows it to be injected anywhere in the app
 */

@Configuration
public class RestTemplateConfig {
    // Creates and registers a RestTemplate bean in spring context

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

// Newer projs use WebClient, but RestTemplate is ok for now