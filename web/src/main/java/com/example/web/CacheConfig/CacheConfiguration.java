package com.example.web.CacheConfig;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

@Configuration
@EnableCaching
public class CacheConfiguration extends CachingConfigurerSupport {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("weatherCache");
    }
}


