package com.fordawn.test;

import com.fasterxml.uuid.Generators;
import com.fordawn.application.DemoApplication;
import com.fordawn.application.dao.entity.RelationEntity;
import com.fordawn.application.dao.mapper.RelationMapper;
import com.fordawn.application.utils.UUIDConverter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MainTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RelationMapper relationMapper;

    @Autowired
    @Qualifier("commander")
    private ThreadPoolExecutor executor;

    @BeforeEach
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    @Test
    public void test1() throws Exception {

        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.base.toString() + "/test", String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }

    @Test
    public void test2() {
        log.info("{}", port);
    }

    @Test
    public void test3() {
//        List<RelationEntity> one = relationMapper.findOne();
//        log.info("{}", one);

    }
}
