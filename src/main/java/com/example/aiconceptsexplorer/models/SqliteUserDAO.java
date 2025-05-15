package com.example.aiconceptsexplorer.models;

import java.sql.*;

public class SqliteUserDAO implements IUserDAO {
    private Connection connection;

    public SqliteUserDAO() {
        this.connection = SqliteConnection.getInstance();
        createTable();
    }


    private void createTable() {
        // Create table if not exists
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR NOT NULL, "
                    + "email VARCHAR NOT NULL, "
                    + "password VARCHAR NOT NULL, "
                    + "latest_achievement TEXT, "
                    + "latest_lesson Text, "
                    + "lesson_streak INTEGER, "
                    + "achievement TEXT, "
                    + "medal TEXT, "
                    + "score INTEGER NOT NULL DEFAULT 0 "
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteUserByEmail(String email) {
        String sql = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Or log
        }
    }

    @Override
    public void addUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO users (name, email, password) VALUES (?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginUser(String email, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) AS count FROM users WHERE email = ? AND password = ?"
            );
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int check = resultSet.getInt("count");
                return check == 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean validateEmail(User user) {
        String userEmail = user.getEmail();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT COUNT(*) AS count FROM users WHERE email = ?"
            );
            statement.setString(1, userEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int check = resultSet.getInt("count");
                return check != 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}