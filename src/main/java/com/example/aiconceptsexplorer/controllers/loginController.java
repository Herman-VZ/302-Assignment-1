package com.example.aiconceptsexplorer.controllers;

import com.example.aiconceptsexplorer.HelloApplication;
import com.example.aiconceptsexplorer.models.SqliteUserDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    private SqliteUserDAO newUserDAO;

    private Runnable navigateToLearn;

    @FXML
    public TextField email;

    @FXML
    public PasswordField password;

    @FXML
    private Button signupButton;

    @FXML
    public Label errorLabel;

    public loginController() {
        newUserDAO = new SqliteUserDAO();
    }

    public void setNavigation(Runnable toLearn) {
        this.navigateToLearn = toLearn;
    }


    @FXML
    public void onLogin() {
        String userEmail = email.getText();
        String userPassword = password.getText();
        boolean loginCheck = newUserDAO.loginUser(userEmail, userPassword);
        if (!loginCheck) {
            errorLabel.setText("Incorrect email or password, please try again");
        } else {
            if (navigateToLearn != null) {
                navigateToLearn.run();  // This will trigger the navigation to the learn view
            }
        }
    }


    @FXML
    private void onSignupRedirect() throws IOException {
        Stage stage = (Stage) signupButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }


}
