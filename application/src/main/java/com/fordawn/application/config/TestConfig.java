package com.fordawn.application.config;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Slf4j
@Configuration
public class TestConfig {

    @Bean
    public void testFat() {
        log.info("testFat start");
        final Map<Object, Object> map = Maps.newHashMap();
        map.put("a", 3);
        map.put("b", "cc");
        map.forEach((o, o2) -> {
            log.info("map {} {}", o, o2);
        });
    }
}
