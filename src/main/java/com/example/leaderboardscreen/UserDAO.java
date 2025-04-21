package com.example.leaderboardscreen;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = DatabaseConnection.connect();
    }

    public List<String> getLeaderboard() {
        List<String> leaderboard = new ArrayList<>();
        String query = "SELECT name, score FROM users ORDER BY score DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            int rank = 1;
            while (rs.next()) {
                String entry = String.format("%d. %s - %d points",
                        rank++,
                        rs.getString("name"),
                        rs.getInt("score"));
                leaderboard.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboard;
    }

    // ... rest of the UserDAO methods remain the same ...
    public void updateScore(String name, int score) {
        String query = "UPDATE users SET score = ? WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, score);
            pstmt.setString(2, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addUser(String name, String email, int score) {
        String query = "INSERT INTO users (name, email, score) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, score);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}