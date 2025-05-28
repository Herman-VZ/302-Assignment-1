package com.example.aiconceptsexplorer.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for managing a SQLite database connection.
 * Provides a single shared {@link Connection} instance for the application.
 */
public class SqliteConnection {
    private static Connection instance = null;

    private SqliteConnection() {
        String url = "jdbc:sqlite:database.db";
        try {
            instance = DriverManager.getConnection(url);
        } catch (SQLException sqlEx) {
            System.err.println(sqlEx);
        }
    }

    /**
     * Returns the singleton {@link Connection} instance to the SQLite database.
     * If the connection does not exist, it will be created.
     * @return the singleton {@link Connection} instance
     */
    public static Connection getInstance() {
        if (instance == null) {
            new SqliteConnection();
        }
        return instance;
    }
}
