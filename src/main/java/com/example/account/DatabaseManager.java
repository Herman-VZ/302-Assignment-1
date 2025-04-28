package com.example.account;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String URL = "jdbc:sqlite:accounts.db";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Method to initialize the database
    public static void initializeDatabase() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "first_name TEXT, " +
                    "last_name TEXT, " +
                    "latest_lesson TEXT, " +            // New column for latest lesson
                    "latest_achievement TEXT, " +        // New column for latest achievement
                    "lesson_streak INTEGER, " +          // New column for lesson streak
                    "achievement TEXT, " +               // Existing column for achievement
                    "medal TEXT)";                       // Existing column for medal
            statement.execute(createTableSQL);

            // Insert dummy data (if not already inserted)
            String checkDataSQL = "SELECT COUNT(*) FROM users";
            ResultSet rs = statement.executeQuery(checkDataSQL);
            if (rs.getInt(1) == 0) {
                String insertSQL = "INSERT INTO users (first_name, last_name, latest_lesson, latest_achievement, lesson_streak, achievement, medal) VALUES " +
                        "('John', 'Doe', 'lesson 1', 'achievement 1', 5, '', ''), " +  // Lesson streak for John is 5
                        "('Jane', 'Smith', 'lesson 2', 'achievement 2', 7, '', ''), " + // Lesson streak for Jane is 7
                        "('Chris', 'Johnson', 'lesson 3', 'achievement 3', 10, '', '')"; // Lesson streak for Chris is 10
                statement.executeUpdate(insertSQL);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch all user names and their latest lesson, achievement, and streak from the database
    public static List<String> getAllUserNames() {
        List<String> userNames = new ArrayList<>();
        try (Connection connection = getConnection()) {
            String query = "SELECT first_name, last_name FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");

                // Create a string with full name, lesson, achievement, and streak
                userNames.add(firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userNames;
    }
}
