package com.example.web.cntroller;
import com.example.web.cntroller.WeatherData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WeatherController {
    @PostMapping("/save")
    public ResponseEntity<String> saveWeatherData(@RequestBody WeatherData weatherData) {

        System.out.println("Received City Name: " + weatherData.getCity());
        System.out.println("Received Weather Condition: " + weatherData.getWeatherCondition());
        String jsonResponse = "{\"message\": \"Data saved successfully\"}";
        return ResponseEntity.ok(jsonResponse);
    }
}
