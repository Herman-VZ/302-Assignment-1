package com.example.aiconceptsexplorer.leaderboardscreen;

import com.example.aiconceptsexplorer.models.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO() {
        this.connection = SqliteConnection.getInstance();
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


    public void updateUserScore(String email, int increment) {
        String sql = "UPDATE users SET score = score + ? WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, increment);
            pstmt.setString(2, email);

            int rows = pstmt.executeUpdate();
            System.out.println("⚙️ updateUserScore called: email=" + email
                    + ", increment=" + increment
                    + " → rows affected = " + rows);

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