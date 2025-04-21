package com.example.leaderboardscreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Connection connection = DatabaseConnection.connect();
        UserDAO userDAO = new UserDAO();

        // Initialize with sample data if empty
        if (userDAO.getLeaderboard().isEmpty()) {
            userDAO.addUser("John Doe", "johndoe@example.com", 1500);
            userDAO.addUser("Jane Smith", "janesmith@example.com", 2200);
            userDAO.addUser("Alice Johnson", "alicejohnson@example.com", 1800);
            userDAO.updateScore("John Doe", 1600);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Leaderboard");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}