package com.example.web;
import com.example.web.dataRepository.DataEntity;
import com.example.web.dataRepository.WeatherDataRepository;
import com.example.web.service.WeatherDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.cache.CacheManager;
public class WeatherDataServiceTest {

    @InjectMocks
    private WeatherDataService weatherDataService;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @Mock
    private CacheManager cacheManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveWeatherData() {
        // Create a mock DataEntity
        DataEntity dataEntity = new DataEntity();

        // Call the method under test
        weatherDataService.saveWeatherData(dataEntity);

        // Verify that the save method on weatherDataRepository was called once
        Mockito.verify(weatherDataRepository, Mockito.times(1)).save(dataEntity);
    }



}