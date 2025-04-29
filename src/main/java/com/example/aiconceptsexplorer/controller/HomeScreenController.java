package com.example.aiconceptsexplorer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class HomeScreenController {

    @FXML
    private ListView<String> leaderboardList;

    @FXML
    public void initialize() {
        leaderboardList.getItems().addAll(
                "Add names from leaderboard"
        );
    }
}
