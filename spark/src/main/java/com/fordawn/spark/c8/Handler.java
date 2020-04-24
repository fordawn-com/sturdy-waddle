package com.fordawn.spark.c8;

public class Handler {

    public static final String APP_NAME = "test";
    public static final String MASTER = "local[*]";
    public static final String HOST = "192.168.0.14";

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName(APP_NAME)
                .config("spark.cassandra.connection.host",HOST)
                .master(MASTER)
                .getOrCreate();
    }

}
