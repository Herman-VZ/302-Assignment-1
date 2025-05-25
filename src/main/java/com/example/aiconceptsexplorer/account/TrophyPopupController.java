package com.example.aiconceptsexplorer.account;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * Controller for the Trophy Popup window in account section.
 * This controller allows the popup to update when a trophy is selected.
 */
public class TrophyPopupController {

    private Button buttonToUpdate;  // This will hold the button reference passed from the main window

    /**
     * Method to set the button reference from the main window
     */
    public void setButtonToUpdate(Button button) {
        this.buttonToUpdate = button;
    }

    /**
     * Handles the action for Trophy 1 selection.
     * Updates the button text in the main window if button reference is set.
     * @param actionEvent
     */
    public void TrophyPopup1(ActionEvent actionEvent) {
        if (buttonToUpdate != null) {
            buttonToUpdate.setText("Trophy 1");  // Change the text of the button from the main window
        }
    }

    /**
     * Handles the action for Trophy 2 selection.
     * Updates the button text in the main window if button reference is set.
     * @param actionEvent
     */
    public void TrophyPopup2(ActionEvent actionEvent) {
        if (buttonToUpdate != null) {
            buttonToUpdate.setText("Trophy 2");  // Change the text of the button from the main window
        }
    }

    /**
     * Handles the action for Trophy 3 selection.
     * Updates the button text in the main window if button reference is set.
     * @param actionEvent
     */
    public void TrophyPopup3(ActionEvent actionEvent) {
        if (buttonToUpdate != null) {
            buttonToUpdate.setText("Trophy 3");  // Change the text of the button from the main window
        }
    }
}
