package com.example.aiconceptsexplorer.quizzes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;

public class ResultsController {

    @FXML
    private Label scoreLabel;

    @FXML
    private ListView<String> resultsList;

    private Runnable navigateBackToLearn;

    public void initialize() {
        resultsList.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item);
                    setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
                }
            }
        });
    }
    public void initializeResults(int score, int total, List<String> incorrectResults) {
        scoreLabel.setText("Score: " + score + " out of " + total);
        resultsList.getItems().addAll(incorrectResults);
    }

    public void setNavigation(Runnable navigateBackToLearn) {
        this.navigateBackToLearn = navigateBackToLearn;
    }

    @FXML
    private void handleClose() {
        if (navigateBackToLearn != null) {
            navigateBackToLearn.run();
        }
    }
}