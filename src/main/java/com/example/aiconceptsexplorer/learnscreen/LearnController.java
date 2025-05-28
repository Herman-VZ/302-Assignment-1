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

/**
 * Controller for the Learn Screen
 * Handles navigation and user interactions for lessons, quizzes and glossary.
 * Provides methods for setting navigation buttons, handling button clicks, and updating UI state.
 */
public class LearnController {

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
    private Runnable navigateToHome;
    private Runnable navigateToSearchLesson;
    private Runnable navigateToSearchQuiz;


    /**
     * Enum representing the current selected option in the learn screen.
     */
    public enum SelectedOption {
        NONE, // Default state
        LESSONS_CONTINUE,
        LESSONS_SEARCH,
        QUIZ_CONTINUE,
        QUIZ_SEARCH,
        GLOSSARY_CONTINUE,
    }

    private SelectedOption mostRecentSelection = SelectedOption.NONE;

    /**
     * Sets the navigation actions for the controller.
     * @param toLeaderboard runnable to navigate to the leaderboard screen
     * @param toAccount runnable to navigate to the account screen
     * @param toGlossary runnable to navigate to the glossary screen
     * @param toHome runnable to navigate to the home screen
     * @param toSearchQuiz runnable to navigate to the search quiz screen
     * @param toSearchLesson runnable to navigate to the search lesson screen
     */
    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toGlossary, Runnable toHome, Runnable toSearchQuiz, Runnable toSearchLesson) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToGlossary = toGlossary;
        this.navigateToHome = toHome;
        this.navigateToSearchQuiz = toSearchQuiz;
        this.navigateToSearchLesson = toSearchLesson;
    }

    /**
     * Handles the action when the first button is clicked.
     * @param actionEvent the action event triggered by the button click
     */
    public void onFirstButtonClick(ActionEvent actionEvent) {
        // Implement specific functionality for the first button, if needed
    }

    /**
     * Handles the action when the second button is clicked.
     * @param actionEvent the action event triggered by the button click
     */
    public void onSecondButtonClick(ActionEvent actionEvent) {
        // Implement specific functionality for the second button, if needed
    }

    /**
     * Handles the action when the third button is clicked.
     * @param actionEvent the action event triggered by the button click
     */
    public void onThirdButtonClick(ActionEvent actionEvent) {
        // Implement specific functionality for the third button, if needed
    }

    /**
     * Handles the action when the lesson button is clicked.
     * Shows lesson options and updates the selection state
     * @param actionEvent the action event triggered by the button click
     */
    public void onLessonButtonClick(ActionEvent actionEvent) {
        toggleVisibility(lessonOptions);
        mostRecentSelection = SelectedOption.LESSONS_CONTINUE;
    }

    /**
     * Handles the action when the quiz button is clicked.
     * Shows quiz options and updates the selection state
     * @param actionEvent the action event triggered by the button click
     */
    public void onQuizButtonClick(ActionEvent actionEvent) {
        toggleVisibility(quizOptions);
        mostRecentSelection = SelectedOption.QUIZ_CONTINUE;
    }

    /**
     * Handles the action when the glossary button is clicked.
     * Shows glossary options and updates the selection state
     * @param actionEvent the action event triggered by the button click
     */
    public void onGlossaryButtonClick(ActionEvent actionEvent) {
        toggleVisibility(glossaryOptions);
        mostRecentSelection = SelectedOption.GLOSSARY_CONTINUE;
    }

    /**
     * Handles the action when the continue lesson button is clicked.
     * Updates the diamond indicators and selection state.
     * @param actionEvent the action event triggered by the button click
     */
    public void onContinueLessonButtonClick(ActionEvent actionEvent) {
        continueLessonDiamond.setFill(javafx.scene.paint.Color.web("#E6E6E6"));
        searchLessonDiamond.setFill(javafx.scene.paint.Color.web("#A6A6A6"));
        mostRecentSelection = SelectedOption.LESSONS_CONTINUE;
    }

    /**
     * Handles the action when the search lesson button is clicked.
     * Updates the diamond indicators and selection state.
     * @param actionEvent the action event triggered by the button click
     */
    public void onSearchLessonButtonClick(ActionEvent actionEvent) {
        searchLessonDiamond.setFill(javafx.scene.paint.Color.web("#E6E6E6"));
        continueLessonDiamond.setFill(javafx.scene.paint.Color.web("#A6A6A6"));
        mostRecentSelection = SelectedOption.LESSONS_SEARCH;
    }

    /**
     * Handles the action when the continue quiz button is clicked.
     * Updates the diamond indicators and selection state.
     * @param actionEvent the action event triggered by the button click
     */
    public void onContinueQuizButtonClick(ActionEvent actionEvent) {
        continueQuizDiamond.setFill(javafx.scene.paint.Color.web("#E6E6E6"));
        searchQuizDiamond.setFill(javafx.scene.paint.Color.web("#A6A6A6"));
        mostRecentSelection = SelectedOption.QUIZ_CONTINUE;
    }

    /**
     * Handles the action when the search quiz button is clicked.
     * Updates the diamond indicators and selection state.
     * @param actionEvent the action event triggered by the button click
     */
    public void onSearchQuizButtonClick(ActionEvent actionEvent) {
        searchQuizDiamond.setFill(javafx.scene.paint.Color.web("#E6E6E6"));
        continueQuizDiamond.setFill(javafx.scene.paint.Color.web("#A6A6A6"));
        mostRecentSelection = SelectedOption.QUIZ_SEARCH;
    }

    /**
     * Handles the action when the continue glossary button is clicked.
     * Updates the selection state.
     * @param actionEvent the action event triggered by the button click
     */
    public void onContinueGlossaryButtonClick(ActionEvent actionEvent) {
        mostRecentSelection = SelectedOption.GLOSSARY_CONTINUE;
    }


    /**
     * Handles the action when the confitm button is clicked.
     * Navigates or loads the appropriate page based on the most recent selection.
     * @param actionEvent the action event triggered by the button click.
     */
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

    /**
     * Handles the action when leaderboard tab is clicked.
     * Navigates to the leaderboard screen if the navigation is set.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    /**
     * Handles the action when the logout button is clicked.
     * Navigates to the home screen if the navigation is set.
     * @param actionevent the action event triggered by the button click
     */
    @FXML
    public void onlogoutButtonClick (ActionEvent actionevent) {
        if (navigateToHome != null) {
            navigateToHome.run();
        }
    }

    /**
     * Handles the action when the account tab is clicked.
     * Navigates to the account screen if the navigation is set.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }
}
