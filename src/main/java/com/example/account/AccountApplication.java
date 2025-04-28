package com.example.account;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AccountApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Initialize the SQLite database and sample users
        DatabaseManager.initializeDatabase();

        FXMLLoader fxmlLoader = new FXMLLoader(AccountApplication.class.getResource("account-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

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
// - Make medals circular, like League of Legends medalions
