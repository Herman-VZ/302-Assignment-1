package com.example.aiconceptsexplorer.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock implementation of the {@link IContactDAO} interface for testinf purposes.
 * Uses an in-memory list to simulate a database.
 */
public class MockContactDAO implements IContactDAO {
    /**
     * A static list of contacts to be used as a mock database.
     * List is shared across all instances of {@link MockContactDAO}.
     */
    public static final ArrayList<Contact> contacts = new ArrayList<>();
    private static int autoIncrementedId = 0;

    /**
     * Constructs a new {@link MockContactDAO} instance and populates it with initial contacts.
     */
    public MockContactDAO() {
        // Add some initial contacts to the mock database
        addContact(new Contact("John", "Doe", "johndoe@example.com", "0423423423"));
        addContact(new Contact("Jane", "Doe", "janedoe@example.com", "0423423424"));
        addContact(new Contact("Jay", "Doe", "jaydoe@example.com", "0423423425"));
    }

    /**
     * Adds a new contact to the mock database.
     * @param contact The contact to add.
     */
    @Override
    public void addContact(Contact contact) {
        contact.setId(autoIncrementedId);
        autoIncrementedId++;
        contacts.add(contact);
    }

    /**
     * Updates an existing contact in the mock database.
     * Matches contacts by their id
     * @param contact The contact to update.
     */
    @Override
    public void updateContact(Contact contact) {
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getId() == contact.getId()) {
                contacts.set(i, contact);
                break;
            }
        }
    }

    /**
     * Deletes a contact from the mock database.
     * @param contact The contact to delete.
     */
    @Override
    public void deleteContact(Contact contact) {
        contacts.remove(contact);
    }

    /**
     * Retrieves a contact from the mock database by its id.
     * @param id The id of the contact to retrieve.
     * @return the contact with the given id, or null if not found.
     */
    @Override
    public Contact getContact(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    /**
     * Retrieves all contacts from the mock database.
     * @return A list of all contacts in the mock database.
     */
    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }
}