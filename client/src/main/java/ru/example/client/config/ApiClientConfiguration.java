package ru.example.client.config;

import org.springframework.context.annotation.Configuration;
import ru.example.client.client.ApiClient;

@Configuration
public class ApiClientConfiguration {

    public ApiClientConfiguration(ApiClient apiClient) {
        apiClient.setBasePath("http://localhost:8080");
    }

}