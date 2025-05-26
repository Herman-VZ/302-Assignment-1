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
    private Runnable navigateToLesson;

    @FXML
    private TextField searchField;

    public static boolean lesson_started = false;

    @FXML
    private ListView<String> LessonList;

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
        LessonList.setItems(lessons);

        searchField.textProperty().addListener((observable, oldText, newText) -> filterLessonList(newText));
    }

    private void filterLessonList(String query) {
        if (query == null || query.isEmpty()) {
            LessonList.setItems(lessons);
        } else {
            List<String> filtered = lessons.stream()
                    .filter(q -> q.toLowerCase().contains(query.toLowerCase()))
                    .collect(Collectors.toList());
            LessonList.setItems(FXCollections.observableArrayList(filtered));
        }
    }

    public void setNavigation(Runnable toAccount, Runnable toLeaderboard, Runnable toLearn, Runnable toLesson) {
        this.navigateToAccount = toAccount;
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToLearn = toLearn;
        this.navigateToLesson= toLesson;
    }

    @FXML
    private void onStartLessonClick(ActionEvent event) {
        if (navigateToLesson != null) {
            navigateToLesson.run();
            lesson_started = true;
        }
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
