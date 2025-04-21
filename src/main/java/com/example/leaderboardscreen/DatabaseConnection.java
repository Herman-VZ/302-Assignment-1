package com.example.leaderboardscreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:leaderboard.db";

    public static Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);

            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT NOT NULL, " +
                    "score INTEGER DEFAULT 0)";

            Statement stmt = connection.createStatement();
            stmt.execute(createTableSQL);

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}