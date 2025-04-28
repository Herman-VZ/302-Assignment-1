package com.example.account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class AccountController {

    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button userInfoButton;  // Button to cycle through user info
    @FXML
    private Text profileNameText;   // Text field to display user name
    @FXML
    private Text latestLessonText;  // Text field for Latest Lesson
    @FXML
    private Text currentStreakText; // Text field for Current Streak
    @FXML
    private Text latestAchievementText; // Text field for Latest Achievement

    private List<String> userNames;  // List to hold user names from the database
    private int currentUserIndex = 0;  // Index to track the current user

    // This method runs automatically after FXML loads
    @FXML
    public void initialize() {
        loadAllUserNames();  // Load all user names from the database
    }

    // Fetch all user names from the database
    private void loadAllUserNames() {
        userNames = DatabaseManager.getAllUserNames();
        if (!userNames.isEmpty()) {
            // Display the first user if available
            profileNameText.setText(userNames.get(0));
            loadUserData(); // Load data for the first user
        } else {
            profileNameText.setText("No users available");
        }
    }

    // Method to handle the action of clicking the "User Info" button
    public void OnUserInfoClick(ActionEvent actionEvent) {
        // Ensure there are user names available
        if (userNames.isEmpty()) {
            profileNameText.setText("No users available");
            return;
        }

        // Cycle to the next user
        currentUserIndex = (currentUserIndex + 1) % userNames.size();
        String currentUserName = userNames.get(currentUserIndex);
        profileNameText.setText(currentUserName);

        // Load data for the current user
        loadUserData();
    }

    private void loadUserData() {
        try (Connection conn = DatabaseManager.getConnection()) {
            // Query to get the user details
            String query = "SELECT first_name, last_name, latest_lesson, lesson_streak, latest_achievement " +
                    "FROM users LIMIT ?, 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, currentUserIndex);  // Offset to get the next user
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Set the profile name
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                profileNameText.setText(firstName + " " + lastName);

                // Set the parameters
                String latestLesson = rs.getString("latest_lesson");
                int currentStreak = rs.getInt("lesson_streak");
                String latestAchievement = rs.getString("latest_achievement");

                latestLessonText.setText("Latest Lesson: " + latestLesson);
                currentStreakText.setText("Lesson Streak: " + currentStreak);
                latestAchievementText.setText("Latest Achievement: " + latestAchievement);
            } else {
                profileNameText.setText("No user found");
                latestLessonText.setText("Latest Lesson: N/A");
                currentStreakText.setText("Lesson Streak: N/A");
                latestAchievementText.setText("Latest Achievement: N/A");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            profileNameText.setText("Error loading user");
        }
    }

    // Method to handle the action of clicking on TrophyDisplay1
    public void TrophyDisplay1(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trophy-popup.fxml"));
            Scene popupScene = new Scene(loader.load());
            Stage popupStage = new Stage();
            popupStage.setTitle("Trophies");
            popupStage.setScene(popupScene);
            popupStage.initModality(Modality.APPLICATION_MODAL);

            TrophyPopupController controller = loader.getController();
            controller.setButtonToUpdate(clickedButton);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Similarly for TrophyDisplay2 and TrophyDisplay3
    public void TrophyDisplay2(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trophy-popup.fxml"));
            Scene popupScene = new Scene(loader.load());
            Stage popupStage = new Stage();
            popupStage.setTitle("Trophies");
            popupStage.setScene(popupScene);
            popupStage.initModality(Modality.APPLICATION_MODAL);

            TrophyPopupController controller = loader.getController();
            controller.setButtonToUpdate(clickedButton);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void TrophyDisplay3(ActionEvent actionEvent) {
        Button clickedButton = (Button) actionEvent.getSource();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("trophy-popup.fxml"));
            Scene popupScene = new Scene(loader.load());
            Stage popupStage = new Stage();
            popupStage.setTitle("Trophies");
            popupStage.setScene(popupScene);
            popupStage.initModality(Modality.APPLICATION_MODAL);

            TrophyPopupController controller = loader.getController();
            controller.setButtonToUpdate(clickedButton);

            popupStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        // Handle leaderboard tab click if necessary
    }

    public void onLearnTabClick(ActionEvent actionEvent) {
        // Handle learn tab click if necessary
    }
}
