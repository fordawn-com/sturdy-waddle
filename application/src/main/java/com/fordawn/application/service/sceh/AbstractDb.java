package com.fordawn.application.service.sceh;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

@Slf4j
public abstract class AbstractDb {

    @Value("${spring.datasource.url}")
    protected String dbUrl;

    @Value("${spring.datasource.username}")
    protected String dbUserName;

    @Value("${spring.datasource.password}")
    protected String dbPassword;

    protected long executeQuery(Connection conn, String query) {
        long removed = 0L;
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            getWarnings(statement);
            resultSet.next();
            removed = resultSet.getLong(1);
            log.debug("Successfully executed query: {}", query);
        } catch (SQLException e) {
            log.debug("Failed to execute query: {} due to: {}", query, e.getMessage());
        }
        return removed;
    }

    protected void getWarnings(Statement statement) throws SQLException {
        SQLWarning warnings = statement.getWarnings();
        if (warnings != null) {
            log.debug("{}", warnings.getMessage());
            SQLWarning nextWarning = warnings.getNextWarning();
            while (nextWarning != null) {
                log.debug("{}", nextWarning.getMessage());
                nextWarning = nextWarning.getNextWarning();
            }
        }
    }

    protected abstract void fire(Connection connection);

}
