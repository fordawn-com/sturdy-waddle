package com.fordawn.application.config;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;

@Configuration
public class ExecutorConfig {

    @Bean("service1")
    public ListeningExecutorService defaultService1() {
        return MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    }

    @Bean("service2")
    public ListeningExecutorService defaultService2() {
        return MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    }
}
