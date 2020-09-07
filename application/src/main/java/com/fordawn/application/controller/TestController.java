package com.fordawn.application.controller;

import com.fordawn.application.design.strategy.Strategy;
import com.fordawn.application.exception.SeriousException;
import com.fordawn.application.service.sceh.UpdateDeviceMV;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 此控制器应无副作用
 *
 * @author ins
 */
@Slf4j
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private UpdateDeviceMV updateDeviceMV;

    @GetMapping("/2")
    public void a2() {
        updateDeviceMV.handle();
    }


    @Autowired
    private final Map<String, Strategy> strategy = new ConcurrentHashMap<>(3);

    @GetMapping("/1")
    public String vv(@RequestParam String key) {
        this.strategy.forEach((k, v) -> {
            log.warn("{} {}", k, v);
        });

        Strategy strategy = this.strategy.get(key);

        if (Objects.isNull(strategy)) {
            throw new SeriousException("no strategy found.");
        }

        return strategy.handle();
    }
}
