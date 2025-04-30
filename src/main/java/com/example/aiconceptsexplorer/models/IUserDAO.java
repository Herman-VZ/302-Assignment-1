package com.example.aiconceptsexplorer.models;

/**
 * Interface for the User Data Access Object that handles
 * the CRUD operations for the User class with the database.
 */
public interface IUserDAO {
    /**
     * Adds a new user to the database.
     * @param user The user to add.
     */
    public void addUser(User user);
    /**
     * Deletes a user from the database.
     * @param user The user to delete.
     */
    public void deleteUser(User user);

    /**
     * Checks if a user account is in the database
     * @param email the email entered
     * @param password the password entered
     * @return true if the email and password match a user account, false otherwise
     */
    public boolean loginUser(String email, String password);

    /**
     * Checks if the inputted email is already used for another user
     * @param user the user that is being signed up
     * @return false if the email is a duplicate, true if it is valid
     */
    public boolean validateEmail(User user);
}