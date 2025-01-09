package BlackBoxTesting;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountBBTesting {

    @Test
    public void testRegisterUserSuccess() {
        // Setup: Create a LoginSystem instance
        LoginSystem loginSystem = new LoginSystem();

        // Test: Register a new user
        User newUser = loginSystem.registerUser("testuser", "password123");

        // Validate
        assertNotNull(newUser, "New user should be created successfully.");
        assertEquals("testuser", newUser.getUsername(), "Username should match the input.");
    }

    @Test
    public void testRegisterUserWithExistingUsername() {
        // Setup: Create a LoginSystem instance and register a user
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("existinguser", "password123");

        // Test: Try registering with the same username
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loginSystem.registerUser("existinguser", "newpassword");
        });

        // Validate
        assertEquals("Username already exists.", exception.getMessage());
    }

    @Test
    public void testRegisterUserWithEmptyFields() {
        // Setup: Create a LoginSystem instance
        LoginSystem loginSystem = new LoginSystem();

        // Test: Try registering with empty username and password
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loginSystem.registerUser("", "");
        });

        // Validate
        assertEquals("Username and password cannot be empty.", exception.getMessage());
    }

    @Test
    public void testLoginSuccess() {
        // Setup: Create a LoginSystem instance and register a user
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("testuser", "password123");

        // Test: Login with valid credentials
        User loggedInUser = loginSystem.validateLogin("testuser", "password123");

        // Validate
        assertNotNull(loggedInUser, "User should log in successfully.");
        assertEquals("testuser", loggedInUser.getUsername(), "Logged-in username should match.");
    }

    @Test
    public void testLoginFailure() {
        // Setup: Create a LoginSystem instance
        LoginSystem loginSystem = new LoginSystem();

        // Test: Try logging in with invalid credentials
        User loggedInUser = loginSystem.validateLogin("nonexistentuser", "wrongpassword");

        // Validate
        assertNull(loggedInUser, "Login should fail for invalid credentials.");
    }

    @Test
    public void testDeactivateAndReactivateAccount() {
        // Setup: Create a User instance
        User user = new User("testuser", "password123");

        // Test: Deactivate and reactivate the account
        user.deactivateAccount();
        assertFalse(user.isActive(), "User account should be deactivated.");

        user.reactivateAccount();
        assertTrue(user.isActive(), "User account should be reactivated.");
    }
}
