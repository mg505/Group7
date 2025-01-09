package WhiteBoxTesting;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountWBTesting {

    @Test
    public void testIsUsernameTakenLogic() {
        // Setup: Create a LoginSystem instance and register a user
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("testuser", "password123");

        // Test: Check if username is taken
        assertTrue(loginSystem.isUsernameTaken("testuser"), "Username should be marked as taken.");
        assertFalse(loginSystem.isUsernameTaken("newuser"), "Username should not be marked as taken.");
    }

    @Test
    public void testRegisterUserLogic() {
        // Setup: Create a LoginSystem instance
        LoginSystem loginSystem = new LoginSystem();

        // Test: Register a new user
        User newUser = loginSystem.registerUser("testuser", "password123");

        // Validate: Internal state of the LoginSystem
        assertEquals(2, loginSystem.getAllUsers().size(), "There should be two users (including admin).");
        assertTrue(loginSystem.getAllUsers().contains(newUser), "Newly registered user should be in the user list.");
    }

    @Test
    public void testValidateLoginLogic() {
        // Setup: Create a LoginSystem instance and register a user
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("testuser", "password123");

        // Test: Validate login with correct credentials
        User loggedInUser = loginSystem.validateLogin("testuser", "password123");
        assertNotNull(loggedInUser, "Login should succeed with correct credentials.");

        // Test: Validate login with incorrect credentials
        User invalidUser = loginSystem.validateLogin("testuser", "wrongpassword");
        assertNull(invalidUser, "Login should fail with incorrect credentials.");
    }

    @Test
    public void testExpireTicketLogic() {
        // Setup: Create a User instance and add tickets
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Ticket A");

        // Test: Expire a ticket
        user.expireTicket("Ticket A");

        // Validate: Internal state of tickets
        assertTrue(user.getExpiredTickets().contains("Ticket A"), "Ticket should be moved to expired list.");
        assertFalse(user.getCurrentTickets().contains("Ticket A"), "Ticket should be removed from current list.");
    }

    @Test
    public void testRemoveTicketsLogic() {
        // Setup: Create a User instance and add tickets
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Ticket A");
        user.addExpiredTicket("Ticket B");

        // Test: Remove tickets
        user.removeCurrentTicket("Ticket A");
        user.removeExpiredTicket("Ticket B");

        // Validate: Internal state of tickets
        assertFalse(user.getCurrentTickets().contains("Ticket A"), "Ticket A should be removed from current list.");
        assertFalse(user.getExpiredTickets().contains("Ticket B"), "Ticket B should be removed from expired list.");
    }
}
