package com.example.web.DataRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeatherDataRepository extends JpaRepository<DataEntity, Long> {
    // You can define custom query methods here if needed
    @Query("SELECT d FROM DataEntity d WHERE d.city = :city ORDER BY d.date DESC")
    List<DataEntity> findRecentWeatherData(@Param("city") String city);
}
