package com.example.aiconceptsexplorer.leaderboardscreen;

import com.example.aiconceptsexplorer.models.SqliteConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) for user related operations in the leaderboard system.
 * Provides methods to interact with the users table in the database such as retrieving
 * leaderboard data, updating scores, adding users, and updating lesson progress.
 */
public class UserDAO {
    private Connection connection;

    /**
     * Constructs a new UserDAO and initializes the database connection.
     */
    public UserDAO() {
        this.connection = SqliteConnection.getInstance();
    }

    /**
     * Retrieves the leaderboard as a list of formatted strings, ordered by score decending.
     * @return a list of leaderboard entries in the format "rank. name - score points"
     */
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


    /**
     * Increments the score of a user identified by their email
     * @param email the email of the user whose score is to be updated
     * @param increment the amount to increment the user's score by
     */
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


    /**
     * Adds new user to the database.
     * @param name the name of the user
     * @param email the email of the user
     * @param score the initial score of the user
     */
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

    /**
     * Updates the latest lesson completed by a user
     * @param email the email of the user
     * @param latest_lesson the identifier of the latest lesson completed.
     */
    public void updateUserLesson(String email, String latest_lesson) {
        String query = "UPDATE users SET latest_lesson = ? WHERE email = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, latest_lesson);  // Set the new lesson
            pstmt.setString(2, email);          // Identify the user by email
            pstmt.executeUpdate();              // Execute the update
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}