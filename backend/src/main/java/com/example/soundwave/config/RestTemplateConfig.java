package com.example.soundwave.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(
            @Value("${musicbrainz.user-agent:SoundWave/0.1 (https://github.com/example/soundwave)}") String userAgent
    ) {
        RestTemplate restTemplate = new RestTemplate();
        ClientHttpRequestInterceptor ua = (request, body, execution) -> {
            request.getHeaders().set("User-Agent", userAgent);
            return execution.execute(request, body);
        };
        restTemplate.setInterceptors(List.of(ua));
        return restTemplate;
    }
}
