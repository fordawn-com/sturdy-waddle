package com.fordawn.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class InfoService {

    private Executor executor;

    @PostConstruct
    public void init() {
        executor = Executors.newFixedThreadPool(10);
    }

    @PreDestroy
    public void free() {
        if (Objects.nonNull(executor)) {
//            executor.PreDestroy
        }
    }

    @Bean
    public void foo() {

        log.error("666");
    }
}
