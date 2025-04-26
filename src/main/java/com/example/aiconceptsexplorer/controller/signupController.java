package com.example.aiconceptsexplorer.controller;

import com.example.aiconceptsexplorer.HelloApplication;
import com.example.aiconceptsexplorer.model.SqliteUserDAO;
import com.example.aiconceptsexplorer.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class signupController {

    private SqliteUserDAO newUserDAO;
    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginButton;

    public signupController() {
        newUserDAO = new SqliteUserDAO();
    }

    @FXML
    private void onSignUp(){
        String userName = name.getText();
        String userEmail = email.getText();
        String userPassword = password.getText();
        User newUser = new User(userName, userEmail, userPassword);
        newUserDAO.addUser(newUser);
    }

    @FXML
    private void onLoginRedirect() throws IOException {
        Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }


}
