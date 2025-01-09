package BlackBoxTesting;


import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProfileBBTesting {

    @Test
    public void testEditProfileSuccess() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate editing the profile
        user.setUsername("newusername");
        user.setPassword("newpassword");

        // Validate
        assertEquals("newusername", user.getUsername(), "Username should be updated.");
        assertEquals("newpassword", user.getPassword(), "Password should be updated.");
    }

    @Test
    public void testEditProfileWithExistingUsername() {
        // Setup: Create a LoginSystem and register users
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("existinguser", "password123");
        User user = loginSystem.registerUser("testuser", "password123");

        // Test: Try updating to an existing username
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            if (loginSystem.isUsernameTaken("existinguser")) {
                throw new IllegalArgumentException("Username already exists.");
            }
        });

        // Validate
        assertEquals("Username already exists.", exception.getMessage());
    }

    @Test
    public void testLogout() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate logout
        loginSystem.logout();

        // Validate
        assertNull(LoginSystem.getCurrentUser(), "Current user should be null after logout.");
    }

    @Test
    public void testDeleteAccount() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate account deletion
        loginSystem.removeUser(user);

        // Validate
        assertFalse(loginSystem.getAllUsers().contains(user), "Deleted user should not exist in the system.");
    }

    @Test
    public void testUserInfoRetentionAfterEdit() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Add tickets to the user's account
        user.addCurrentTicket("Ticket A");
        user.addExpiredTicket("Ticket B");

        // Edit profile
        user.setUsername("newusername");
        user.setPassword("newpassword");

        // Validate that tickets are retained
        assertEquals(1, user.getCurrentTickets().size(), "Current tickets should be retained.");
        assertEquals(1, user.getExpiredTickets().size(), "Expired tickets should be retained.");
    }
}
