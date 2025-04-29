package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import com.example.account.AccountController;
import com.example.learnscreen.GlossaryController;
import com.example.learnscreen.LessonController;
import com.example.leaderboardscreen.LeaderController;

public class MainApplication extends Application {
    private static Stage primaryStage;

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
        loadAccountView();
        primaryStage.show();
    }

    private void loadAccountView() throws IOException {
        System.out.println("Loading Account View...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/account/account-view.fxml"));
        Parent root = loader.load();

        AccountController controller = loader.getController();
        controller.setNavigation(
                () -> {
                    try {
                        System.out.println("Navigating to Leaderboard from Account");
                        loadLeaderboardView();
                    } catch (IOException e) {
                        System.err.println("Account->Leaderboard navigation failed:");
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        System.out.println("Navigating to Learn from Account");
                        loadLearnView();
                    } catch (IOException e) {
                        System.err.println("Account->Learn navigation failed:");
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadLeaderboardView() throws IOException {
        System.out.println("Loading Leaderboard View...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/leaderboardscreen/leaderboard-view.fxml"));
        Parent root = loader.load();

        LeaderController controller = loader.getController();
        controller.setNavigation(
                () -> {
                    try {
                        System.out.println("Navigating to Account from Leaderboard");
                        loadAccountView();
                    } catch (IOException e) {
                        System.err.println("Leaderboard->Account navigation failed:");
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        System.out.println("Navigating to Learn from Leaderboard");
                        loadLearnView();
                    } catch (IOException e) {
                        System.err.println("Leaderboard->Learn navigation failed:");
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadLearnView() throws IOException {
        System.out.println("Loading Learn View...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnscreen/learn.fxml"));
        Parent root = loader.load();

        LessonController controller = loader.getController();
        controller.setNavigation(
                () -> {
                    try {
                        System.out.println("Navigating to Account from Learn");
                        loadAccountView();
                    } catch (IOException e) {
                        System.err.println("Learn->Account navigation failed:");
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        System.out.println("Navigating to Leaderboard from Learn");
                        loadLeaderboardView();
                    } catch (IOException e) {
                        System.err.println("Learn->Leaderboard navigation failed:");
                        e.printStackTrace();
                    }
                },
                () -> {
                    try {
                        System.out.println("Navigating to Glossary from Learn");
                        loadGlossaryView();
                    } catch (IOException e) {
                        System.err.println("Learn->Glossary navigation failed:");
                        e.printStackTrace();
                    }
                }
        );

        primaryStage.setScene(new Scene(root, 1200, 700));
    }

    private void loadGlossaryView() throws IOException {
        System.out.println("Loading Glossary View...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnscreen/glossary.fxml"));
        Parent root = loader.load();

        GlossaryController controller = loader.getController();
        System.out.println("Controller instance created: " + controller);

        // Create new lambdas with proper error handling
        Runnable toLeaderboard = () -> {
            try {
                System.out.println("Navigating to Leaderboard");
                loadLeaderboardView();
            } catch (IOException e) {
                System.err.println("Navigation to Leaderboard failed:");
                e.printStackTrace();
                Platform.runLater(() -> showError("Failed to load leaderboard view"));
            }
        };

        Runnable toAccount = () -> {
            try {
                System.out.println("Navigating to Account");
                loadAccountView();
            } catch (IOException e) {
                System.err.println("Navigation to Account failed:");
                e.printStackTrace();
                Platform.runLater(() -> showError("Failed to load account view"));
            }
        };

        Runnable toLearn = () -> {
            try {
                System.out.println("Navigating to Learn");
                loadLearnView();
            } catch (IOException e) {
                System.err.println("Navigation to Learn failed:");
                e.printStackTrace();
                Platform.runLater(() -> showError("Failed to load learn view"));
            }
        };

        // Set navigation with the new lambdas
        controller.setNavigation(toLeaderboard, toAccount, toLearn);

        // Verify they were set
        System.out.println("Navigation handlers set - Leaderboard: " + (toLeaderboard != null));
        System.out.println("Navigation handlers set - Account: " + (toAccount != null));
        System.out.println("Navigation handlers set - Learn: " + (toLearn != null));

        Scene scene = new Scene(root, 1200, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        System.out.println("Application starting...");
        launch(args);
    }
}