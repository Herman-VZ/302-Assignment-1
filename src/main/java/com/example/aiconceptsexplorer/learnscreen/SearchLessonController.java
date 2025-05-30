package com.example.aiconceptsexplorer.learnscreen;

import com.example.aiconceptsexplorer.leaderboardscreen.UserDAO;
import com.example.aiconceptsexplorer.models.SqliteUserDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller for the Search Lesson Screen.
 * Handles lesson searching, filtering and navigation actions.
 */
public class SearchLessonController {

    private Runnable navigateToAccount;
    private Runnable navigateToLeaderboard;
    private Runnable navigateToLearn;
    private Runnable navigateToLesson;

    @FXML
    private TextField searchField;

    private String currentUserEmail;
    private UserDAO userDAO;

    /**
     * Sets the current users email.
     * @param email the users email address.
     */
    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
    }

    /**
     * Sets the UserDAO for user data acess.
     * @param userDAO the UserDAO imp[lementation.
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Indicates if a lesson has been started.
     */
    public static boolean lesson_started = false;

    @FXML
    private ListView<String> LessonList;

    private ObservableList<String> lessons;


    /**
     * Initialises the controller, sets up the lesson list and search filter.
     */
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

    /**
     * Sets the navigation runnables for different tabs/screens.
     * @param toAccount runnable for Account navigation
     * @param toLeaderboard runnable for Leaderboard navigation
     * @param toLearn runnable for Learn navigation
     * @param toLesson runnable for Lesson navigation
     */
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

            System.out.println("QuizPageController: email=" + currentUserEmail);
            userDAO.updateUserLesson(currentUserEmail,"Intro to ai");
        }

    }

    /**
     * Handles the action when the Start Lesson tab is clicked.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }

    /**
     * Handles the action when the leaderboard tab is clicked.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    /**
     * Handles the action when the Learn tab is clicked.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onLearnTabClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }
}
