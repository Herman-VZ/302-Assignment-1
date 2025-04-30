package com.example.aiconceptsexplorer.controllers;

import com.example.aiconceptsexplorer.models.SqliteUserDAO;
import com.example.aiconceptsexplorer.models.User;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class SignupControllerTest {

    private SqliteUserDAO dao;

    @BeforeEach
    void setUp() {
        dao = new SqliteUserDAO();
    }

    @AfterEach
    void tearDown() {
        dao.deleteUserByEmail("testuser@example.com");
        dao.deleteUserByEmail("dupe@example.com");
        dao.deleteUserByEmail("loginuser@example.com");
    }

    @Test
    void testAddUserAndLogin() {
        String testEmail = "testuser@example.com";
        User newUser = new User("Test User", testEmail, "testpass");

        assertTrue(dao.validateEmail(newUser));
        dao.addUser(newUser);
        assertTrue(dao.loginUser(testEmail, "testpass"));
    }

    @Test
    void testDuplicateEmailIsRejected() {
        String testEmail = "dupe@example.com";
        User user1 = new User("User One", testEmail, "pass1");
        User user2 = new User("User Two", testEmail, "pass2");

        dao.addUser(user1);
        assertFalse(dao.validateEmail(user2));
    }

    // === NEW LOGIN TESTS ===

    @Test
    void testLoginWithCorrectCredentials() {
        String email = "loginuser@example.com";
        String password = "secure123";
        dao.addUser(new User("Login User", email, password));

        boolean loginSuccess = dao.loginUser(email, password);
        assertTrue(loginSuccess, "Login should succeed with correct credentials");
    }

    @Test
    void testLoginWithIncorrectPassword() {
        String email = "loginuser@example.com";
        dao.addUser(new User("Login User", email, "correctpass"));

        boolean loginSuccess = dao.loginUser(email, "wrongpass");
        assertFalse(loginSuccess, "Login should fail with incorrect password");
    }

    @Test
    void testLoginWithNonexistentUser() {
        boolean loginSuccess = dao.loginUser("ghost@example.com", "nopass");
        assertFalse(loginSuccess, "Login should fail for a non-existent user");
    }
}
