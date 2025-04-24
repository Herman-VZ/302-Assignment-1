package com.example.learnscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private ListView<String> userListView;
    @FXML
    private VBox lessonOptions;
    @FXML
    private VBox quizOptions;
    @FXML
    private VBox glossaryOptions;

    public void onFirstButtonClick(ActionEvent actionEvent) {

    }

    public void onSecondButtonClick(ActionEvent actionEvent) {
        // Add your functionality here
    }

    public void onThirdButtonClick(ActionEvent actionEvent) {
        // Add your functionality here
    }
    public void onLessonButtonClick(ActionEvent actionEvent) {
        toggleVisibility(lessonOptions);
    }
    public void onContinueLessonButtonClick(ActionEvent actionEvent) {

    }
    public void onSearchLessonButtonClick(ActionEvent actionEvent) {

    }
    public void onContinueQuizButtonClick(ActionEvent actionEvent) {

    }
    public void onSearchQuizButtonClick(ActionEvent actionEvent) {

    }
    public void onContinueGlossaryButtonClick(ActionEvent actionEvent) {

    }
    public void onSearchGlossaryButtonClick(ActionEvent actionEvent) {

    }
    public void onQuizButtonClick(ActionEvent actionEvent) {
        toggleVisibility(quizOptions);
    }
    public void onGlossaryButtonClick(ActionEvent actionEvent) {
        toggleVisibility(glossaryOptions);
    }
    public void onConfirmButtonClick(ActionEvent actionEvent) {

    }
    // Toggle visibility of the given section
    private void toggleVisibility(VBox options) {
        if (options.isVisible()) {
            options.setVisible(false);
        } else {
            // Otherwise, hide all sections and show the clicked one
            lessonOptions.setVisible(false);
            quizOptions.setVisible(false);
            glossaryOptions.setVisible(false);
            options.setVisible(true);
        }
    }
}