package com.example.aiconceptsexplorer.learnscreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controller for the Glossary screen in the application.
 * Handles glossary search, selection and navigation between screens.
 */
public class GlossaryController {

    private Runnable navigateToAccount;
    private Runnable navigateToLeaderboard;
    private Runnable navigateToLearn;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> glossaryList;

    @FXML
    private Label definitionLabel;

    private final Map<String, String> glossaryData = new HashMap<>();
    private final ObservableList<String> glossaryItems = FXCollections.observableArrayList();


    /**
     * Initialises the controller, populates the glossary, and sets up listeners for search and selection.
     */
    @FXML
    public void initialize() {
        populateGlossary();
        glossaryItems.setAll(glossaryData.keySet());
        glossaryList.setItems(glossaryItems);

        glossaryList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                definitionLabel.setText(glossaryData.getOrDefault(newVal, "Definition not found."));
            }
        });

        searchField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText == null || newText.isEmpty()) {
                glossaryItems.setAll(glossaryData.keySet());
            } else {
                glossaryItems.setAll(
                        glossaryData.keySet()
                                .stream()
                                .filter(term -> term.toLowerCase().contains(newText.toLowerCase()))
                                .collect(Collectors.toList())
                );
            }
        });
    }

    private void populateGlossary() {
        glossaryData.put("Machine Learning", "A field of AI that enables systems to learn from data without being explicitly programmed.");
        glossaryData.put("Supervised Learning", "A type of ML where the model is trained on labeled data with known outputs.");
        glossaryData.put("Unsupervised Learning", "A type of ML where the model finds hidden patterns in data without labeled outputs.");
        glossaryData.put("Reinforcement Learning", "A learning method where an agent interacts with an environment and learns from feedback.");
        glossaryData.put("Model", "A mathematical representation built by an algorithm to make predictions or decisions.");
        glossaryData.put("Training Data", "Data used to teach a model during its training phase.");
        glossaryData.put("Testing Data", "Data used to evaluate how well a trained model performs on unseen examples.");
        glossaryData.put("Overfitting", "When a model learns noise and details in the training data, hurting its performance on new data.");
        glossaryData.put("Underfitting", "When a model is too simple to capture the patterns in the data, resulting in poor performance.");
        glossaryData.put("Neural Network", "A machine learning model inspired by the human brain, made of layers of interconnected nodes.");
        glossaryData.put("Deep Learning", "A subfield of ML using complex neural networks with many layers to model data.");
        glossaryData.put("Abstraction", "Hiding complex reality while exposing only the necessary parts.");
        glossaryData.put("Encapsulation", "Bundling data with methods that operate on that data.");
        glossaryData.put("Polymorphism", "One interface, many implementations.");
        glossaryData.put("Inheritance", "One class can inherit fields and methods from another.");
    }

    /**
     * Sets the navigation runnables for switching between screens.
     * @param toAccount runnable to navigate to the account screen
     * @param toLeaderboard runnable to navigate to the leaderboard screen
     * @param toLearn runnable to navigate to the learn screen
     */
    public void setNavigation(Runnable toAccount, Runnable toLeaderboard, Runnable toLearn) {
        this.navigateToAccount = toAccount;
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToLearn = toLearn;
    }

    /**
     * Handles the action when the Account tab is clicked.
     * Navigates to the account screen if navigation is set.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) {
            navigateToAccount.run();
        }
    }

    /**
     * Handles the action when the Leaderboard tab is clicked.
     * Navigates to the leaderboard screen if navigation is set.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) {
            navigateToLeaderboard.run();
        }
    }

    /**
     * Handles the action when the Learn tab is clicked.
     * Navigates to the learn screen if navigation is set.
     * @param actionEvent the action event triggered by the tab click
     */
    @FXML
    public void onLearnTabClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) {
            navigateToLearn.run();
        }
    }

}
