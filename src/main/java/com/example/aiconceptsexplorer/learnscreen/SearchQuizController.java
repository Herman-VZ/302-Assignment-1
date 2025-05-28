package com.example.aiconceptsexplorer.learnscreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import com.example.aiconceptsexplorer.quizzes.QuizPageController;

/**
 * Controller for the Search Quiz Screen.
 * Handles quiz searching, filtering and navigation actions.
 */
public class SearchQuizController {

    private Runnable navigateToAccount;
    private Runnable navigateToLeaderboard;
    private Runnable navigateToLearn;
    private Runnable navigateToQuizz;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> quizList;

    private ObservableList<String> quizzes;


    /**
     * Initialises the controller, sets up the quiz list and search filter.
     */
    public void initialize() {
        quizzes = FXCollections.observableArrayList(
                "Intro to AI",
                "Machine Learning Basics",
                "Deep Learning Concepts",
                "AI Ethics",
                "Neural Networks",
                "Search Algorithms",
                "Natural Language Processing"
        );
        quizList.setItems(quizzes);

        searchField.textProperty().addListener((observable, oldText, newText) -> filterQuizList(newText));
    }

    private void filterQuizList(String query) {
        if (query == null || query.isEmpty()) {
            quizList.setItems(quizzes);
        } else {
            List<String> filtered = quizzes.stream()
                    .filter(q -> q.toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            quizList.setItems(FXCollections.observableArrayList(filtered));
        }

    }

    /**
     * Handles the action when Start Quiz button is clicked.
     * Navigates to the quiz if navigation is set.
     * @param actionEvent the action event triggered by the button click
     */
    public void onStartQuizClick(ActionEvent actionEvent) {
        if (navigateToQuizz != null) {
            navigateToQuizz.run();
        }
    }


    /**
     * Sets the naviation runnables for different tabs/screens.
     * @param toLeaderboard runnable for Leaderboard navigation
     * @param toAccount runnable for Account navigation
     * @param toLearn runnable for Learn navigation
     * @param toQuizz runnable for Quizz navigation
     */
    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toLearn, Runnable toQuizz) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToLearn = toLearn;
        this.navigateToQuizz = toQuizz;
    }

    /**
     * Handles the action when the Account tab is clicked
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }

    /**
     * Handles the action when the Leaderboard tab is clicked.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    /**
     * Handles the actiion when the Learn tab is clicked.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onLearnTabClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }
}
