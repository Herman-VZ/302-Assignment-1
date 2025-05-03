package com.example.aiconceptsexplorer.leaderboardscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LeaderController {

    @FXML
    private ListView<String> userListView;

    private Runnable navigateToAccount;
    private Runnable navigateToLearn;
    private Runnable navigateToLogin;

    @FXML
    public void initialize() {
        refreshLeaderboard();
    }

    public void setNavigation(Runnable toAccount, Runnable toLearn, Runnable toLogin) {
        this.navigateToAccount = toAccount;
        this.navigateToLearn = toLearn;
        this.navigateToLogin = toLogin;
    }

    private void refreshLeaderboard() {
        userListView.getItems().clear();

        // Query to get the user names and scores from the database, ordered by score descending
        List<String> leaderboard = getLeaderboardData();

        // Add the leaderboard data to the ListView
        userListView.getItems().addAll(leaderboard);
    }

    private List<String> getLeaderboardData() {
        List<String> leaderboard = new ArrayList<>();

        String url = "jdbc:sqlite:database.db"; // Replace with your database path
        String sql = "SELECT name, score FROM users ORDER BY score DESC"; // Adjust based on your table structure

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String username = rs.getString("name");
                int score = rs.getInt("score");
                leaderboard.add(username + " - " + score + " points");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly in a real app
        }

        return leaderboard;
    }

    @FXML
    public void onFirstButtonClick(ActionEvent actionEvent) throws IOException {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }

    @FXML
    public void onLogOutClick(ActionEvent actionEvent) throws IOException {
        if (navigateToLogin != null) {
            navigateToLogin.run();
        }
    }

    @FXML
    public void onSecondButtonClick(ActionEvent actionEvent) {
        refreshLeaderboard();
    }

    @FXML
    public void onThirdButtonClick(ActionEvent actionEvent) throws IOException {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }
}
