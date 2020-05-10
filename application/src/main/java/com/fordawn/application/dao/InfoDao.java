package com.fordawn.application.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Slf4j
public class InfoDao {

    public String getInfo(){
        String random = RandomStringUtils.random(12);
        log.info("random [{}]", random);
        return random;
    }
}
