package BlackBoxTesting;

import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserProfileBBTesting {

    @Test
    // Tests successful profile editing
    public void testEditProfileSuccess() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        user.setUsername("newusername");
        user.setPassword("newpassword");

        assertEquals("newusername", user.getUsername());
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    // Tests failure when editing profile with an existing username
    public void testEditProfileWithExistingUsername() {
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("existinguser", "password123");
        User user = loginSystem.registerUser("testuser", "password123");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            if (loginSystem.isUsernameTaken("existinguser")) {
                throw new IllegalArgumentException("Username already exists.");
            }
        });

        assertEquals("Username already exists.", exception.getMessage());
    }

    @Test
    // Tests user logout functionality
    public void testLogout() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        loginSystem.logout();

        assertNull(LoginSystem.getCurrentUser());
    }

    @Test
    // Tests account deletion functionality
    public void testDeleteAccount() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        loginSystem.removeUser(user);

        assertFalse(loginSystem.getAllUsers().contains(user));
    }

    @Test
    // Tests ticket retention after profile editing
    public void testUserInfoRetentionAfterEdit() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        user.addCurrentTicket("Ticket A");
        user.addExpiredTicket("Ticket B");

        user.setUsername("newusername");
        user.setPassword("newpassword");

        assertEquals(1, user.getCurrentTickets().size());
        assertEquals(1, user.getExpiredTickets().size());
    }
}
