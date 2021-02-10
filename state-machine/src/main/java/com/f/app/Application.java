package com.f.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan("com.f")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info("go go go");
    }
}
