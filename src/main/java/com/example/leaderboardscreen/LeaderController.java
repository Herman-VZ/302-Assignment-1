package com.example.leaderboardscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class LeaderController {

    @FXML
    private ListView<String> userListView;

    private Runnable navigateToAccount;
    private Runnable navigateToLearn;

    @FXML
    public void initialize() {
        refreshLeaderboard();
    }

    public void setNavigation(Runnable toAccount, Runnable toLearn) {
        this.navigateToAccount = toAccount;
        this.navigateToLearn = toLearn;
    }

    private void refreshLeaderboard() {
        userListView.getItems().clear();
        userListView.getItems().addAll("User1 - 1000 points", "User2 - 900 points", "User3 - 850 points");
    }

    @FXML
    public void onFirstButtonClick(ActionEvent actionEvent) throws IOException {
        if (navigateToAccount != null) {
            navigateToAccount.run();
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
