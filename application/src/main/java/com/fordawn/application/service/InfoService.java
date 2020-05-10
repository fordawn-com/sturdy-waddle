package com.fordawn.application.service;

import com.fordawn.application.dao.InfoDao;
import com.google.common.base.MoreObjects;
import com.google.common.collect.MoreCollectors;
import com.google.common.io.MoreFiles;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Collector;

@Service
@Slf4j
public class InfoService {

    @Autowired
    private InfoDao infoDao;

    private ExecutorService executor;

    ListeningExecutorService service;

    @PostConstruct
    public void init() {
        executor = Executors.newFixedThreadPool(10);
        service = MoreExecutors
                .listeningDecorator(Executors.newWorkStealingPool());
    }

    @PreDestroy
    public void free() {
        if (Objects.nonNull(executor)) {
            executor.shutdown();
        }
        service.shutdown();
    }


    public CompletableFuture getInfo() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            infoDao.getInfo();
        }, executor);

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 555);
//        integerCompletableFuture.thenCombine()
        return voidCompletableFuture;
    }

    @Bean
    public void foo() {

        log.error("666");
    }
}
