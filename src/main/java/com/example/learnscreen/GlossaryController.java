package com.example.learnscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.HashMap;
import java.util.Map;

public class GlossaryController {
    public void onFirstButtonClick(ActionEvent actionEvent) {

    }

    public void onSecondButtonClick(ActionEvent actionEvent) {
        // Add your functionality here
    }

    public void onThirdButtonClick(ActionEvent actionEvent) {
        // Add your functionality here
    }

    @FXML
    private ListView<String> glossaryList;

    @FXML
    private Label definitionLabel;

    // A map of words to their definitions
    private Map<String, String> glossaryDefinitions = new HashMap<>();

    @FXML
    public void initialize() {
        // Add glossary terms and definitions
        glossaryDefinitions.put("Machine Learning", "A field of AI that enables systems to learn from data without being explicitly programmed.");
        glossaryDefinitions.put("Supervised Learning", "A type of ML where the model is trained on labeled data with known outputs.");
        glossaryDefinitions.put("Unsupervised Learning", "A type of ML where the model finds hidden patterns in data without labeled outputs.");
        glossaryDefinitions.put("Reinforcement Learning", "A learning method where an agent interacts with an environment and learns from feedback.");
        glossaryDefinitions.put("Model", "A mathematical representation built by an algorithm to make predictions or decisions.");
        glossaryDefinitions.put("Training Data", "Data used to teach a model during its training phase.");
        glossaryDefinitions.put("Testing Data", "Data used to evaluate how well a trained model performs on unseen examples.");
        glossaryDefinitions.put("Overfitting", "When a model learns noise and details in the training data, hurting its performance on new data.");
        glossaryDefinitions.put("Underfitting", "When a model is too simple to capture the patterns in the data, resulting in poor performance.");
        glossaryDefinitions.put("Neural Network", "A machine learning model inspired by the human brain, made of layers of interconnected nodes.");
        glossaryDefinitions.put("Deep Learning", "A subfield of ML using complex neural networks with many layers to model data.");
        glossaryDefinitions.put("Abstraction", "Hiding complex reality while exposing only the necessary parts.");
        glossaryDefinitions.put("Encapsulation", "Bundling data with methods that operate on that data.");
        glossaryDefinitions.put("Polymorphism", "One interface, many implementations.");
        glossaryDefinitions.put("Inheritance", "One class can inherit fields and methods from another.");

        glossaryList.getItems().addAll(glossaryDefinitions.keySet());

        // When a word is clicked, show its definition
        glossaryList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                definitionLabel.setText(glossaryDefinitions.get(newValue));
            }
        });
    }

    private void showDefinitionPopup(String term, String definition) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Definition");
        alert.setHeaderText(term);
        alert.setContentText(definition);
        alert.showAndWait();
    }
}