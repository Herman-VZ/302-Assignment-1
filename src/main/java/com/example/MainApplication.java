package com.example;

import com.example.aiconceptsexplorer.learnscreen.SearchLessonController;
import com.example.aiconceptsexplorer.learnscreen.SearchQuizController;
import com.example.aiconceptsexplorer.quizzes.ResultsController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import com.example.aiconceptsexplorer.account.AccountController;
import com.example.aiconceptsexplorer.learnscreen.LearnController;
import com.example.aiconceptsexplorer.leaderboardscreen.LeaderController;
import com.example.aiconceptsexplorer.learnscreen.GlossaryController;
import com.example.aiconceptsexplorer.controllers.loginController;
import com.example.aiconceptsexplorer.controllers.signupController;
import com.example.aiconceptsexplorer.lessons.lessoncontroller;
import com.example.aiconceptsexplorer.home.HomeScreenController;
import com.example.aiconceptsexplorer.quizzes.QuizPageController;
import com.example.aiconceptsexplorer.leaderboardscreen.UserDAO;

/**
 * Main entry point for the AI Concepts Explorer application.
 * This class manages the primary stage and navigation between all major screens,
 * incl. signin, login, account, leaderboard, learn, glossary, search quiz & lesson,
 * lesson, home, quiz, and results views.
 */
public class MainApplication extends Application {

    private static Stage primaryStage;
    private static String currentUserEmail; // ✅ Store the logged-in user's email

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found!");
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Initialises the primary stage of the application.
     * Loads the login view as the initial screen and displays the main window.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        loadLoginView();
        primaryStage.show();
    }

    private void loadSignupView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/aiconceptsexplorer/signup-view.fxml"));
        Parent root = loader.load();

        signupController controller = loader.getController();
        controller.setNavigation(() -> {
            try {
                loadLoginView();  // Navigate to Login view after signup
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadLoginView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/aiconceptsexplorer/login-view.fxml"));
        Parent root = loader.load();

        loginController controller = loader.getController();
        controller.setNavigation((String userEmail) -> {
            currentUserEmail = userEmail;  // Capture email after login
            try {
                loadHomeView();  // Navigate to Learn view after login
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, () -> {
            try {
                loadSignupView(); // Navigate to Signup view
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadAccountView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/account/account-view.fxml"));
        Parent root = loader.load();

        AccountController controller = loader.getController();
        controller.setCurrentUserEmail(currentUserEmail); // ✅ Pass email to AccountController

        controller.setNavigation(
                () -> {
                    try {
                        loadLeaderboardView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLearnView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadHomeView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadLeaderboardView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/leaderboardscreen/leaderboard-view.fxml"));
        Parent root = loader.load();

        LeaderController controller = loader.getController();
        controller.setNavigation(
                () -> {
                    try {
                        loadAccountView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLearnView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadHomeView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadLearnView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnscreen/learn.fxml"));
        Parent root = loader.load();

        LearnController controller = loader.getController();
        controller.setNavigation(
                () -> {
                    try {
                        loadLeaderboardView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadAccountView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadGlossaryView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadHomeView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadSearchQuizView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadSearchLessonView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadGlossaryView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnscreen/ContinueGlossary.fxml"));
        Parent root = loader.load();

        GlossaryController controller = loader.getController();
        controller.setNavigation(
                () -> {
                    try {
                        loadAccountView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLeaderboardView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLearnView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadSearchQuizView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnscreen/SearchQuiz.fxml"));
        Parent root = loader.load();

        SearchQuizController controller = loader.getController();
        controller.setNavigation(
                () -> {
                    try {
                        loadAccountView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLeaderboardView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLearnView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadQuizzView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadSearchLessonView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnscreen/SearchLessons.fxml"));
        Parent root = loader.load();
        SearchLessonController controller = loader.getController();
        controller.setCurrentUserEmail(currentUserEmail); // ✅ Pass email to AccountController
        controller.setUserDAO(new UserDAO());
        controller.setNavigation(
                () -> {
                    try {
                        loadAccountView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLeaderboardView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLearnView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLessonView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadLessonView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/lessons/lessonscreen.fxml"));
        Parent root = loader.load();

        lessoncontroller controller = loader.getController();
        controller.setNavigation(
                () -> {
                    try {
                        loadLeaderboardView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadAccountView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLearnView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadHomeView(); // This method must exist in your class
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadHomeView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/home/HomeScreen.fxml"));
        Parent root = loader.load();

        // Use HomeScreenController instead of lessoncontroller
        HomeScreenController controller = loader.getController();
        controller.setNavigation(
                () -> {
                    try {
                        loadLeaderboardView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadAccountView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLoginView(); // This method must exist in your class
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadLearnView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        loadSearchLessonView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadQuizzView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizz/Quiz.fxml"));
        Parent root = loader.load();

        QuizPageController controller = loader.getController();
        controller.setCurrentUserEmail(currentUserEmail);
        controller.setUserDAO(new UserDAO());
        controller.setNavigation(
                () -> {
                    try { loadLeaderboardView(); }
                    catch (IOException e) { e.printStackTrace(); }
                },
                () -> {
                    try { loadAccountView(); }
                    catch (IOException e) { e.printStackTrace(); }
                },
                () -> {
                    try { loadLearnView(); }
                    catch (IOException e) { e.printStackTrace(); }
                },
                (resultsData) -> {
                    try {
                        loadResultsView(resultsData.score, resultsData.total, resultsData.incorrectResults);
                    }
                    catch (IOException e) { e.printStackTrace(); }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }


    private void loadResultsView(int score, int total, List<String> incorrectResults) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quizz/Results.fxml"));
        Parent root = loader.load();

        ResultsController controller = loader.getController();
        controller.initializeResults(score, total, incorrectResults);
        controller.setNavigation(() -> {
            try {
                loadLearnView();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        primaryStage.setScene(new Scene(root, 1200, 700));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
