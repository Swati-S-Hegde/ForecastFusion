package com.example.web.controller;

import com.example.web.dataRepository.DataEntity;
import com.example.web.dataRepository.WeatherDataRepository;
import com.example.web.service.WeatherDataService;
import com.example.web.model.WeatherDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WeatherController {

    private final WeatherDataService weatherDataService;
    private final WeatherDataRepository weatherDataRepository;

    @Autowired
    public WeatherController(WeatherDataService weatherDataService, WeatherDataRepository weatherDataRepository) {
        this.weatherDataService = weatherDataService;
        this.weatherDataRepository = weatherDataRepository;
    }

    @GetMapping("/get")
    public ResponseEntity<WeatherDataModel> getWeatherData(@RequestParam String city) {
        WeatherDataModel weatherData = weatherDataService.getRecentWeatherData(city);
        if (weatherData != null) {
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveWeatherData(@RequestBody WeatherDataModel weatherData) {
        if (weatherData != null && weatherData.getCity() != null) {

            // Convert WeatherData to DataEntity
            DataEntity dataEntity = convertWeatherDataToDataEntity(weatherData);

            // Call the service method to save the data
            weatherDataService.saveWeatherData(dataEntity);

            return ResponseEntity.ok("Weather data saved successfully.");
        } else {
            return ResponseEntity.badRequest().body("Weather data or city parameter is missing.");
        }
    }

private DataEntity convertWeatherDataToDataEntity(WeatherDataModel weatherData) {
    DataEntity dataEntity = new DataEntity();

    List<DataEntity> dataEntries = weatherData.getWeatherEntries().stream()
            .map(entry -> {
                DataEntity dataEntry = new DataEntity();
                dataEntry.setCity(weatherData.getCity());
                String dateString = entry.getDate();
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
                LocalDate parsedDate = LocalDate.parse(dateString, inputFormatter);
                dataEntry.setDate(parsedDate);
                dataEntry.setHighTemperature(entry.getHighTemperature());
                dataEntry.setLowTemperature(entry.getLowTemperature());
                dataEntry.setCarryUmbrella(entry.isCarryUmbrella());
                dataEntry.setUseSunscreen(entry.isUseSunscreen());
                return dataEntry;
            })
            .collect(Collectors.toList());

    dataEntity.setWeatherEntries(dataEntries);
    return dataEntity;
}
}

