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
import java.util.function.Consumer;

public class loginController {

    private SqliteUserDAO newUserDAO;


    private Consumer<String> navigateToHome;

    private Runnable navigateToSignup;

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

    public void setNavigation(Consumer<String> toHome, Runnable toSignup) {
        this.navigateToSignup = toSignup;
        this.navigateToHome = toHome;
    }

    @FXML
    public void onLogin() {
        String userEmail = email.getText();
        String userPassword = password.getText();
        boolean loginCheck = newUserDAO.loginUser(userEmail, userPassword);
        if (!loginCheck) {
            errorLabel.setText("Incorrect email or password, please try again");
        } else {
            if (navigateToHome != null) {
                navigateToHome.accept(userEmail);  // Pass email to the next screen
            }
        }
    }

    @FXML
    void onSignupRedirect() throws IOException {
        if (navigateToSignup != null) {
            navigateToSignup.run();  // Navigate to Signup view
        }
    }
}
