package com.example.learnscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;

public class GlossaryController {
    private Runnable navigateToLeaderboard;
    private Runnable navigateToAccount;
    private Runnable navigateToLearn;

    @FXML
    private ListView<String> glossaryList;
    @FXML
    private TextField searchField;
    @FXML
    private VBox glossaryOptions;

    private Map<String, String> glossaryDefinitions = new HashMap<>();
    private ObservableList<String> glossaryWords = FXCollections.observableArrayList();

    public GlossaryController() {
        System.out.println("New GlossaryController instance created");
    }

    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toLearn) {
        System.out.println("Setting navigation handlers...");

        // Verify lambdas aren't null before assignment
        if (toLeaderboard == null) {
            System.err.println("WARNING: Leaderboard navigation lambda is null!");
        }
        if (toAccount == null) {
            System.err.println("WARNING: Account navigation lambda is null!");
        }
        if (toLearn == null) {
            System.err.println("WARNING: Learn navigation lambda is null!");
        }

        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToLearn = toLearn;

        // Verify assignment
        System.out.println("Current navigation handlers after set:");
        System.out.println("Leaderboard: " + (this.navigateToLeaderboard != null));
        System.out.println("Account: " + (this.navigateToAccount != null));
        System.out.println("Learn: " + (this.navigateToLearn != null));
    }


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

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterGlossary(newValue);
        });
    }

    private void filterGlossary(String searchText) {
        ObservableList<String> filteredWords = FXCollections.observableArrayList();
        for (String word : glossaryDefinitions.keySet()) {
            if (word.toLowerCase().contains(searchText.toLowerCase()) ||
                    glossaryDefinitions.get(word).toLowerCase().contains(searchText.toLowerCase())) {
                filteredWords.add(word);
            }
        }
        glossaryList.setItems(filteredWords);
    }

    @FXML
    private void onLeaderboardTabClick(ActionEvent actionEvent) {
        System.out.println("Leaderboard tab clicked - current handler: " +
                (navigateToLeaderboard != null ? navigateToLeaderboard.toString() : "NULL"));

        if (navigateToLeaderboard != null) {
            System.out.println("Executing leaderboard navigation");
            navigateToLeaderboard.run();
        } else {
            System.err.println("ERROR: Leaderboard navigation handler is null!");
            showError("Cannot navigate to leaderboard - system error");
        }
    }

    @FXML
    private void onAccountTabClick(ActionEvent actionEvent) {
        System.out.println("Account tab clicked - current handler: " +
                (navigateToAccount != null ? navigateToAccount.toString() : "NULL"));

        if (navigateToAccount != null) {
            System.out.println("Executing account navigation");
            navigateToAccount.run();
        } else {
            System.err.println("ERROR: Account navigation handler is null!");
            showError("Cannot navigate to account - system error");
        }
    }

    @FXML
    private void onLearnTabClick(ActionEvent actionEvent) {
        System.out.println("Learn tab clicked - current handler: " +
                (navigateToLearn != null ? navigateToLearn.toString() : "NULL"));

        if (navigateToLearn != null) {
            System.out.println("Executing learn navigation");
            navigateToLearn.run();
        } else {
            System.err.println("ERROR: Learn navigation handler is null!");
            showError("Cannot navigate to learn screen - system error");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Navigation Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}