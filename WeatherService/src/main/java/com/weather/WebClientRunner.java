package com.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class WebClientRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(WebClientRunner.class);

    private final NonBlockingService nonBlockingService;
    private final Map<Double, Double> coordinates;

    public WebClientRunner(NonBlockingService nonBlockingService, Map<Double, Double> coordinates) {
        this.nonBlockingService = nonBlockingService;
        this.coordinates = coordinates;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("WebClientRunner has started!");
        long startTime = System.currentTimeMillis();
        List<Mono<Forecast>> responseMonos = new ArrayList<>();
        coordinates.forEach((latitude, longitude) -> {
            responseMonos.add(nonBlockingService.fetchData(latitude, longitude));
        });
        Mono.zip(responseMonos, objects -> {
            List<Forecast> responses = new ArrayList<>();
            for (Object response : objects) {
                responses.add((Forecast) response);
            }
            return responses;
        }).doOnTerminate(() -> {
            logger.info("All API calls completed!");
        }).subscribe(
                result -> {
                    logger.info("Got weather data for {} cities", result.size());
                    long endTime = System.currentTimeMillis();
                    long duration = (endTime - startTime);
                    logger.info("Time to get weather for all cities using non blocking web client is : {} ms", duration);
                },
                error -> logger.error("Error occurred: {}", error.getMessage())
        );
    }
}
