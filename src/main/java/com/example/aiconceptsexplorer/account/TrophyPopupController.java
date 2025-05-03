package com.example.aiconceptsexplorer.account;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class TrophyPopupController {

    private Button buttonToUpdate;  // This will hold the button reference passed from the main window

    // Method to set the button reference from the main window
    public void setButtonToUpdate(Button button) {
        this.buttonToUpdate = button;
    }

    // Action method to change the text of the button

    // The existing popup methods for buttons in the popup (these will remain unchanged)
    public void TrophyPopup1(ActionEvent actionEvent) {
        if (buttonToUpdate != null) {
            buttonToUpdate.setText("Trophy 1");  // Change the text of the button from the main window
        }
    }

    public void TrophyPopup2(ActionEvent actionEvent) {
        if (buttonToUpdate != null) {
            buttonToUpdate.setText("Trophy 2");  // Change the text of the button from the main window
        }
    }

    public void TrophyPopup3(ActionEvent actionEvent) {
        if (buttonToUpdate != null) {
            buttonToUpdate.setText("Trophy 3");  // Change the text of the button from the main window
        }
    }
}
