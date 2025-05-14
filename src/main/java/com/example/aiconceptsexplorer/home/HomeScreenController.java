package com.example.aiconceptsexplorer.home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomeScreenController {


    @FXML
    private ListView<String> leaderboardList;

    private Runnable navigateToLeaderboard;
    private Runnable navigateToAccount;
    private Runnable navigateToLogin;
    private Runnable navigateToLearn;
    private Runnable navigateToLesson;

    // This method allows setting the navigation actions for the controller
    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toLogin, Runnable toLearn, Runnable toLesson) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToLogin = toLogin;
        this.navigateToLearn = toLearn;
        this.navigateToLesson = toLesson;
    }

    @FXML
    public void initialize() {
        refreshLeaderboard();
    }

    private void refreshLeaderboard() {
        leaderboardList.getItems().clear();

        // Query to get the user names and scores from the database, ordered by score descending
        List<String> leaderboard = getLeaderboardData();

        // Add the leaderboard data to the ListView
        leaderboardList.getItems().addAll(leaderboard);
    }

    @FXML
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

    // Navigation methods for buttons
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }

    @FXML
    public void ContinueLesson(ActionEvent actionEvent) {
        if (navigateToLesson != null) {
            navigateToLesson.run();
        }
    }

    public void onLearnTabClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }

    @FXML
    public void onLogoutClick(ActionEvent actionEvent) {
        if (navigateToLogin != null) {
            navigateToLogin.run();
        }
    }

}
