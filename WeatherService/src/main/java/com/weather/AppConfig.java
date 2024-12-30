package com.weather;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Configuration
public class AppConfig {

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl("https://api.open-meteo.com")
                .build();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://api.open-meteo.com")
                .build();
    }

    @Bean
    public Map<Double, Double> coordinates() {
        return Map.ofEntries(
                Map.entry(35.6895, 139.6917),  // Tokyo
                Map.entry(-33.8688, 151.2093), // Sydney
                Map.entry(52.5200, 13.4050),   // Berlin
                Map.entry(55.7558, 37.6173),   // Moscow
                Map.entry(48.2082, 16.3738),   // Vienna
                Map.entry(40.7128, -74.0060),  // New York
                Map.entry(31.2304, 121.4737),  // Shanghai
                Map.entry(37.5665, 126.9780),  // Seoul
                Map.entry(28.6139, 77.2090),   // New Delhi
                Map.entry(51.1657, 10.4515),   // Germany
                Map.entry(40.7306, -73.9352),  // Brooklyn
                Map.entry(53.3498, -6.2603),   // Dublin
                Map.entry(41.0179, 28.9845),   // Istanbul
                Map.entry(55.6761, 12.5683),   // Copenhagen
                Map.entry(34.0522, -118.2437), // Los Angeles
                Map.entry(52.3676, 4.9041),    // Amsterdam
                Map.entry(22.3964, 114.1095),  // Hong Kong
                Map.entry(44.4268, 26.1025),   // Bucharest
                Map.entry(37.9838, 23.7275),   // Athens
                Map.entry(60.1692, 24.9402),   // Helsinki
                Map.entry(41.0151, 28.9794),   // Istanbul
                Map.entry(19.4326, -99.1332),  // Mexico City
                Map.entry(52.3791, 4.9009),    // The Hague
                Map.entry(43.6532, -79.3832),  // Toronto
                Map.entry(33.4484, -112.0740), // Phoenix
                Map.entry(41.0148, 28.9536),   // Istanbul
                Map.entry(45.4215, -75.6972),  // Ottawa
                Map.entry(39.9138, 32.8597),   // Ankara
                Map.entry(29.7604, -95.3698),  // Houston
                Map.entry(51.5074, -0.1278),   // London
                Map.entry(50.0755, 14.4378),   // Prague
                Map.entry(41.9028, 12.4964),   // Rome
                Map.entry(48.8566, 2.3522),    // Paris
                Map.entry(39.9042, 116.4074),  // Beijing
                Map.entry(37.7749, -122.4194)  // San Francisco
        );
    }
}
