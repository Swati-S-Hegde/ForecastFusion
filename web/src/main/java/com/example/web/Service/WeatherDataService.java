package com.example.web.Service;

import com.example.web.Controller.WeatherData;
import com.example.web.Controller.WeatherData.WeatherEntry;
import com.example.web.DataRepository.DataEntity;
import com.example.web.DataRepository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherDataService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Transactional
    public void saveWeatherData(DataEntity dataEntity) {
        // Set the date property using the current LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.now();
        dataEntity.setDate();

        // Set the useSunscreen property to true (or any other default value you prefer)
        dataEntity.setUseSunscreen(true);

        weatherDataRepository.save(dataEntity);
    }

    @Cacheable(value = "weatherCache", key = "#city")
    public WeatherData getRecentWeatherData(String city) {
        // Fetch recent weather data for the city and return it
        List<DataEntity> recentWeatherData = weatherDataRepository.findRecentWeatherData(city);

        // Convert DataEntity to WeatherData
        WeatherData weatherData = convertDataEntitiesToWeatherData(recentWeatherData);

        return weatherData;
    }

    // Convert DataEntity to WeatherData
    private WeatherData convertDataEntitiesToWeatherData(List<DataEntity> dataEntities) {
        WeatherData weatherData = new WeatherData();

        // Initialize a list to hold WeatherEntry objects
        List<WeatherEntry> weatherEntries = new ArrayList<>();

        for (DataEntity dataEntity : dataEntities) {
            WeatherEntry weatherEntry = new WeatherEntry();

            // Map properties from DataEntity to WeatherEntry
            weatherEntry.setDate(String.valueOf(dataEntity.getDate()));
            weatherEntry.setHighTemperature(dataEntity.getHighTemperature());
            weatherEntry.setLowTemperature(dataEntity.getLowTemperature());
            weatherEntry.setCarryUmbrella(dataEntity.isCarryUmbrella());
            weatherEntry.setUseSunscreen(dataEntity.isUseSunscreen());

            // Add the WeatherEntry to the list
            weatherEntries.add(weatherEntry);
        }

        // Set the weatherEntries list in the WeatherData object using a setter
        weatherData.setWeatherEntries(weatherEntries);

        return weatherData;
    }

    @CacheEvict(value = "weatherCache", key = "#city")
    public void clearCache(String city) {
    }

}

