package com.line.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

public class ConnectionA implements ConnectionMaker{

     public Connection makeConnection() throws SQLException {
        Map<String, String> env = System.getenv();
        Connection conn = DriverManager.getConnection(env.get("DB_HOST"),
                env.get("DB_NAME"), env.get("DB_PASSWORD"));

        return conn;


    }
}
