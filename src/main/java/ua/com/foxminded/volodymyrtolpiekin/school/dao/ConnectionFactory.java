package ua.com.foxminded.volodymyrtolpiekin.school.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static ua.com.foxminded.volodymyrtolpiekin.school.Constants.*;

public final class ConnectionFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionFactory.class);
    private static ConnectionFactory instance;
    private static final Connection CONNECTION = null;

    private ConnectionFactory() {}

    public Connection getConnection(){
        try {
            if (CONNECTION == null){
                return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
        } catch (SQLException e) {
            LOGGER.error("Database Connection Creation Failed : %s", e);
        }
        return CONNECTION;
    }

    public static ConnectionFactory getInstance() {
        if (instance == null) {
            instance = new ConnectionFactory();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new ConnectionFactory();
                }
            } catch (SQLException e) {
                LOGGER.error("Database Connection Creation Failed : %s", e);
            }
        }
        return instance;
    }
}
