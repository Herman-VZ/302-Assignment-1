package com.example.aiconceptsexplorer.lessons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class lessoncontroller implements Initializable {


    @FXML
    private Button confirmButton; // Ensure this matches the FX ID in your FXML

    // New fields for lesson pagination
    @FXML private ScrollPane lessonScroll;
    @FXML private VBox contentBox;
    @FXML private Button prevButton;
    @FXML private Button nextButton;

    private List<VBox> sections = new ArrayList<>();
    private int currentIndex = 0;

    private Runnable navigateToLeaderboard;
    private Runnable navigateToAccount;
    private Runnable navigateToHome;
    private Runnable navigateToLearn;

    // This method allows setting the navigation actions for the controller
    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toLearn, Runnable toHome) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToHome = toHome;
        this.navigateToLearn = toLearn;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buildSections();
        showSection(0);
    }

    private void buildSections() {
        sections.add(createSection(
                "What is Artificial Intelligence?",
                "Artificial Intelligence (AI) is the branch of computer science that aims to create machines that can perform tasks that typically require human intelligence. These tasks include things like learning, reasoning, problem-solving, perception, and language understanding.",
                null
        ));

        sections.add(createSection(
                "Everyday Examples of AI",
                "Virtual Assistants: Siri, Alexa, Google Assistant.\n" +
                        "\n" +
                        "Recommendation Systems: Netflix suggesting movies, Amazon recommending products.\n" +
                        "\n" +
                        "Smart Devices: Smart thermostats, voice-controlled home automation.",
                new Image(getClass().getResourceAsStream("/Images/Exit.png"))
        ));

        sections.add(createSection(
                "History of AI",
                "In the 1950s, Alan Turing proposed the \"Turing Test\" to measure a machine's ability to exhibit intelligent behavior equivalent to or indistinguishable from that of a human..",
        null
        ));

        sections.add(createSection(
                "Key Milestones",
                "1956: The term \"Artificial Intelligence\" was coined at the Dartmouth Conference.\n" +
                        "\n" +
                        "1997: IBM’s Deep Blue defeated chess champion Garry Kasparov.\n" +
                        "\n" +
                        "2011: IBM’s Watson won on Jeopardy!, showcasing AI’s ability to understand and answer natural language questions.",
                new Image(getClass().getResourceAsStream("/Images/Exit.png"))
        ));
    }

    private VBox createSection(String title, String text, Image image) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-size:16px; -fx-font-weight:bold;");

        Label textLabel = new Label(text);
        textLabel.setWrapText(true);
        textLabel.setStyle("-fx-font-size:14px;");

        VBox box = new VBox(10, titleLabel, textLabel);
        if (image != null) {
            ImageView iv = new ImageView(image);
            iv.setFitWidth(400);
            iv.setPreserveRatio(true);
            box.getChildren().add(iv);
        }
        return box;
    }

    private void showSection(int index) {
        currentIndex = index;
        contentBox.getChildren().setAll(sections.get(index));
        prevButton.setDisable(index == 0);
        nextButton.setDisable(index == sections.size() - 1);
        lessonScroll.setVvalue(0);
    }

    // Existing navigation methods
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) navigateToLeaderboard.run();
    }

    @FXML
    public void onlogoutButtonClick(ActionEvent actionevent) {
        if (navigateToHome != null) navigateToHome.run();
    }

    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) navigateToAccount.run();
    }

    @FXML
    public void onFirstButtonClick(ActionEvent actionEvent) {
        if (navigateToLearn != null) navigateToLearn.run();
    }

    // New pagination handlers
    @FXML private void onPrevClick() { if (currentIndex > 0) showSection(currentIndex - 1); }
    @FXML private void onNextClick() { if (currentIndex < sections.size() - 1) showSection(currentIndex + 1); }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void loadPage(String fxmlFile) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            Stage stage = (Stage) confirmButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load page: " + fxmlFile);
        }
    }
}