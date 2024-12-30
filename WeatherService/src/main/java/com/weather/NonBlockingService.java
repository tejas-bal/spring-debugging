package com.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NonBlockingService {

    private static final Logger logger = LoggerFactory.getLogger(NonBlockingService.class);

    private final WebClient webClient;

    public NonBlockingService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<Forecast> fetchData(Double latitude, Double longitude) {
        String uri = "/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m";
        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Forecast.class);
    }
}
