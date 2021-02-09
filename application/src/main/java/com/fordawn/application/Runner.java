package com.fordawn.application;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        log.warn("first runner");
    }
}
