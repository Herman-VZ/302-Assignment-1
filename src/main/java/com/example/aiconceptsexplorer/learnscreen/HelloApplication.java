package com.example.aiconceptsexplorer.learnscreen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code HelloApplication} class serves as the main entry point for the JavaFX application.
 * It loads the main FXML layout and displays the primary stage.
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/learnscreen/learn.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Learn");
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