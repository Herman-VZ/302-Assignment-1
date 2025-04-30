import com.example.aiconceptsexplorer.models.SqliteUserDAO;
import com.example.aiconceptsexplorer.models.User;
import com.example.aiconceptsexplorer.controllers.signupController;
import com.example.aiconceptsexplorer.controllers.loginController;

public class SignUpLoginTest {

    private SqliteUserDAO mockUserDAO;
    private signupController signupCtrl;
    private loginController loginCtrl;

    // Simulate a setup method
    public void setUp() {
        // Mocking SqliteUserDAO
        mockUserDAO = new SqliteUserDAO();

        // Initialize controllers with mockUserDAO injected
        signupCtrl = new signupController(mockUserDAO);
        loginCtrl = new loginController(mockUserDAO);
    }

    // Test Sign-Up Process
    public void testSignUpAndLogin() {
        // Prepare mock user details for sign-up
        String userName = "Test User";
        String userEmail = "test@example.com";
        String userPassword = "password123";

        User newUser = new User(userName, userEmail, userPassword);

        // Simulate successful email validation
        boolean valid = mockUserDAO.validateEmail(newUser);
        if (valid) {
            mockUserDAO.addUser(newUser); // Simulate user addition
        }

        // Assert that the user has been added successfully
        assert mockUserDAO.validateEmail(newUser) : "User already exists!";

        // Now test Login:
        // Simulate login with correct credentials
        boolean loginSuccess = mockUserDAO.loginUser(userEmail, userPassword);
        assert loginSuccess : "Login failed with correct credentials!";

        // Simulate successful login
        if (loginSuccess) {
            // Here, we would check for the navigation, or test the login logic further
            System.out.println("Login successful, navigating to the Learn page...");
        }
    }

    // Test Sign-Up with Existing Email
    public void testSignUpWithExistingEmail() {
        // Set up mock user with an existing email
        String existingEmail = "existing@example.com";
        String userName = "New User";
        String userPassword = "password123";

        User newUser = new User(userName, existingEmail, userPassword);

        // Simulate that email already exists
        boolean valid = mockUserDAO.validateEmail(newUser);
        assert !valid : "Email already in use!";

        // Simulate error message for duplicate email
        if (!valid) {
            System.out.println("This email is already in use, please try again with another email.");
        }
    }

    // Main Method to Run the Test
    public static void main(String[] args) {
        SignUpLoginTest test = new SignUpLoginTest();

        // Set up environment
        test.setUp();

        // Run tests
        test.testSignUpAndLogin();
        test.testSignUpWithExistingEmail();
    }
}
