package com.example.aiconceptsexplorer.account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages database operations for user accounts using SQLite.
 * Provides methods to initialise the database and fetch user info.
 */
public class AccountDatabaseManager {

    /**
     * Establishes & returns a connection to the SQLite database.
     */
    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /**
     * Initializes the database by creating the 'users' table if it does not exist.
     * The table includes columns for id, email, password, latest lesson,
     * latest achievement, lesson streak, achievement, score and medal.
     */
    public static void initializeDatabase() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "email TEXT, " +
                    "password TEXT, " +
                    "latest_lesson TEXT, " +            // New column for latest lesson
                    "latest_achievement TEXT, " +        // New column for latest achievement
                    "lesson_streak INTEGER, " +          // New column for lesson streak
                    "achievement TEXT, " +               // Existing column for achievement
                    "score INT, " +
                    "medal TEXT)";                       // Existing column for medal
            statement.execute(createTableSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetch all usernames and their latest lesson, achievement, and streak from the database
     */
    public static List<String> getAllUserNames() {
        List<String> userNames = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT name FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("name");

                // Create a string with full name, lesson, achievement, and streak
                userNames.add(firstName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userNames;
    }
}


