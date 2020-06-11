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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@Slf4j
public class InfoService {

    @Autowired
    private InfoDao infoDao;

    @Autowired
    private RelationMapper relationMapper;

    private ExecutorService executor;

    ListeningExecutorService service;

    @PostConstruct
    public void init() {
//        executor = Executors.newFixedThreadPool(20);
        executor = Executors.newWorkStealingPool(8);
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
    }
}
