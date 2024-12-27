package BlackBoxTesting;


import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserAccountBBTesting {

    @Test
    public void testAddCurrentTicket() {
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Concert A - $50");

        List<String> currentTickets = user.getCurrentTickets();
        assertEquals(1, currentTickets.size(), "Current tickets should contain one ticket.");
        assertTrue(currentTickets.contains("Concert A - $50"), "Current tickets should include 'Concert A - $50'.");
    }

    @Test
    public void testAddExpiredTicket() {
        User user = new User("testuser", "password123");
        user.addExpiredTicket("Movie B - $30");

        List<String> expiredTickets = user.getExpiredTickets();
        assertEquals(1, expiredTickets.size(), "Expired tickets should contain one ticket.");
        assertTrue(expiredTickets.contains("Movie B - $30"), "Expired tickets should include 'Movie B - $30'.");
    }

    @Test
    public void testGetBasket() {
        User user = new User("testuser", "password123");
        assertNotNull(user.getBasket(), "User's basket should be initialized.");
    }
}
