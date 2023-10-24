package com.example.web;
import com.example.web.controller.WeatherController;
import com.example.web.dataRepository.WeatherDataRepository;
import com.example.web.model.WeatherDataModel;
import com.example.web.service.WeatherDataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class WeatherControllerTest {

    @InjectMocks
    private WeatherController weatherController;

    @Mock
    private WeatherDataService weatherDataService;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherData_Success() {
        // Mock data for testing
        WeatherDataModel mockWeatherData = new WeatherDataModel();
        mockWeatherData.setCity("MockCity");

        // Mock the behavior of the WeatherDataService
        Mockito.when(weatherDataService.getRecentWeatherData(Mockito.anyString()))
                .thenReturn(mockWeatherData);

        // Test the getWeatherData method
        ResponseEntity<WeatherDataModel> response = weatherController.getWeatherData("MockCity");

        // Assert that the response is successful and contains the expected data
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(mockWeatherData, response.getBody());
    }

    @Test
    public void testGetWeatherData_NotFound() {
        // Mock the behavior of the WeatherDataService to return null
        Mockito.when(weatherDataService.getRecentWeatherData(Mockito.anyString()))
                .thenReturn(null);

        // Test the getWeatherData method
        ResponseEntity<WeatherDataModel> response = weatherController.getWeatherData("NonExistentCity");

        // Assert that the response is 404 (Not Found)
        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void testSaveWeatherData_Success() {
        // Mock data for testing
        WeatherDataModel mockWeatherData = new WeatherDataModel();
        mockWeatherData.setCity("MockCity");

        // Mock the behavior of the WeatherDataService
        Mockito.doNothing().when(weatherDataService).saveWeatherData(Mockito.any());

        // Test the saveWeatherData method
        ResponseEntity<String> response = weatherController.saveWeatherData(mockWeatherData);

        // Assert that the response is successful and contains the expected message
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("Weather data saved successfully.", response.getBody());
    }

    @Test
    public void testSaveWeatherData_BadRequest() {
        // Test with invalid WeatherDataModel (missing city)
        WeatherDataModel invalidWeatherData = new WeatherDataModel();

        // Test the saveWeatherData method with invalid data
        ResponseEntity<String> response = weatherController.saveWeatherData(invalidWeatherData);

        // Assert that the response is 400 (Bad Request)
        Assertions.assertEquals(400, response.getStatusCodeValue());
        Assertions.assertEquals("Weather data or city parameter is missing.", response.getBody());
    }
}
