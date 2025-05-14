package com.example.aiconceptsexplorer.learnscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LessonController {

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

    private Runnable navigateToLeaderboard;
    private Runnable navigateToAccount;
    private Runnable navigateToGlossary;
    private Runnable navigateToLogin;
    private Runnable navigateToSearchLesson;
    private Runnable navigateToSearchQuiz;



    public enum SelectedOption {
        NONE, // Default state
        LESSONS_CONTINUE,
        LESSONS_SEARCH,
        QUIZ_CONTINUE,
        QUIZ_SEARCH,
        GLOSSARY_CONTINUE,
    }

    private SelectedOption mostRecentSelection = SelectedOption.NONE;

    // This method allows setting the navigation actions for the controller
    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toGlossary, Runnable toLogin, Runnable toSearchQuiz, Runnable toSearchLesson) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToGlossary = toGlossary;
        this.navigateToLogin = toLogin;
        this.navigateToSearchQuiz = toSearchQuiz;
        this.navigateToSearchLesson = toSearchLesson;
    }

    public void onFirstButtonClick(ActionEvent actionEvent) {
        // Implement specific functionality for the first button, if needed
    }

    public void onSecondButtonClick(ActionEvent actionEvent) {
        // Implement specific functionality for the second button, if needed
    }

    public void onThirdButtonClick(ActionEvent actionEvent) {
        // Implement specific functionality for the third button, if needed
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
        continueLessonDiamond.setFill(javafx.scene.paint.Color.web("#E6E6E6"));
        searchLessonDiamond.setFill(javafx.scene.paint.Color.web("#A6A6A6"));
        mostRecentSelection = SelectedOption.LESSONS_CONTINUE;
    }

    public void onSearchLessonButtonClick(ActionEvent actionEvent) {
        searchLessonDiamond.setFill(javafx.scene.paint.Color.web("#E6E6E6"));
        continueLessonDiamond.setFill(javafx.scene.paint.Color.web("#A6A6A6"));
        mostRecentSelection = SelectedOption.LESSONS_SEARCH;
    }

    public void onContinueQuizButtonClick(ActionEvent actionEvent) {
        continueQuizDiamond.setFill(javafx.scene.paint.Color.web("#E6E6E6"));
        searchQuizDiamond.setFill(javafx.scene.paint.Color.web("#A6A6A6"));
        mostRecentSelection = SelectedOption.QUIZ_CONTINUE;
    }

    public void onSearchQuizButtonClick(ActionEvent actionEvent) {
        searchQuizDiamond.setFill(javafx.scene.paint.Color.web("#E6E6E6"));
        continueQuizDiamond.setFill(javafx.scene.paint.Color.web("#A6A6A6"));
        mostRecentSelection = SelectedOption.QUIZ_SEARCH;
    }

    public void onContinueGlossaryButtonClick(ActionEvent actionEvent) {
        mostRecentSelection = SelectedOption.GLOSSARY_CONTINUE;
    }


    public void onConfirmButtonClick(ActionEvent actionEvent) {
        switch (mostRecentSelection) {
            case LESSONS_CONTINUE:
                loadPage("ContinueLessons.fxml");
                break;
            case LESSONS_SEARCH:
                if (navigateToSearchLesson != null) {
                    navigateToSearchLesson.run();
                }
                break;
            case QUIZ_CONTINUE:
                loadPage("ContinueQuiz.fxml");
                break;
            case QUIZ_SEARCH:
                if (navigateToSearchQuiz != null) {
                    navigateToSearchQuiz.run();
                }
                break;
            case GLOSSARY_CONTINUE:
                if (navigateToGlossary != null) {
                    navigateToGlossary.run();
                }
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

    private void loadPage(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load page: " + fxmlFile);
        }
    }

    private void toggleVisibility(VBox options) {
        // Hide all sections first
        lessonOptions.setVisible(false);
        quizOptions.setVisible(false);
        glossaryOptions.setVisible(false);

        // Then show the selected one
        options.setVisible(true);
    }

    // These methods handle the navigation when the user clicks the respective tabs
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    @FXML
    public void onlogoutButtonClick (ActionEvent actionevent) {
        if (navigateToLogin != null) {
            navigateToLogin.run();
        }
    }


    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }
}
