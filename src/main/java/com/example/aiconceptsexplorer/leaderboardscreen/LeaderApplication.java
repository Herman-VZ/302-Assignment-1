package com.example.aiconceptsexplorer.leaderboardscreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;

/**
 * The {@code LeaderApplication} class is the main entry point for the leaderboard screen
 * JavaFX application. It initialises the database connection, populates the database
 * with sample users if empty and loads the leaderboard view.
 *
 */
public class LeaderApplication extends Application {
    /**
     * Starts the JavaFX application by setting up the database, initialising sample data,
     * and displaying the leaderboard screen.
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        Connection connection = LeaderDatabaseConnection.connect();
        UserDAO userDAO = new UserDAO();

        // Initialize with sample data if empty
        if (userDAO.getLeaderboard().isEmpty()) {
            userDAO.addUser("John Doe", "johndoe@example.com", 1500);
            userDAO.addUser("Jane Smith", "janesmith@example.com", 2200);
            userDAO.addUser("Alice Johnson", "alicejohnson@example.com", 1800);
        }

        FXMLLoader fxmlLoader = new FXMLLoader(LeaderApplication.class.getResource("leaderboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Leaderboard");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main entry point for the application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}