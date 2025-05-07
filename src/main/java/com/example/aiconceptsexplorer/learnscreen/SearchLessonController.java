package com.example.aiconceptsexplorer.learnscreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;


public class SearchLessonController {

    private Runnable navigateToAccount;
    private Runnable navigateToLeaderboard;
    private Runnable navigateToLearn;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> lessonList;

    private ObservableList<String> lessons;


    public void initialize() {
        lessons = FXCollections.observableArrayList(
                "Intro to AI",
                "Machine Learning Basics",
                "Deep Learning Concepts",
                "AI Ethics",
                "Neural Networks",
                "Search Algorithms",
                "Natural Language Processing"
        );
        lessonList.setItems(lessons);

        searchField.textProperty().addListener((observable, oldText, newText) -> filterLessonList(newText));
    }

    private void filterLessonList(String query) {
        if (query == null || query.isEmpty()) {
            lessonList.setItems(lessons);
        } else {
            List<String> filtered = lessons.stream()
                    .filter(q -> q.toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            lessonList.setItems(FXCollections.observableArrayList(filtered));
        }
    }

    public void setNavigation(Runnable toAccount, Runnable toLeaderboard, Runnable toLearn) {
        this.navigateToAccount = toAccount;
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToLearn = toLearn;
    }

    @FXML
    private void onStartLessonClick(ActionEvent event) {

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
