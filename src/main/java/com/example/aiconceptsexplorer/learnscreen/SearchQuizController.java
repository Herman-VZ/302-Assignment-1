package com.example.aiconceptsexplorer.learnscreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;


public class SearchQuizController {

    private Runnable navigateToAccount;
    private Runnable navigateToLeaderboard;
    private Runnable navigateToLearn;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> quizList;

    private ObservableList<String> quizzes;


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

    public void setNavigation(Runnable toAccount, Runnable toLeaderboard, Runnable toLearn) {
        this.navigateToAccount = toAccount;
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToLearn = toLearn;
    }

    @FXML
    private void onStartQuizClick(ActionEvent event) {

    }

    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }

    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    @FXML
    public void onLearnTabClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }
}
