package com.fordawn.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class InfoController {

    @GetMapping("/version")
    public String getVersion() {
        log.info("666");
        return "3.1";
    }
}
