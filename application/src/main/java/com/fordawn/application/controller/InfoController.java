package com.fordawn.application.controller;

import com.fordawn.application.model.dto.ResDto;
import com.fordawn.application.service.InfoService;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
@RequestMapping("/api")
public class InfoController {

    @Autowired
    @Qualifier("service1")
    private ListeningExecutorService service;

    @Autowired
    @Qualifier("service2")
    private ListeningExecutorService service2;

    @Autowired
    private InfoService infoService;

    @GetMapping("/version")
    public DeferredResult getVersion() throws InterruptedException, ExecutionException {
        DeferredResult<ResDto> objectDeferredResult = new DeferredResult<>();
        log.info("666");
        ResDto resDto = new ResDto();
//        info.
        List<ListenableFuture<String>> futures1 = Lists.newLinkedList();
        List<ListenableFuture<Long>> futures2 = Lists.newLinkedList();
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            ListenableFuture<String> submit = service.submit(() -> {
                try {
                    Thread.sleep(230);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}", finalI);
                return RandomStringUtils.randomAscii(12);
            });
            futures1.add(submit);
        }
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            ListenableFuture<Long> submit = service2.submit(() -> {
                try {
                    int i1 = RandomUtils.nextInt(200, 300);
                    log.info("sleep {}", i1);
                    Thread.sleep(i1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("{}", finalI);
                return RandomUtils.nextLong();
            });
            futures2.add(submit);
        }
        ListenableFuture<List<String>> listListenableFuture = Futures.allAsList(futures1);
        ListenableFuture<List<Long>> listListenableFuture1 = Futures.allAsList(futures2);

        List<String> strings = listListenableFuture.get();
        List<Long> longs = listListenableFuture1.get();

        resDto.setLongList(longs);
        resDto.setStringList(strings);
        objectDeferredResult.setResult(resDto);
        return objectDeferredResult;
    }
}
