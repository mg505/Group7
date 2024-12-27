package WhiteBoxTesting;

import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAccountWBTesting {

    @Test
    public void testAddCurrentTicketInternalLogic() {
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Concert A - $50");

        assertEquals(1, user.getCurrentTickets().size(), "Adding a ticket should increase currentTickets size.");
        assertEquals("Concert A - $50", user.getCurrentTickets().get(0), "The added ticket should match.");
    }

    @Test
    public void testAddExpiredTicketInternalLogic() {
        User user = new User("testuser", "password123");
        user.addExpiredTicket("Movie B - $30");

        assertEquals(1, user.getExpiredTickets().size(), "Adding a ticket should increase expiredTickets size.");
        assertEquals("Movie B - $30", user.getExpiredTickets().get(0), "The added ticket should match.");
    }

    @Test
    public void testBasketInitialization() {
        User user = new User("testuser", "password123");
        assertNotNull(user.getBasket(), "User's basket should be initialized.");
    }
}
