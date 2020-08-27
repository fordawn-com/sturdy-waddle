package com.fordawn.application.service;

import java.time.Instant;

/**
 * siot upload test
 */
public class UploadService {


    public static final String PATH = "/api/v1/thingsdata/postFeature";

    public void upload() {

        /*
        header
         */
        String authToken = "";
        String sequence = "";
        long timestamp = Instant.now().toEpochMilli();
        String auth = "";

        /*
        body
         */
        String secretKey = "";
        String category = "";
        String entityName = "";
        String featureId = "";
        String featureVersion = "";

    }
}
