package com.example.aiconceptsexplorer.home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import com.example.aiconceptsexplorer.learnscreen.SearchLessonController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the Home Screen.
 * Handles the display of lessons and leaderboard, and manages navigation between different sections of the app.
 */
public class HomeScreenController {

    /**
     * ListView for displaying available lessons.
     */
    @FXML
    public ListView LessonList;

    @FXML
    private ListView<String> leaderboardList;

    private Runnable navigateToLeaderboard;
    private Runnable navigateToAccount;
    private Runnable navigateToLogin;
    private Runnable navigateToLearn;
    private Runnable navigateToLesson;

    /**
     * Method sets the navigation actions for the controller.
     * @param toLeaderboard runnable to navigate to the leaderboard view
     * @param toAccount runnable to navigate to the account view
     * @param toLogin runnable to navigate to the login view
     * @param toLearn runnable to navigate to the learn view
     * @param toLesson runnable to navigate to the lesson view
     */
    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toLogin, Runnable toLearn, Runnable toLesson) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToLogin = toLogin;
        this.navigateToLearn = toLearn;
        this.navigateToLesson = toLesson;
    }

    private ObservableList<String> lessons;

    /**
     * Initialises the home screen, populating the lessons and leaderboard.
     */
    @FXML
    public void initialize() {
        refreshLeaderboard();
        lessons = FXCollections.observableArrayList(
                "Intro to AI",
                "Machine Learning Basics",
                "Deep Learning Concepts"
        );
        LessonList.setItems(lessons);
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

    /**
     * Handles navigation to the leaderboard tab when the button is clicked.
     * @param actionEvent the action event triggered by clicking the leaderboard tab.
     */
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    /**
     * Handles navigation to the account tab.
     * @param actionEvent the action event triggered by clicking the account tab.
     */
    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }

    /**
     * Handles the action when a lesson is selected from the list.
     * @param actionEvent the action event triggered by selecting a lesson.
     */
    @FXML
    public void ContinueLesson(ActionEvent actionEvent) {
        if (navigateToLesson != null) {
            navigateToLesson.run();
        }
    }

    /**
     * Handles navigation to the learn tab.
     * @param actionEvent the action event triggered by clicking the learn tab.
     */
    public void onLearnTabClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }

    /**
     * Handles user logout and navigation to the login screen.
     * @param actionEvent the action event triggered by clicking the logout button.
     */
    @FXML
    public void onLogoutClick(ActionEvent actionEvent) {
        if (navigateToLogin != null) {
            navigateToLogin.run();
        }
    }

    /**
     * Handles starting a lesson.
     * @param actionEvent the action event triggered by clicking the start lesson button.
     */
    public void Startlesson(ActionEvent actionEvent) {
        if (navigateToLesson != null) {
            navigateToLesson.run();
        }
    }


}
