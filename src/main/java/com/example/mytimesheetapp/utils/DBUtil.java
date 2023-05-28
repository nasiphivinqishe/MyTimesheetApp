package com.example.mytimesheetapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private final static Logger logger = LogManager.getLogger(GetTimesheetByIdHandler.class.getName());

    public static Connection createConnectionViaUserPwd() throws Exception {
        String username ="postgres";
        String password ="postgres";
        String dbEndpoint = "postgresql://postgres:1_Uminathi@postgres:5432/mydb";
        String jdbc_url = "jdbc:postgresql://localhost:5432/mydb";

        try{
            Connection connection =DriverManager.getConnection(jdbc_url, username, password);
            logger.info(String.format("Connection Established to :%s", jdbc_url));
            return connection;
        }catch(SQLException e) {
            System.out.println("Failed to connect to PostgreSQL database!");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
