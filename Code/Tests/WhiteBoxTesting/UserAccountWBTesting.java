package WhiteBoxTesting;

import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountWBTesting {

    @Test
    // Test the logic for checking if a username is already taken
    public void testIsUsernameTakenLogic() {
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("testuser", "password123");
        assertTrue(loginSystem.isUsernameTaken("testuser"));
        assertFalse(loginSystem.isUsernameTaken("newuser"));
    }

    @Test
    // Test the logic for registering a new user
    public void testRegisterUserLogic() {
        LoginSystem loginSystem = new LoginSystem();
        User newUser = loginSystem.registerUser("testuser", "password123");
        assertEquals(2, loginSystem.getAllUsers().size());
        assertTrue(loginSystem.getAllUsers().contains(newUser));
    }

    @Test
    // Test the logic for validating login credentials
    public void testValidateLoginLogic() {
        LoginSystem loginSystem = new LoginSystem();
        loginSystem.registerUser("testuser", "password123");
        User loggedInUser = loginSystem.validateLogin("testuser", "password123");
        assertNotNull(loggedInUser);
        User invalidUser = loginSystem.validateLogin("testuser", "wrongpassword");
        assertNull(invalidUser);
    }

    @Test
    // Test the logic for expiring a ticket
    public void testExpireTicketLogic() {
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Ticket A");
        user.expireTicket("Ticket A");
        assertTrue(user.getExpiredTickets().contains("Ticket A"));
        assertFalse(user.getCurrentTickets().contains("Ticket A"));
    }

    @Test
    // Test the logic for removing tickets from the user's lists
    public void testRemoveTicketsLogic() {
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Ticket A");
        user.addExpiredTicket("Ticket B");
        user.removeCurrentTicket("Ticket A");
        user.removeExpiredTicket("Ticket B");
        assertFalse(user.getCurrentTickets().contains("Ticket A"));
        assertFalse(user.getExpiredTickets().contains("Ticket B"));
    }
}
