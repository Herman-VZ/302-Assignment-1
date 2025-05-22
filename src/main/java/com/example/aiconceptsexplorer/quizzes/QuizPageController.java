package com.example.aiconceptsexplorer.quizzes;

import com.example.aiconceptsexplorer.leaderboardscreen.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

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


    public void setCurrentUserEmail(String email) {
        this.currentUserEmail = email;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

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


    public static class ResultsData {
        public final int score;
        public final int total;
        public final List<String> incorrectResults;

        public ResultsData(int score, int total, List<String> incorrectResults) {
            this.score = score;
            this.total = total;
            this.incorrectResults = incorrectResults;
        }
    }

    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toLearn, Consumer<ResultsData> ToResults) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToLearn = toLearn;
        this.navigateToResults = ToResults;
    }


    @FXML
    public void onAccountTabClick() {
        if (navigateToAccount != null) navigateToAccount.run();
    }

    @FXML
    public void onLeaderboardTabClick() {
        if (navigateToLeaderboard != null) navigateToLeaderboard.run();
    }

    @FXML
    public void onLearnTabClick() {
        if (navigateToLearn != null) navigateToLearn.run();
    }
}
