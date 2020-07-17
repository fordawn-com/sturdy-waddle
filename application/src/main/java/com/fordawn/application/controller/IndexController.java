package com.fordawn.application.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class IndexController {

    @GetMapping("/")
    public String index() {

        return "ok";
    }

    @GetMapping("/admin")
    public String admin() {

        return "admin yes;";
    }
}
