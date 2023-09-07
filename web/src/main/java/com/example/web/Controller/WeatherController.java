package com.example.web.cntroller;

import com.example.web.DataRepository.DataEntity;
import com.example.web.Service.WeatherDataService;
import com.example.web.cntroller.WeatherData.WeatherEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WeatherController {

    private final WeatherDataService weatherDataService;

    @Autowired
    public WeatherController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveWeatherData(@RequestBody WeatherData weatherData) {
        System.out.println("Received City Name: " + weatherData.getCity());
        System.out.println("WEATHER CONDITION ");

        for (WeatherData.WeatherEntry entry : weatherData.getWeatherEntries()) {
            System.out.println("Date: " + entry.getDate());
            System.out.println("High Temperature: " + entry.getHighTemperature() + "°C");
            System.out.println("Low Temperature: " + entry.getLowTemperature() + "°C");
            System.out.println("Carry Umbrella: " + entry.isCarryUmbrella());
            System.out.println("Use Sunscreen: " + entry.isUseSunscreen());
            System.out.println("--------------------------------------------");
        }

        // Assuming that weatherData is a suitable representation of your data
        DataEntity dataEntity = convertWeatherDataToDataEntity(weatherData);

        // Save the data using the service
        weatherDataService.saveWeatherData(dataEntity);

        String jsonResponse = "{\"message\": \"Data saved successfully\"}";
        return ResponseEntity.ok(jsonResponse);
    }

    // Create a method to convert your WeatherData to DataEntity here
    private DataEntity convertWeatherDataToDataEntity(WeatherData weatherData) {
        DataEntity dataEntity = new DataEntity();
        // Set city on the main data entity
        dataEntity.setCity(weatherData.getCity());

        List<DataEntity> dataEntries = new ArrayList<>();

        for (WeatherEntry entry : weatherData.getWeatherEntries()) {
            DataEntity dataEntry = new DataEntity();
            // Set city on each entry
            dataEntry.setCity(weatherData.getCity());

            // Set the date property using entry.getDate()
            dataEntry.setDate(entry.getDate());

            // Set other properties
            dataEntry.setHighTemperature(entry.getHighTemperature());
            dataEntry.setLowTemperature(entry.getLowTemperature());
            dataEntry.setCarryUmbrella(entry.isCarryUmbrella());
            dataEntry.setUseSunscreen(entry.isUseSunscreen());

            dataEntries.add(dataEntry);
        }

        dataEntity.setWeatherEntries(dataEntries);

        return dataEntity;
    }



}