package com.example.web.model;

import java.time.LocalDateTime;
import java.util.List;
public class WeatherDataModel {
    private String city;
    public List<WeatherDataModel.WeatherEntry> weatherEntries;
    private LocalDateTime localDateTime;

    // Getters and setters for WeatherData fields

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<com.example.web.model.WeatherDataModel.WeatherEntry> getWeatherEntries() {
        return weatherEntries;
    }

    public void setWeatherEntries(List<com.example.web.model.WeatherDataModel.WeatherEntry> weatherEntries) {
        this.weatherEntries = weatherEntries;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public static class WeatherEntry {
        private String date;
        private String highTemperature;
        private String lowTemperature;
        private boolean carryUmbrella;
        private boolean useSunscreen;

        // Getters and setters for WeatherEntry fields

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getHighTemperature() {
            return highTemperature;
        }

        public void setHighTemperature(String highTemperature) {
            this.highTemperature = highTemperature;
        }

        public String getLowTemperature() {
            return lowTemperature;
        }

        public void setLowTemperature(String lowTemperature) {
            this.lowTemperature = lowTemperature;
        }

        public boolean isCarryUmbrella() {
            return carryUmbrella;
        }

        public void setCarryUmbrella(boolean carryUmbrella) {
            this.carryUmbrella = carryUmbrella;
        }

        public boolean isUseSunscreen() {
            return useSunscreen;
        }

        public void setUseSunscreen(boolean useSunscreen) {
            this.useSunscreen = useSunscreen;
        }
    }
}






