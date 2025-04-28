package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    static {
        try {
            // Force-load the SQLite driver
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC driver not found!");
            e.printStackTrace();
            System.exit(1);
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize databases
        com.example.account.AccountDatabaseManager.initializeDatabase();
        com.example.leaderboardscreen.LeaderDatabaseConnection.connect();

        // Load account view
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/account/account-view.fxml"));
        Parent root = loader.load();

        // Set up navigation
        com.example.account.AccountController controller = loader.getController();
        controller.setNavigationToLeaderboard(() -> {
            try {
                showLeaderboardView(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        stage.setScene(new Scene(root, 1200, 700));
        stage.setTitle("Learning App");
        stage.show();
    }

    private void showLeaderboardView(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/leaderboardscreen/leaderboard-view.fxml"));
        Parent root = loader.load();

        com.example.leaderboardscreen.LeaderController controller = loader.getController();
        controller.setNavigationToAccount(() -> {
            try {
                start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        stage.setScene(new Scene(root, 1200, 700));
    }

    public static void main(String[] args) {
        launch(args);
    }
}