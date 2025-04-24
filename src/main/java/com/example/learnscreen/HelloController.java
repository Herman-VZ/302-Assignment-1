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
    @FXML
    private javafx.scene.shape.Polygon continueLessonDiamond;
    @FXML
    private javafx.scene.shape.Polygon searchLessonDiamond;
    @FXML
    private javafx.scene.shape.Polygon continueQuizDiamond;
    @FXML
    private javafx.scene.shape.Polygon searchQuizDiamond;
    @FXML
    private javafx.scene.shape.Polygon continueGlossaryDiamond; 
    @FXML
    private javafx.scene.shape.Polygon searchGlossaryDiamond;

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
        continueLessonDiamond.setFill(javafx.scene.paint.Color.WHITE);
        searchLessonDiamond.setFill(javafx.scene.paint.Color.GRAY);
    }
    public void onSearchLessonButtonClick(ActionEvent actionEvent) {
        searchLessonDiamond.setFill(javafx.scene.paint.Color.WHITE);
        continueLessonDiamond.setFill(javafx.scene.paint.Color.GRAY);
    }

    public void onContinueQuizButtonClick(ActionEvent actionEvent) {
        continueQuizDiamond.setFill(javafx.scene.paint.Color.WHITE);
        searchQuizDiamond.setFill(javafx.scene.paint.Color.GRAY);
    }
    public void onSearchQuizButtonClick(ActionEvent actionEvent) {
        searchQuizDiamond.setFill(javafx.scene.paint.Color.WHITE);
        continueQuizDiamond.setFill(javafx.scene.paint.Color.GRAY);
    }
    public void onContinueGlossaryButtonClick(ActionEvent actionEvent) {
        continueGlossaryDiamond.setFill(javafx.scene.paint.Color.WHITE);
        searchGlossaryDiamond.setFill(javafx.scene.paint.Color.GRAY);
    }
    public void onSearchGlossaryButtonClick(ActionEvent actionEvent) {
        searchGlossaryDiamond.setFill(javafx.scene.paint.Color.WHITE);
        continueGlossaryDiamond.setFill(javafx.scene.paint.Color.GRAY);
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