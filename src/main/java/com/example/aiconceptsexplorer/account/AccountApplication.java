package com.example.aiconceptsexplorer.account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The AccountApplication class is the entry point for the JavaFX application.
 * Initialises the SQLite DB, loads the main FXML view, and displays the primary stage.
 */
public class AccountApplication extends Application {
    /**
     * Starts thr JavaFX application.
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the SQLite database and sample users
        AccountDatabaseManager.initializeDatabase();
        FXMLLoader fxmlLoader = new FXMLLoader(AccountApplication.class.getResource("account-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main entry point for the application.
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}

// TODOs:
// - Make SQL achievement checks that allow 3 "users" to have dynamically altering inputted achievements
// - Link with "bum ass" login guy
// - Check with Dasha about home screen
// - Plan lesson screen and start writing it
// - Edit the popup to not be so ugly
// - Fix button names
// - Make medals circular, like League of Legends medallions