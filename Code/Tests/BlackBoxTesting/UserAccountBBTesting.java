package BlackBoxTesting;

import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountBBTesting {

    @Test
    // Tests successful registration of a new user
    public void testRegisterUserSuccess() {
        LoginSystem loginSystem = new LoginSystem();
        User newUser = loginSystem.registerUser("testuser", "password123");

        assertNotNull(newUser);
        assertEquals("testuser", newUser.getUsername());
    }

    @Test
    // Tests registration failure due to an existing username
    public void testRegisterUserWithExistingUsername() {
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("existinguser", "password123");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loginSystem.registerUser("existinguser", "newpassword");
        });

        assertEquals("Username already exists.", exception.getMessage());
    }

    @Test
    // Tests registration failure due to empty fields
    public void testRegisterUserWithEmptyFields() {
        LoginSystem loginSystem = new LoginSystem();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            loginSystem.registerUser("", "");
        });

        assertEquals("Username and password cannot be empty.", exception.getMessage());
    }

    @Test
    // Tests successful user login with valid credentials
    public void testLoginSuccess() {
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("testuser", "password123");

        User loggedInUser = loginSystem.validateLogin("testuser", "password123");

        assertNotNull(loggedInUser);
        assertEquals("testuser", loggedInUser.getUsername());
    }

    @Test
    // Tests failed user login with invalid credentials
    public void testLoginFailure() {
        LoginSystem loginSystem = new LoginSystem();
        User loggedInUser = loginSystem.validateLogin("nonexistentuser", "wrongpassword");

        assertNull(loggedInUser);
    }

    @Test
    // Tests deactivation and reactivation of a user account
    public void testDeactivateAndReactivateAccount() {
        User user = new User("testuser", "password123");

        user.deactivateAccount();
        assertFalse(user.isActive());

        user.reactivateAccount();
        assertTrue(user.isActive());
    }
}
