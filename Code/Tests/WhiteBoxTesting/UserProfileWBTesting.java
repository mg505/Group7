package WhiteBoxTesting;

import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;
import UserInterfaces.DashboardUI;

import static org.junit.jupiter.api.Assertions.*;

public class UserProfileWBTesting {

    @Test
    // Test the logic for saving changes to the user's profile
    public void testHandleSaveChangesLogic() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        String newUsername = "newusername";
        String newPassword = "newpassword";
        user.setUsername(newUsername);
        user.setPassword(newPassword);
        assertEquals("newusername", user.getUsername());
        assertEquals("newpassword", user.getPassword());
    }

    @Test
    // Test the logic for logging out the current user
    public void testLogoutLogic() {
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("testuser", "password123");
        loginSystem.logout();
        assertNull(LoginSystem.getCurrentUser());
    }

    @Test
    // Test the logic for deleting a user account
    public void testDeleteAccountLogic() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        loginSystem.removeUser(user);
        assertFalse(loginSystem.getAllUsers().contains(user));
    }

    @Test
    // Test that user information is retained after editing the profile
    public void testRetainUserInfoAfterEdit() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        user.addCurrentTicket("Ticket A");
        user.addExpiredTicket("Ticket B");
        user.setUsername("newusername");
        user.setPassword("newpassword");
        assertTrue(user.getCurrentTickets().contains("Ticket A"));
        assertTrue(user.getExpiredTickets().contains("Ticket B"));
    }

    @Test
    // Test that the dashboard UI opens without errors
    public void testOpenDashboardUILogic() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        assertDoesNotThrow(() -> new DashboardUI(user, loginSystem));
    }
}
