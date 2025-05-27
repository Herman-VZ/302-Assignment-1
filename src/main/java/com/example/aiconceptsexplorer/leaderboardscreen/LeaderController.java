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

/**
 * Controller class for the leaderboard screen in the application.
 * Handles the display of user scores and navigation between different screens.
 */
public class LeaderController {

    @FXML
    private ListView<String> userListView;

    private Runnable navigateToAccount;
    private Runnable navigateToLearn;
    private Runnable navigateToHome;

    /**
     * Initialises the controller after its root element has been completely processed.
     * Refreshes the leaderbaord data.
     */
    @FXML
    public void initialize() {
        refreshLeaderboard();
    }

    /**
     * Sets the navigation actions for the controller.
     * @param toAccount Runnable to navigate to the account screen
     * @param toLearn Runnable to navigate to the learn screen
     * @param toHome Runnable to navigate to the home screen
     */
    public void setNavigation(Runnable toAccount, Runnable toLearn, Runnable toHome) {
        this.navigateToAccount = toAccount;
        this.navigateToLearn = toLearn;
        this.navigateToHome = toHome;
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

    /**
     * Handles the event when the first button is clicked.
     * Navigated to the account screen if the action is set.
     * @param actionEvent the action event triggered by the button click
     * @throws IOException if navigation fails
     */
    @FXML
    public void onFirstButtonClick(ActionEvent actionEvent) throws IOException {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }

    /**
     * Handles the event when the logout button is clicked
     * Navigates to the home screen if the action is set.
     * @param actionEvent the action event triggered by the button click
     * @throws IOException if navigation fails
     */
    @FXML
    public void onLogOutClick(ActionEvent actionEvent) throws IOException {
        if (navigateToHome != null) {
            navigateToHome.run();
        }
    }

    /**
     * Handles the event when the second button is clicked.
     * Refreshes the leaderboard data.
     * @param actionEvent the action event triggered by the button click
     */
    @FXML
    public void onSecondButtonClick(ActionEvent actionEvent) {
        refreshLeaderboard();
    }

    /**
     * Handles the event when the third button is clicked
     * Navigates to the learn screen if the action is set.
     * @param actionEvent the action event triggered by the button click
     * @throws IOException if navigation fails
     */
    @FXML
    public void onThirdButtonClick(ActionEvent actionEvent) throws IOException {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }
}
