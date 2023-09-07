package com.example.web.Service;

import com.example.web.DataRepository.DataEntity;
import com.example.web.DataRepository.WeatherDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//public class WeatherDataService {
//}
@Service
public class WeatherDataService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Transactional
    public void saveWeatherData(DataEntity dataEntity) {
        dataEntity.setDate(" ");
        dataEntity.setUseSunscreen(true);
        weatherDataRepository.save(dataEntity);
    }
}
