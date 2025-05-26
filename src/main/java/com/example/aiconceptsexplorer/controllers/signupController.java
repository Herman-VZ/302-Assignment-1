package com.example.aiconceptsexplorer.controllers;

import com.example.aiconceptsexplorer.HelloApplication;
import com.example.aiconceptsexplorer.models.SqliteUserDAO;
import com.example.aiconceptsexplorer.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the signup screen.
 * Handles new user registration.
 */
public class signupController {

    public Label goodLabel;

    private SqliteUserDAO newUserDAO;

    private Runnable navigateToLogIn;

    /**
     * Sets the navigation action for redirecting to the login screen.
     * @param toLogIn a Runnable representing the navigation logic to the login screen.
     */
    public void setNavigation(Runnable toLogIn) {
        this.navigateToLogIn = toLogIn;
    }

    @FXML
    public TextField name;

    @FXML
    public TextField email;

    @FXML
    public PasswordField password;

    @FXML
    private Button loginButton;

    @FXML
    public Label errorLabel;

    /**
     * Sets up the controller to interact with the user database for signup operations
     */
    public signupController() {
        newUserDAO = new SqliteUserDAO();
    }

    /**
     * Handles signup process when user submits the form.
     * Validates the email and password, checks email for uniqueness,
     * and registers the user if all checks pass.
     * Displays appropriate success or error messages.
     */
    @FXML
    public void onSignUp() {
        errorLabel.setText(""); // Clear previous errors

        String userName = name.getText();
        String userEmail = email.getText();
        String userPassword = password.getText();

        if (!isValidEmail(userEmail)) {
            errorLabel.setText("Invalid email. Must contain '@' and end with '.com'.");
            return;
        }

        if (!isValidPassword(userPassword)) {
            errorLabel.setText("Password must be 6+ chars, include a number & special character.");
            return;
        }

        User newUser = new User(userName, userEmail, userPassword);
        boolean valid = newUserDAO.validateEmail(newUser);

        if (valid) {
            newUserDAO.addUser(newUser);
            goodLabel.setText("Signup successful.");
        } else {
            errorLabel.setText("This email is already in use, please try another.");
        }
    }

    /**
     * Handles the action event for redirecting to the login screen.
     * @param actionEvent event triggered by the login button.
     */
    @FXML
    public void onLoginRedirect(ActionEvent actionEvent) {
        if (navigateToLogIn != null) {
            navigateToLogIn.run();  // Navigate back to Login view
        }
    }

    private boolean isValidEmail(String email) {
        return email.contains("@") && email.toLowerCase().endsWith(".com");
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 6) return false;
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
        return hasNumber && hasSpecial;
    }
}
