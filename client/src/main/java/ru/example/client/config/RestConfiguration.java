package ru.example.client.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

@Configuration
public class RestConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .defaultMessageConverters()
                .build();
    }

    @Bean
    public WebClient webClient() {
        HttpClient httpClient = HttpClient
                .create()
                .wiretap(true);
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector())
                .build();
    }

}