package com.example.leaderboardscreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import java.util.List;

public class HelloController {
    @FXML
    private ListView<String> userListView;

    @FXML
    public void initialize() {
        refreshLeaderboard();
    }

    public void refreshLeaderboard() {
        UserDAO userDAO = new UserDAO();
        List<String> leaderboard = userDAO.getLeaderboard();
        ObservableList<String> observableLeaderboard = FXCollections.observableArrayList(leaderboard);
        userListView.setItems(observableLeaderboard);
    }

    public void onFirstButtonClick(ActionEvent actionEvent) {
        refreshLeaderboard(); // Refresh when button is clicked
    }

    public void onSecondButtonClick(ActionEvent actionEvent) {
        // Add your functionality here
    }

    public void onThirdButtonClick(ActionEvent actionEvent) {
        // Add your functionality here
    }
}