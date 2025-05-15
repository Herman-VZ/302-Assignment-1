package com.example.aiconceptsexplorer.leaderboardscreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LeaderDatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);


            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}