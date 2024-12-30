package com.weather;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class BlockingService {

    private final RestClient restClient;

    public BlockingService(RestClient restClient) {
        this.restClient = restClient;
    }

    public Forecast fetchData(Double latitude, Double longitude) {
        String uri = "/v1/forecast?latitude=" + latitude + "&longitude=" + longitude + "&hourly=temperature_2m";
        return restClient.get()
                .uri(uri)
                .retrieve()
                .body(Forecast.class);
    }
}
