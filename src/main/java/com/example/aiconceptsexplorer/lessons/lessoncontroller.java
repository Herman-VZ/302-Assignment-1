package com.example.aiconceptsexplorer.lessons;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class lessoncontroller implements Initializable {

    @FXML
    private Button confirmButton;
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
        List<LessonData.LessonSection> lessonSections = LessonData.getLessonSections();
        for (LessonData.LessonSection section : lessonSections) {
            sections.add(createSection(section.getTitle(), section.getText(), section.getImage()));
        }
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

    // Navigation methods
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) navigateToLeaderboard.run();
    }

    @FXML
    public void onLogoutButtonClick(ActionEvent actionevent) {
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

    @FXML private void onPrevClick() { if (currentIndex > 0) showSection(currentIndex - 1); }
    @FXML private void onNextClick() { if (currentIndex < sections.size() - 1) showSection(currentIndex + 1); }

    private void showError(String message) {

    }

    public void onlogoutButtonClick(ActionEvent actionEvent) {
    }
}
