package com.example.account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class AccountController {
    @FXML private Text profileNameText;
    @FXML private Text latestLessonText;
    @FXML private Text currentStreakText;
    @FXML private Text latestAchievementText;

    private Runnable navigateToLeaderboard;
    private Runnable navigateToLearn;

    private List<String> userNames;
    private int currentUserIndex = 0;

    @FXML
    public void initialize() {
        loadAllUserNames();
    }

    public void setNavigation(Runnable toLeaderboard, Runnable toLearn) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToLearn = toLearn;
    }

    private void loadAllUserNames() {
        userNames = AccountDatabaseManager.getAllUserNames();
        if (!userNames.isEmpty()) {
            profileNameText.setText(userNames.get(0));
            loadUserData();
        } else {
            profileNameText.setText("No users available");
        }
    }

    public void OnUserInfoClick(ActionEvent actionEvent) {
        if (userNames.isEmpty()) {
            profileNameText.setText("No users available");
            return;
        }

        currentUserIndex = (currentUserIndex + 1) % userNames.size();
        String currentUserName = userNames.get(currentUserIndex);
        profileNameText.setText(currentUserName);
        loadUserData();
    }

    private void loadUserData() {
        try (Connection conn = AccountDatabaseManager.getConnection()) {
            String query = "SELECT first_name, last_name, latest_lesson, lesson_streak, latest_achievement " +
                    "FROM users LIMIT ?, 1";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, currentUserIndex);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                profileNameText.setText(firstName + " " + lastName);

                String latestLesson = rs.getString("latest_lesson");
                int currentStreak = rs.getInt("lesson_streak");
                String latestAchievement = rs.getString("latest_achievement");

                latestLessonText.setText("Latest Lesson: " + latestLesson);
                currentStreakText.setText("Lesson Streak: " + currentStreak);
                latestAchievementText.setText("Latest Achievement: " + latestAchievement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            profileNameText.setText("Error loading user");
        }
    }

    public void TrophyDisplay1(ActionEvent actionEvent) {
        handleTrophyPopup(actionEvent);
    }

    public void TrophyDisplay2(ActionEvent actionEvent) {
        handleTrophyPopup(actionEvent);
    }

    public void TrophyDisplay3(ActionEvent actionEvent) {
        handleTrophyPopup(actionEvent);
    }

    private void handleTrophyPopup(ActionEvent actionEvent) {
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

    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    @FXML
    public void onLearnTabClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }
}
