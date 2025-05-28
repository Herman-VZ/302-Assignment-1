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

/**
 * Controller class for managing the lesson view.
 * Handles navigation between lesson sections and tab navigation.
 */
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

    /**
     * Sets the navigation actions for the controller.
     * @param toLeaderboard runnable action to navigate to the leaderboard view
     * @param toAccount runnable action to navigate to the account view
     * @param toLearn runnable action to navigate to the learn view
     * @param toHome runnable action to navigate to the home view
     */
    public void setNavigation(Runnable toLeaderboard, Runnable toAccount, Runnable toLearn, Runnable toHome) {
        this.navigateToLeaderboard = toLeaderboard;
        this.navigateToAccount = toAccount;
        this.navigateToHome = toHome;
        this.navigateToLearn = toLearn;
    }

    /**
     * Initislaises the controller after its root element has been completely processed.
     * @param location the location used to resolve relative paths for the root object, or null if unknown.
     * @param resources the resources used to localise the root object, or null if not localised.
     */
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

    /**
     * Handles the event when the leaderboard tab is clicked
     * @param actionEvent the action event
     */
    @FXML
    public void onLeaderboardTabClick(ActionEvent actionEvent) {
        if (navigateToLeaderboard != null) navigateToLeaderboard.run();
    }

    /**
     * Handles the event when the confirm button is clicked
     * @param actionevent the action event
     */
    @FXML
    public void onLogoutButtonClick(ActionEvent actionevent) {
        if (navigateToHome != null) navigateToHome.run();
    }

    /**
     * Handles the event when the account tab is clicked
     * @param actionEvent the action event
     */
    @FXML
    public void onAccountTabClick(ActionEvent actionEvent) {
        if (navigateToAccount != null) navigateToAccount.run();
    }

    /**
     * Handles the event when the first button is clicked (navigates to learn view)
     * @param actionEvent the action event
     */
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
