package com.example.aiconceptsexplorer.leaderboardscreen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Provides a connection to the leaderboard database using SQLite.
 * This class offers a static method to establish a connection to the database.
 */
public class LeaderDatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:database.db";

    /**
     * Establishes and returns a connection to the leaderboard database.
     * @return a {@link Connection} onject to the SQLite database, or {@code null} if the connection could not be established.
     */
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