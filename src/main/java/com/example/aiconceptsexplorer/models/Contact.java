package com.example.aiconceptsexplorer.models;

/**
 * Represents a contact with personal info such as name, email, and phone number.
 * Provides methods to access and modify contact details.
 */
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    /**
     * Constructs a new Constact with the specified details.
     * @param firstName the first name of the contact
     * @param lastName the last name of the contact
     * @param email the email address of the contact
     * @param phone the phone number of the contact
     */
    public Contact(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Returns the unique identifier of the contact.
     * @return the contact ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the contact.
     * @param id the contact ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the first name of the contact.
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the contact.
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the last name of the contact.
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the contact.
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns the email address of the contact.
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the contact.
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the phone number of the contact.
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the contact.
     * @param phone the phone number to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Returns the full name of the contact by combining first and last names.
     * @return the full name of the contact
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}