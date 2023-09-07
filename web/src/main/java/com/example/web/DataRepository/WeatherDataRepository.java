//package com.example.web.DataRepository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.sql.Date;
//
//public interface WeatherDataRepository extends JpaRepository<DataEntity, Date> {
//    // You can define custom query methods here if needed
//
//}

package com.example.web.DataRepository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<DataEntity, Long> {
    // You can define custom query methods here if needed
}
