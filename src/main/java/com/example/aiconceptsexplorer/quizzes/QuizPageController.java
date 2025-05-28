package com.example.aiconceptsexplorer.quizzes;

import com.example.aiconceptsexplorer.leaderboardscreen.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Controller for the quiz page
 * Handles quiz question display, answer selection, scoring, and navigation.
 */
public class QuizPageController {

    @FXML private VBox questionsBox;
    @FXML private Button submitButton;

    private final List<ToggleGroup> toggleGroups = new ArrayList<>();
    private final List<Integer> correctAnswers = new ArrayList<>();

    private Runnable navigateToAccount;
    private Runnable navigateToLeaderboard;
    private Runnable navigateToLearn;
    private Consumer<ResultsData> navigateToResults;

    private String currentUserEmail;
    private UserDAO userDAO;


    /**
     * Sets the current users email
     * @param email the users email address.
     */
    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
    }

    /**
     * Sets the UserDAO for database operations.
     * @param userDAO the UserDAO instance
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Initialises the quiz page by creating question and answer options.
     * Called automatically by JavaFX after FXML loading.
     */
    @FXML
    public void initialize() {
        String[][] questions = {
                {"What does AI stand for?", "Artificial Intelligence", "Automated Interface", "Algorithm Integration", "Advanced Inference", "0"},
                {"Which of these is a type of machine learning?", "Supervised Learning", "Encrypted Mining", "Database Querying", "Stack Memory", "0"},
                {"Which language is popular for AI development?", "Python", "HTML", "CSS", "SQL", "0"},
                {"Which of these is NOT an AI application?", "Toaster Control", "Speech Recognition", "Image Classification", "Autonomous Vehicles", "0"},
                {"What is the purpose of a neural network?", "Mimic the human brain", "Increase storage space", "Send emails", "Manage databases", "0"},
                {"Which of the following is an AI-powered assistant?", "Siri", "Notepad", "Paint", "Excel", "0"},
                {"What is ‘training data’ in AI?", "Examples used to teach models", "A new software", "A computer virus", "None of the above", "0"}
        };

        for (int i = 0; i < questions.length; i++) {
            VBox questionBox = new VBox(10);
            Label questionLabel = new Label((i + 1) + ". " + questions[i][0]);
            questionLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

            ToggleGroup group = new ToggleGroup();
            toggleGroups.add(group);
            correctAnswers.add(Integer.parseInt(questions[i][5]));

            for (int j = 1; j <= 4; j++) {
                RadioButton option = new RadioButton(questions[i][j]);
                option.setToggleGroup(group);
                option.setStyle("-fx-text-fill: white;");
                questionBox.getChildren().add(option);
            }

            questionsBox.getChildren().addAll(questionLabel, questionBox);
        }
    }

    @FXML
    private void onSubmitClick() {
        int score = 0;
        int total = toggleGroups.size();
        List<String> incorrectResults = new ArrayList<>();

        for (int i = 0; i < toggleGroups.size(); i++) {
            ToggleGroup group = toggleGroups.get(i);
            int correctIndex = correctAnswers.get(i);
            Toggle selected = group.getSelectedToggle();

            Label questionLabel = (Label) questionsBox.getChildren().get(i * 2);
            String questionText = questionLabel.getText().substring(3);

            if (selected != null) {
                RadioButton selectedButton = (RadioButton) selected;
                RadioButton correctButton = (RadioButton) group.getToggles().get(correctIndex);
                if (selectedButton == correctButton) {
                    score++;
                } else {
                    incorrectResults.add(
                            (i+ 1) + ". " + questionText +
                                    "\nYour Answer: " + selectedButton.getText() +
                                    "\nCorrect Answer: " + correctButton.getText()
                    );
                }
            }
        }

        if (currentUserEmail != null) {
            System.out.println("QuizPageController: email=" + currentUserEmail + ", correctCount=" + score);
            userDAO.updateUserScore(currentUserEmail, score);
        } else {
            System.out.println("currentUserEmail or userDAO is null");
            return;
        }

        for (ToggleGroup group : toggleGroups) {
            for (Toggle toggle : group.getToggles()) {
                ((RadioButton) toggle).setDisable(true);
            }
        }

        submitButton.setDisable(true);

        if (navigateToResults != null) {
            navigateToResults.accept(new ResultsData(score, total, incorrectResults));
        }
    }


    /**
     * Data class representing the results of a quiz attempt.
     */
    public static class ResultsData {
        /** The users score */
        public final int score;
        /** The total number of questions */
        public final int total;
        /** List of incorrect results for feedback */
        public final List<String> incorrectResults;

        /**
         * Constructs a ResultsData object.
         *
         * @param score the users score
         * @param total the total number of questions
         * @param incorrectResults list of incorrect results for feedback
         */
        public ResultsData(int score, int total, List<String> incorrectResults) {
            this.score = score;
            this.total = total;
            this.incorrectResults = incorrectResults;
        }
    }

    /**
     * Sets the navigation actions for the quiz page.
     *
     * @param toLeaderboard runnable for navigating to the leaderboard view
     * @param toAccount runnable for navigating to the account view
     * @param toLearn runnable for navigating to the learn view
     * @param ToResults runnable for navigating to the results view with quiz results
     */
    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toLearn, Consumer<ResultsData> ToResults) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToLearn = toLearn;
        this.navigateToResults = ToResults;
    }


    /**
     * Handles the account tab click event, navigating to the account view.
     */
    @FXML
    public void onAccountTabClick() {
        if (navigateToAccount != null) navigateToAccount.run();
    }

    /**
     * Handles the leaderboard tab click event, navigating to the leaderboard view.
     */
    @FXML
    public void onLeaderboardTabClick() {
        if (navigateToLeaderboard != null) navigateToLeaderboard.run();
    }

    /**
     * Handles the learn tab click event, navigating to the learn view.
     */
    @FXML
    public void onLearnTabClick() {
        if (navigateToLearn != null) navigateToLearn.run();
    }
}
