package com.example;

import com.example.aiconceptsexplorer.learnscreen.SearchQuizController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.aiconceptsexplorer.account.AccountController;
import com.example.aiconceptsexplorer.learnscreen.LessonController;
import com.example.aiconceptsexplorer.leaderboardscreen.LeaderController;
import com.example.aiconceptsexplorer.learnscreen.GlossaryController;
import com.example.aiconceptsexplorer.controllers.loginController;
import com.example.aiconceptsexplorer.controllers.signupController;

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
                loadLearnView();  // Navigate to Learn view after login
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
                        loadLoginView();
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
                        loadLoginView();
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

        LessonController controller = loader.getController();
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
                        loadLoginView();
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
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
