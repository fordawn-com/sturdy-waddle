package com.fordawn.application.service;

import com.fasterxml.uuid.Generators;
import com.fordawn.application.dao.InfoDao;
import com.fordawn.application.dao.entity.RelationEntity;
import com.fordawn.application.dao.mapper.RelationMapper;
import com.fordawn.application.utils.UUIDConverter;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class InfoService {

    @Autowired
    private InfoDao infoDao;

    @Autowired
    private RelationMapper relationMapper;

    private ThreadPoolExecutor executor;

    private ListeningExecutorService service;

    @PostConstruct
    public void init() {
        executor = new ThreadPoolExecutor(500, 500,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(500),
                new NameTreadFactory());
        executor.prestartAllCoreThreads();

        service = MoreExecutors.listeningDecorator(Executors.newWorkStealingPool());
    }

    static class NameTreadFactory implements ThreadFactory {
        private final AtomicLong mThreadNum = new AtomicLong(1);

        @Override
        public Thread newThread(@NonNull Runnable r) {
            String format = String.format("%s-%03d", "ford-pool-", mThreadNum.getAndIncrement());
            Thread t = new Thread(r, format);
            log.info("{} has been created", t.getName());
            return t;
        }
    }

    @PreDestroy
    public void free() {
        if (Objects.nonNull(executor)) {
            executor.shutdown();
        }
        service.shutdown();
    }


    public CompletableFuture<?> getInfo() {
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            infoDao.getInfo();
        }, executor);

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> 555);
//        integerCompletableFuture.thenCombine()
        return voidCompletableFuture;
    }

    public static final int THREAD_NUM = 500;
    public static final long HANDLE_PER_THREAD = Long.MAX_VALUE;

    public static List<Object> oc = Lists.newLinkedList();

    static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / (1024 * 1024);
    }

//    @Bean("te_test")
    public void foo() {
        log.error("666");

        List<CompletableFuture<Void>> futures = Lists.newLinkedList();
        for (int i = 0; i < THREAD_NUM; i++) {
            int finalI = i;
            CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
                BigInteger index = BigInteger.ZERO;
                while (true) {
                    Object o = new Object();
                    oc.add(o);
                    if (index.mod(BigInteger.valueOf(30000L)).equals(BigInteger.ZERO)) {
                        log.info("{}-{}-{}-{}-{}",
                                String.format("%03d", finalI),
                                RandomStringUtils.randomAlphabetic(8),
                                index,
                                oc.size(),
                                getFreeMemory()
                        );
                    }
//                    index = index.add(BigInteger.ONE);
                }
            }, executor);
            futures.add(voidCompletableFuture);
        }

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        voidCompletableFuture
                .thenApplyAsync(r -> "hello world, finish.")
                .whenCompleteAsync((r, t) -> {
                    if (Objects.isNull(t)) {
                        log.warn("{}", r);
                    } else {
                        log.error("finish wrong: {}", t.getMessage());
                        throw new RuntimeException(t);
                    }
                }, executor);
    }

    public void bar() {
        List<CompletableFuture<Void>> futures = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            futures.add(CompletableFuture.runAsync(() -> {
                log.info("task submit");
                for (int j = 0; j < 100000; j++) {
                    List<RelationEntity> insert = Lists.newArrayListWithCapacity(10);
                    for (int k = 0; k < 20; k++) {
                        RelationEntity relationEntity = new RelationEntity();
                        relationEntity.setFromId(UUIDConverter.fromTimeUUID(Generators.timeBasedGenerator().generate()));
                        relationEntity.setToId(UUIDConverter.fromTimeUUID(Generators.timeBasedGenerator().generate()));
                        relationEntity.setFromType("ASSET");
                        relationEntity.setToType("DEVICE");
                        relationEntity.setRelationType(String.format("mock%d", finalI));
                        relationEntity.setRelationTypeGroup("COMMON");
                        insert.add(relationEntity);
                    }
                    int insertResult = relationMapper.batchInsert(insert);
                    log.info("insert: {}-{}, {}:{}", finalI, j, insert.size(), insertResult);
                }
            }));
        }
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
    }
}
