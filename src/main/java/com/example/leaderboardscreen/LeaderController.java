package com.example.leaderboardscreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.util.List;

public class LeaderController {
    @FXML private ListView<String> userListView;
    private Runnable navigateToAccount;

    @FXML
    public void initialize() {
        refreshLeaderboard();
    }

    public void setNavigationToAccount(Runnable navigationHandler) {
        this.navigateToAccount = navigationHandler;
    }

    public void refreshLeaderboard() {
        UserDAO userDAO = new UserDAO();
        List<String> leaderboard = userDAO.getLeaderboard();
        ObservableList<String> observableLeaderboard = FXCollections.observableArrayList(leaderboard);
        userListView.setItems(observableLeaderboard);
    }

    @FXML
    public void onFirstButtonClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }

    @FXML
    public void onSecondButtonClick(ActionEvent actionEvent) {
        refreshLeaderboard();
    }

    @FXML
    public void onThirdButtonClick(ActionEvent actionEvent) {
        // Existing third button functionality
    }
}