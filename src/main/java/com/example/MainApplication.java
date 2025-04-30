package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.account.AccountController;
import com.example.learnscreen.LessonController;
import com.example.leaderboardscreen.LeaderController;
import com.example.learnscreen.GlossaryController;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/account/account-view.fxml"));
        Parent root = loader.load();

        AccountController controller = loader.getController();
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
                        loadGlossaryView();  // Assuming this method exists for loading the glossary view
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


    public static void main(String[] args) {
        launch(args);
    }
}
