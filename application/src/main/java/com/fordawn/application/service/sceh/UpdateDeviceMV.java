package com.fordawn.application.service.sceh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
@Slf4j
public class UpdateDeviceMV extends AbstractDb {

    @Scheduled(initialDelay = 1, fixedDelay = 1)
    public void handle(){
        log.warn("handle");
        try {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            fire(conn);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void fire(Connection connection) {
        long totalEventsRemoved = executeQuery(connection, "REFRESH MATERIALIZED VIEW CONCURRENTLY device_view_m;");
        log.info("Total events removed by TTL: [{}]", totalEventsRemoved);
    }
}
