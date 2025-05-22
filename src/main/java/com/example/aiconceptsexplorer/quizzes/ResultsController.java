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

public class ResultsController {

    @FXML
    private Label scoreLabel;

    @FXML
    private ListView<String> resultsList;

    @FXML
    private Button explainButton;

    private Runnable navigateBackToLearn;

    private List<String> incorrectResults;

    public void initialize() {
        resultsList.setCellFactory(lv -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item);
                    setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
                }
            }
        });
    }

    public void initializeResults(int score, int total, List<String> incorrectResults) {
        scoreLabel.setText("Score: " + score + " out of " + total);
        this.incorrectResults = incorrectResults;
        resultsList.getItems().addAll(incorrectResults);
    }

    public void setNavigation(Runnable navigateBackToLearn) {
        this.navigateBackToLearn = navigateBackToLearn;
    }

    @FXML
    private void handleClose() {
        if (navigateBackToLearn != null) {
            navigateBackToLearn.run();
        }
    }

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

