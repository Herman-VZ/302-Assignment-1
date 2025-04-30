import com.example.aiconceptsexplorer.controllers.loginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginControllerTest {

    private static final String EMAIL = "john.doe@example.com";
    private static final String PASSWORD = "password123";
    private static final String WRONG_EMAIL = "wrong.email@example.com";
    private static final String WRONG_PASSWORD = "wrongpassword";

    private loginController loginCtrl;

    @BeforeEach
    public void setUp() {
        loginCtrl = new loginController();
    }

    @Test
    public void testGetEmail() {
        loginCtrl.email.setText(EMAIL);
        assertEquals(EMAIL, loginCtrl.email.getText());
    }

    @Test
    public void testSetEmail() {
        loginCtrl.email.setText(WRONG_EMAIL);
        assertEquals(WRONG_EMAIL, loginCtrl.email.getText());
    }

    @Test
    public void testGetPassword() {
        loginCtrl.password.setText(PASSWORD);
        assertEquals(PASSWORD, loginCtrl.password.getText());
    }

    @Test
    public void testSetPassword() {
        loginCtrl.password.setText(WRONG_PASSWORD);
        assertEquals(WRONG_PASSWORD, loginCtrl.password.getText());
    }

    @Test
    public void testLoginValidUser() {
        loginCtrl.email.setText(EMAIL);
        loginCtrl.password.setText(PASSWORD);
        loginCtrl.onLogin();

        // Verify that the login process is initiated (you can mock DAO and check methods are called)
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        loginCtrl.email.setText(WRONG_EMAIL);
        loginCtrl.password.setText(WRONG_PASSWORD);
        loginCtrl.onLogin();

        // Verify that the login fails when credentials are incorrect
    }
}
