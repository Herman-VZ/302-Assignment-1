package com.example.aiconceptsexplorer.controllers;

import com.example.aiconceptsexplorer.learnscreen.LessonController;
import com.example.aiconceptsexplorer.models.SqliteUserDAO;
import com.example.aiconceptsexplorer.models.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

public class AuthControllerTest {

    private signupController signupCtrl;
    private loginController loginCtrl;
    private StubDao stubDao;

    // capture flags
    private String loginNavigatedTo;
    private boolean signupRedirected;

    /** Stub DAO so we don’t touch a real database */
    static class StubDao extends SqliteUserDAO {
        boolean loginSucceeds;
        boolean emailAvailable;
        List<User> addedUsers = new ArrayList<>();

        @Override
        public boolean loginUser(String email, String password) {
            return loginSucceeds;
        }

        @Override
        public boolean validateEmail(User u) {
            return emailAvailable;
        }

        @Override
        public void addUser(User u) {
            addedUsers.add(u);
        }
    }

    /** Initialize the JavaFX toolkit once for all tests */
    @BeforeAll
    static void initToolkit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    /** Set up controllers, stub DAO, and UI controls */
    @BeforeEach
    void setUp() throws Exception {
        signupCtrl = new signupController();
        loginCtrl = new loginController();

        signupCtrl.name = new TextField();
        signupCtrl.email = new TextField();
        signupCtrl.password = new PasswordField();
        signupCtrl.errorLabel = new Label();
        signupCtrl.goodLabel = new Label();

        loginCtrl.email = new TextField();
        loginCtrl.password = new PasswordField();
        loginCtrl.errorLabel = new Label();

        stubDao = new StubDao();
        injectDao(signupCtrl, stubDao);
        injectDao(loginCtrl, stubDao);

        signupRedirected = false;
        signupCtrl.setNavigation(() -> signupRedirected = true);

        loginNavigatedTo = null;
        loginCtrl.setNavigation(
                email -> loginNavigatedTo = email,
                () -> signupRedirected = true
        );
    }

    /** Reflection helper for private field injection */
    private static void injectDao(Object ctrl, StubDao dao) throws Exception {
        Field f = ctrl.getClass().getDeclaredField("newUserDAO");
        f.setAccessible(true);
        f.set(ctrl, dao);
    }

    // --- signupController tests ---

    @Test
    void signup_invalidEmail_showsEmailError() {
        signupCtrl.name.setText("Alice");
        signupCtrl.email.setText("alice_at_example.com");
        signupCtrl.password.setText("Pass#1");

        signupCtrl.onSignUp();

        assertEquals("Invalid email. Must contain '@' and end with '.com'.", signupCtrl.errorLabel.getText());
        assertEquals("", signupCtrl.goodLabel.getText());
    }

    @Test
    void signup_invalidPassword_showsPasswordError() {
        signupCtrl.name.setText("Bob");
        signupCtrl.email.setText("bob@example.com");
        signupCtrl.password.setText("short");

        signupCtrl.onSignUp();

        assertEquals("Password must be 6+ chars, include a number & special character.", signupCtrl.errorLabel.getText());
        assertEquals("", signupCtrl.goodLabel.getText());
    }

    @Test
    void signup_emailTaken_showsTakenError() {
        stubDao.emailAvailable = false;

        signupCtrl.name.setText("Carol");
        signupCtrl.email.setText("carol@example.com");
        signupCtrl.password.setText("Good#123");

        signupCtrl.onSignUp();

        assertEquals("This email is already in use, please try another.", signupCtrl.errorLabel.getText());
        assertTrue(stubDao.addedUsers.isEmpty());
    }

    @Test
    void signup_success_addsUserAndShowsSuccess() {
        stubDao.emailAvailable = true;

        signupCtrl.name.setText("Dave");
        signupCtrl.email.setText("dave@example.com");
        signupCtrl.password.setText("Strong!9");

        signupCtrl.onSignUp();

        assertEquals("Signup successful.", signupCtrl.goodLabel.getText());
        assertEquals("", signupCtrl.errorLabel.getText());

        assertEquals(1, stubDao.addedUsers.size());
        User u = stubDao.addedUsers.get(0);
        assertEquals("Dave", u.getName());
        assertEquals("dave@example.com", u.getEmail());
        assertEquals("Strong!9", u.getPassword());
    }

    @Test
    void signup_onLoginRedirect_setsFlag() {
        signupRedirected = false;
        signupCtrl.onLoginRedirect(new ActionEvent());
        assertTrue(signupRedirected);
    }

    // --- loginController tests ---

    @Test
    void login_validCredentials_navigatesToLearn() {
        stubDao.loginSucceeds = true;

        loginCtrl.email.setText("john.doe@example.com");
        loginCtrl.password.setText("Password!1");
        loginCtrl.onLogin();

        assertEquals("john.doe@example.com", loginNavigatedTo);
        assertEquals("", loginCtrl.errorLabel.getText());
    }

    @Test
    void login_invalidCredentials_showsError() {
        stubDao.loginSucceeds = false;

        loginCtrl.email.setText("wrong@example.com");
        loginCtrl.password.setText("badpass");
        loginCtrl.onLogin();

        assertNull(loginNavigatedTo);
        assertEquals("Incorrect email or password, please try again", loginCtrl.errorLabel.getText());
    }

    @Test
    void login_onSignupRedirect_setsFlag() throws IOException {
        signupRedirected = false;
        loginCtrl.onSignupRedirect();
        assertTrue(signupRedirected);
    }

    // --- lessonController tests ---

    private LessonController lessonCtrl;
    private boolean leaderboardRedirected;
    private boolean accountRedirected;
    private boolean learnScreenShown;
    private boolean logoutRedirected;
    private boolean dummy1;
    private boolean dummy2;

    @BeforeEach
    void setupLessonController() {
        lessonCtrl = new LessonController();

        leaderboardRedirected = false;
        accountRedirected = false;
        learnScreenShown = false;
        logoutRedirected = false;
        dummy1 = false;
        dummy2 = false;

        lessonCtrl.setNavigation(
                () -> leaderboardRedirected = true,
                () -> accountRedirected = true,
                () -> learnScreenShown = true,
                () -> logoutRedirected = true,
                () -> dummy1 = true,
                () -> dummy2 = true
        );
    }

    @Test
    void lesson_clicksLeaderboard_triggersLeaderboardNavigation() {
        lessonCtrl.onLeaderboardTabClick(new ActionEvent());
        assertTrue(leaderboardRedirected);
    }

    @Test
    void lesson_clicksAccount_triggersAccountNavigation() {
        lessonCtrl.onAccountTabClick(new ActionEvent());
        assertTrue(accountRedirected);
    }

    @Test
    void logout_triggersLogoutNavigation() {
        lessonCtrl.onlogoutButtonClick(new ActionEvent());
        assertTrue(logoutRedirected);
    }
}
