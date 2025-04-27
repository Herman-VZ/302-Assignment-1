package com.example.learnscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloController {
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
    @FXML
    private Button confirmButton; // Ensure this matches the FX ID in your FXML

    public enum SelectedOption {
        NONE, // Default state
        LESSONS_CONTINUE,
        LESSONS_SEARCH,
        QUIZ_CONTINUE,
        QUIZ_SEARCH,
        GLOSSARY_CONTINUE,
        GLOSSARY_SEARCH
    }
    private SelectedOption mostRecentSelection = SelectedOption.NONE;

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
        mostRecentSelection = SelectedOption.LESSONS_CONTINUE;
    }

    public void onQuizButtonClick(ActionEvent actionEvent) {
        toggleVisibility(quizOptions);
        mostRecentSelection = SelectedOption.QUIZ_CONTINUE;
    }

    public void onGlossaryButtonClick(ActionEvent actionEvent) {
        toggleVisibility(glossaryOptions);
        mostRecentSelection = SelectedOption.GLOSSARY_CONTINUE;
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
        mostRecentSelection = SelectedOption.GLOSSARY_CONTINUE;
    }

    public void onSearchGlossaryButtonClick(ActionEvent actionEvent) {
        searchGlossaryDiamond.setFill(javafx.scene.paint.Color.WHITE);
        continueGlossaryDiamond.setFill(javafx.scene.paint.Color.GRAY);
    }

    public void onConfirmButtonClick(ActionEvent actionEvent) {
        switch (mostRecentSelection) {
            case LESSONS_CONTINUE:
                loadPage("ContinueLessons.fxml");
                break;
            case LESSONS_SEARCH:
                loadPage("SearchLessons.fxml");
                break;
            case QUIZ_CONTINUE:
                loadPage("ContinueQuiz.fxml");
                break;
            case QUIZ_SEARCH:
                loadPage("SearchQuiz.fxml");
                break;
            case GLOSSARY_CONTINUE:
                loadPage("ContinueGlossary.fxml");
                break;
            case GLOSSARY_SEARCH:
                loadPage("SearchGlossary.fxml");
                break;
            case NONE:
                showError("Please select an option first!");
                break;
        }
    }
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Generic method to load any FXML page
    private void loadPage(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) confirmButton.getScene().getWindow(); // Replace with your Confirm button
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load page: " + fxmlFile);
        }
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