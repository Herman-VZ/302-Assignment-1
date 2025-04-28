package com.example.learnscreen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlossaryController {

    public void onFirstButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("learn.fxml"));
        Parent root = loader.load();

        // Get the current stage (window)
        Stage stage = (Stage) glossaryList.getScene().getWindow();

        // Set the new scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    @FXML
    private TextField searchField;


    private Map<String, String> glossaryDefinitions = new HashMap<>();

    private ObservableList<String> glossaryWords = FXCollections.observableArrayList();

    private static String lastSearchText = "";
    private static boolean continueFromLastSearch = false;

    @FXML
    public void initialize() {
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

        glossaryWords.addAll(glossaryDefinitions.keySet());
        glossaryList.setItems(glossaryWords);

        // Auto-select first item
        if (!glossaryList.getItems().isEmpty()) {
            glossaryList.getSelectionModel().selectFirst();
        }

        // Search functionality
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterGlossary(newValue);
        });
    }

    private void filterGlossary(String searchText) {
        ObservableList<String> filteredWords = FXCollections.observableArrayList();

        for (String word : glossaryDefinitions.keySet()) {
            String definition = glossaryDefinitions.get(word);
            if (word.toLowerCase().contains(searchText.toLowerCase()) ||
                    definition.toLowerCase().contains(searchText.toLowerCase())) {
                filteredWords.add(word);
            }
        }

        glossaryList.setItems(filteredWords);

        if (!filteredWords.isEmpty()) {
            glossaryList.getSelectionModel().selectFirst();
        }
    }
}