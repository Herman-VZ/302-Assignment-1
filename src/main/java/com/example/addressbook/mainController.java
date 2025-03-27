package com.example.addressbook;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.List;

public class mainController {
    @FXML
    private ListView<Contact> contactsListView;
    private IContactDAO contactDAO;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;

    @FXML
    private VBox contactContainer;

    public mainController() {
        contactDAO = new MockContactDAO();
    }

    private ListCell<Contact> renderCell(ListView<Contact> contactListView) {
        ListCell<Contact> cell = new ListCell<>() {
            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);
                if (empty || contact == null || contact.getFullName() == null) {
                    setText(null);
                } else {
                    setText(contact.getFullName());
                }
            }
        };

        cell.setOnMouseClicked(event -> {
            if (cell.getItem() != null) {
                selectContact(cell.getItem());
            }
        });

        return cell;
    }

    private void selectContact(Contact contact) {
        if (contact != null) {
            contactsListView.getSelectionModel().select(contact);
            firstNameTextField.setText(contact.getFirstName());
            lastNameTextField.setText(contact.getLastName());
            emailTextField.setText(contact.getEmail());
            phoneTextField.setText(contact.getPhone());
        }
    }

    private void syncContacts() {
        contactsListView.getItems().clear();
        List<Contact> contacts = contactDAO.getAllContacts();
        boolean hasContact = !contacts.isEmpty();
        if (hasContact) {
            contactsListView.getItems().addAll(contacts);
        }
        // Show / hide based on whether there are contacts
        contactContainer.setVisible(hasContact);
    }

    @FXML
    public void initialize() {
        contactsListView.setCellFactory(this::renderCell);
        syncContacts();
        // Select the first contact if available
        if (!contactsListView.getItems().isEmpty()) {
            contactsListView.getSelectionModel().selectFirst();
            selectContact(contactsListView.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    private void onEditConfirm() {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            selectedContact.setFirstName(firstNameTextField.getText());
            selectedContact.setLastName(lastNameTextField.getText());
            selectedContact.setEmail(emailTextField.getText());
            selectedContact.setPhone(phoneTextField.getText());
            contactDAO.updateContact(selectedContact);
            syncContacts();
        }
    }

    @FXML
    private void onDelete() {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            contactDAO.deleteContact(selectedContact);
            syncContacts();
        }
    }

    @FXML
    private void onAdd() {
        final String DEFAULT_FIRST_NAME = "New";
        final String DEFAULT_LAST_NAME = "Contact";
        final String DEFAULT_EMAIL = "";
        final String DEFAULT_PHONE = "";
        Contact newContact = new Contact(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL, DEFAULT_PHONE);
        contactDAO.addContact(newContact);
        syncContacts();
        selectContact(newContact);
        firstNameTextField.requestFocus();
    }

    @FXML
    private void onCancel() {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            selectContact(selectedContact);
        }
    }


}
