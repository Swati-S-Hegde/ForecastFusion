package com.example.web.Controller;
import com.example.web.Controller.WeatherData.WeatherEntry;
import com.example.web.DataRepository.DataEntity;
import com.example.web.DataRepository.WeatherDataRepository;
import com.example.web.Service.WeatherDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
    public ResponseEntity<WeatherData> getWeatherData(@RequestParam String city) {
        WeatherData weatherData = weatherDataService.getRecentWeatherData(city);
        if (weatherData != null) {
            return ResponseEntity.ok(weatherData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/save")
    public ResponseEntity<String> saveWeatherData(@RequestBody WeatherData weatherData) {
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


    private DataEntity convertWeatherDataToDataEntity(WeatherData weatherData) {
        DataEntity dataEntity = new DataEntity();
        List<DataEntity> dataEntries = new ArrayList<>();

        for (WeatherEntry entry : weatherData.getWeatherEntries()) {
            DataEntity dataEntry = new DataEntity();
            // Set city on each entry
            dataEntry.setCity(weatherData.getCity());

            // Set the date property using entry.getDate()
            String dateString = entry.getDate();
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);

            LocalDate parsedDate = LocalDate.parse(dateString, inputFormatter);
            dataEntry.setDate(LocalDate.parse(String.valueOf(parsedDate)));
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

