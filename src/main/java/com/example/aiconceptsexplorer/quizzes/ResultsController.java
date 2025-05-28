package com.example.aiconceptsexplorer.quizzes;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Controller for the results view in the AI Concepts Explorer quiz application.
 * Displays the users quiz score, incorrect answers, and provides AI generated explanations for mistakes.
 */
public class ResultsController {

    @FXML
    private Label scoreLabel;

    @FXML
    private ListView<String> resultsList;

    @FXML
    private Button explainButton;

    private Runnable navigateBackToLearn;

    private List<String> incorrectResults;

    /**
     * Initialises the ListView cell factory for displaying results with custom styling and wrapping.
     */
    public void initialize() {
        resultsList.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    // Create a Label or Text to wrap the text
                    Label label = new Label(item);
                    label.setWrapText(true); // Enable text wrapping
                    label.setMaxWidth(1000); // Set max width for wrapping (you can adjust this value)

                    // Apply styles to the label
                    label.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5px;");

                    // Set the label as the graphic of the ListCell
                    setGraphic(label);
                }
            }
        });
    }


    /**
     * Initialises the results view with the users score and list of incorrect answers.
     * @param score the user's score
     * @param total the total number of questions in the quiz
     * @param incorrectResults a list of incorrect results to display
     */
    public void initializeResults(int score, int total, List<String> incorrectResults) {
        scoreLabel.setText("Score: " + score + " out of " + total);
        this.incorrectResults = incorrectResults;
        resultsList.getItems().addAll(incorrectResults);
    }

    /**
     * Sets the navigation action to return to Learn view
     * @param navigateBackToLearn runnable to execute when navigating back.
     */
    public void setNavigation(Runnable navigateBackToLearn) {
        this.navigateBackToLearn = navigateBackToLearn;
    }

    /**
     * Handles the close action, navigating back to the Learn view if set.
     */
    @FXML
    private void handleClose() {
        if (navigateBackToLearn != null) {
            navigateBackToLearn.run();
        }
    }

    /**
     * Handles the explain button click, requesting AI generated explanations for incorrect answers.
     * Disables the button while explanations are being fetched
     */
    @FXML
    private void onExplainClick() {
        explainButton.setDisable(true);
        new Thread(() -> {
            try {
                for (int i = 0; i < incorrectResults.size(); i++) {
                    String prompt = "Explain why the following answer is incorrect, and provide a helpful explanation:\n" + incorrectResults.get(i);
                    String feedback = getAIExplanation(prompt);
                    final int index = i;
                    Platform.runLater(() -> {
                        resultsList.getItems().set(index, incorrectResults.get(index) + "\nAI Feedback: " + feedback);
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> explainButton.setDisable(false));
        }).start();
    }

    private String getAIExplanation(String prompt) throws IOException {
        URL url = new URL("http://localhost:11434/api/generate");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        String jsonInput = """
                {
                  "model": "mistral",
                  "prompt": "%s",
                  "stream": false
                }
                """.formatted(prompt.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n"));

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input);
        }

        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            try (BufferedReader errorReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
                StringBuilder errorResponse = new StringBuilder();
                String line;
                while ((line = errorReader.readLine()) != null) {
                    errorResponse.append(line.trim());
                }
                System.err.println("Ollama Error Response: " + errorResponse);
            }
            throw new IOException("Ollama returned error code: " + responseCode);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line.trim());
            }

            String json = response.toString();
            int contentIndex = json.indexOf("\"response\":\"");
            if (contentIndex != -1) {
                int start = contentIndex + 12;
                int end = json.indexOf("\"", start);
                if (end > start) {
                    return json.substring(start, end).replace("\\n", "\n").replace("\\\"", "\"");
                }
            }
            return "Unable to parse Ollama response.";
        }
    }
}

