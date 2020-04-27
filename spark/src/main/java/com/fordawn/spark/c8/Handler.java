package com.fordawn.spark.c8;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.spark.connector.cql.CassandraConnector;
import lombok.extern.slf4j.Slf4j;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.time.Instant;

import static org.apache.spark.sql.functions.desc;

@Slf4j
public class Handler {

    public static final String APP_NAME = "test";
    public static final String MASTER = "local[*]";
    //    public static final String HOST = "192.168.0.14";
    public static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("tb")
                .config("spark.cassandra.connection.host", "127.0.0.1")
                .config("spark.cassandra.connection.port", 9045)
                .master(MASTER)
                .getOrCreate();

        long now = Instant.now().toEpochMilli();

        Dataset<Row> load = spark.read()
                .format("org.apache.spark.sql.cassandra")
                .option("keyspace", "thingsboard")
                .option("table", "ts_kv_cf")
                .load();
//        Dataset<Row> ts = load.orderBy(desc("ts")).limit(1);
//        ts.show();
//        Dataset<Row> where = load.where("entity_type = 'DEVICE' and ts > 1585670400000");
//        where.show();

        Dataset<Row> siotTs = spark.read()
                .format("org.apache.spark.sql.cassandra")
                .option("keyspace", "siot")
                .option("table", "ts_kv_cf")
                .load();

//        Dataset<Row> ts_desc = siotTs.orderBy(desc("ts"));
//        ts_desc.show();

//        long count = where.count();
//        System.out.println("ts count: " + count);

        Dataset<Row> load1 = spark.read()
                .format("org.apache.spark.sql.cassandra")
                .option("keyspace", "siot")
                .option("table", "ts_kv_cf")
                .load();
        long count = load1.count();
//        load1.sa
        System.out.println(count);
    }

}
