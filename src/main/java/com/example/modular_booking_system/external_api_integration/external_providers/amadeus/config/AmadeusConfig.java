package com.example.modular_booking_system.external_api_integration.external_providers.amadeus.config;

import com.amadeus.Amadeus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmadeusConfig {

    @Value("${amadeus.api.key}")
    private String apiKey;

    @Value("${amadeus.api.secret}")
    private String apiSecret;

    @Bean
    public Amadeus amadeus() {
        return Amadeus.builder(apiKey, apiSecret)
                .setLogLevel("debug") // Optional for development
                .build();
    }
}
