package com.example.employeedb.Constant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public static final String username = "postgres";
    public static final String password = "root";
    public static final String url = "jdbc:postgresql://localhost:5432/EIS";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
