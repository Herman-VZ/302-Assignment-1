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

/**
 * Controller class for the Account view
 * Handles user profile display, navigation, and trophy popups.
 */
public class AccountController {
    @FXML private Text profileNameText;
    @FXML private Text latestLessonText;
    @FXML private Text currentStreakText;
    @FXML private Text latestAchievementText;

    private Runnable navigateToLeaderboard;
    private Runnable navigateToLearn;
    private Runnable navigateToLogOut;

    private String currentUserEmail;

    /**
     * Method sets the email of the signed-in user and loads their data.
     * @param email the users email address
     */
    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
        loadUserDataForEmail(currentUserEmail);  // Load data when email is set
    }

    /**
     * Initialises the controller after its root element has been processed.
     * Loads user data if the email is already set.
     */
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

    /**
     * Handles the display of first trophy popup.
     * Triggered by corresponding FXML button.
     * @param actionEvent
     */
    public void TrophyDisplay1(ActionEvent actionEvent) {
        handleTrophyPopup(actionEvent);
    }

    /**
     * Handles the display of second trophy popup.
     * Triggered by corresponding FXML button.
     * @param actionEvent
     */
    public void TrophyDisplay2(ActionEvent actionEvent) {
        handleTrophyPopup(actionEvent);
    }

    /**
     * Handles the display of third trophy popup.
     * Triggered by corresponding FXML button.
     * @param actionEvent
     */
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

    /**
     * Sets navigation actions for leaderboard, learn and logout buttons
     * @param toLeaderboard action to navigate to leaderboard
     * @param toLearn action to navigate to learn
     * @param toLogout action to log out the user
     */
    public void setNavigation(Runnable toLeaderboard, Runnable toLearn, Runnable toLogout) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToLearn = toLearn;
        this.navigateToLogOut = toLogout;
    }

    /**
     * Handles leaderboard tab click event.
     * Triggers navigation to the leaderboard if set.
     * @param actionEvent
     */
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    /**
     * Handles logout button click event.
     * Triggers logout action if set.
     * @param actionEvent
     */
    @FXML
    public void onLogoutClick(ActionEvent actionEvent) {
        if (navigateToLogOut != null) {
            navigateToLogOut.run();
        }
    }

    /**
     * Handles learn tab click event.
     * Triggers navigation to the learn screen if set.
     * @param actionEvent
     */
    @FXML
    public void onLearnTabClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }
}
