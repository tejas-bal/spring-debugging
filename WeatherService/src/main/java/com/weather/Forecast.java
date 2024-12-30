package com.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class Forecast {

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("generationtime_ms")
    private double generationTimeMs;

    @JsonProperty("utc_offset_seconds")
    private int utcOffsetSeconds;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;

    @JsonProperty("elevation")
    private int elevation;

    @JsonProperty("hourly_units")
    private Map<String, String> hourlyUnits;

    @JsonProperty("hourly")
    private Hourly hourly;

    // Nested Hourly Class
    public static class Hourly {

        @JsonProperty("time")
        private List<String> time;

        @JsonProperty("temperature_2m")
        private List<Double> temperature2m;

        public List<String> getTime() {
            return time;
        }

        public void setTime(List<String> time) {
            this.time = time;
        }

        public List<Double> getTemperature2m() {
            return temperature2m;
        }

        public void setTemperature2m(List<Double> temperature2m) {
            this.temperature2m = temperature2m;
        }
    }
}
