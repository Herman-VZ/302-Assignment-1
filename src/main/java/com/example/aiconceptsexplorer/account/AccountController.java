package com.example.aiconceptsexplorer.account;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountController {
    @FXML private Text profileNameText;
    @FXML private Text latestLessonText;
    @FXML private Text currentStreakText;
    @FXML private Text latestAchievementText;

    private Runnable navigateToLeaderboard;
    private Runnable navigateToLearn;
    private Runnable navigateToLogOut;

    private String currentUserEmail;

    // Method to set the email of the signed-in user
    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
        loadUserDataForEmail(currentUserEmail);  // Load data when email is set
    }

    @FXML
    public void initialize() {
        if (currentUserEmail != null) {
            loadUserDataForEmail(currentUserEmail);  // Ensure data is loaded on initialization
        } else {
            profileNameText.setText("No user signed in");
        }
    }

    private void loadUserDataForEmail(String email) {
        if (email == null) {
            profileNameText.setText("No user signed in");
            return;
        }

        try (Connection conn = AccountDatabaseManager.getConnection()) {
            String query = "SELECT name, latest_lesson, lesson_streak, latest_achievement FROM users WHERE email = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String latestLesson = rs.getString("latest_lesson");
                int streak = rs.getInt("lesson_streak");
                String achievement = rs.getString("latest_achievement");

                profileNameText.setText(name);
                latestLessonText.setText("Latest Lesson: " + latestLesson);
                currentStreakText.setText("Lesson Streak: " + streak);
                latestAchievementText.setText("Latest Achievement: " + achievement);
            } else {
                profileNameText.setText("User not found.");
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/account/trophy-popup.fxml"));
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

    public void setNavigation(Runnable toLeaderboard, Runnable toLearn, Runnable toLogout) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToLearn = toLearn;
        this.navigateToLogOut = toLogout;
    }

    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    @FXML
    public void onLogoutClick(ActionEvent actionEvent) {
        if (navigateToLogOut != null) {
            navigateToLogOut.run();
        }
    }

    @FXML
    public void onLearnTabClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }
}
