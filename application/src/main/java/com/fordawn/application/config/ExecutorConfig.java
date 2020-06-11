package com.fordawn.application.config;

import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Executor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

@Configuration
@Slf4j
public class ExecutorConfig {

    @Bean("service1")
    public ListeningExecutorService defaultService1() {
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        return listeningExecutorService;
    }

    @Bean("service2")
    public ListeningExecutorService defaultService2() {

//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
        return MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
    }

    @Bean("commander")
    public ThreadPoolExecutor service1(){
        log.info("service1 init");
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 10,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100));

        return threadPoolExecutor;
    }
}
