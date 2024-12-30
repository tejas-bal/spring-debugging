package com.weather;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class RestClientRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RestClientRunner.class);

    private final BlockingService blockingService;
    private final Map<Double, Double> coordinates;

    public RestClientRunner(BlockingService blockingService, Map<Double, Double> coordinates) {
        this.blockingService = blockingService;
        this.coordinates = coordinates;
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("RestClientRunner has started!");
        long startTime = System.currentTimeMillis();
        List<Forecast> forecastList = new ArrayList<>();
        coordinates.forEach((latitude, longitude) -> {
            forecastList.add(blockingService.fetchData(latitude, longitude));
        });
        logger.info("Got weather data for {} cities", forecastList.size());
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        logger.info("Time to get weather for all cities using blocking rest client is : {} ms", duration);
    }
}
