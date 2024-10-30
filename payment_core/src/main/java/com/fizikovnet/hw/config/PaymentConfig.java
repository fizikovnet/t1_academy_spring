package com.fizikovnet.hw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class PaymentConfig {

    private final String PRODUCT_SERVICE_URL = "http://localhost:8080/api/products";

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(PRODUCT_SERVICE_URL)
                .build();
    }

}
