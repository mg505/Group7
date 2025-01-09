package WhiteBoxTesting;

import login.LoginSystem;
import login.User;
import viewTickets.viewTickets;
import PurchaseBasket.Basket;
import UserInterfaces.ViewTicketsUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ViewTicketsWBTesting {

    @Test
    public void testFormatTicketsForDisplay() {
        // Setup: Create a User, Basket, and LoginSystem
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();

        // Add tickets to the basket
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);
        testBasket.addTicket(2, new String[]{"Route B", "2:00 PM", "30.0"}, 30.0);

        // Create the ViewTicketsUI instance
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);

        // Verify the formatted ticket string
        String formattedTickets = viewTicketsUI.formatTicketsForDisplay();
        assertTrue(formattedTickets.contains("Route A"), "Formatted tickets should include 'Route A'.");
        assertTrue(formattedTickets.contains("10:00 AM"), "Formatted tickets should include '10:00 AM'.");
    }

    @Test
    public void testAddSelectedTicketToBasketLogic() {
        // Setup: Create a User, Basket, and LoginSystem
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();

        // Add tickets to the basket
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        // Create the ViewTicketsUI instance
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);

        // Verify basket contents before and after adding a ticket
        assertEquals(1, testBasket.getTickets().size(), "Basket should initially contain one ticket.");
        viewTicketsUI.addSelectedTicketToBasket(new viewTickets(testUser));
        assertEquals(1, testBasket.getTickets().size(), "Adding a ticket should not duplicate existing tickets.");
    }

    @Test
    public void testNavigateToDashboardLogic() {
        // Setup: Create a User, Basket, and LoginSystem
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();

        // Create the ViewTicketsUI instance
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);

        // Verify navigation logic does not throw exceptions
        assertDoesNotThrow(viewTicketsUI::navigateToDashboard,
                "Navigating to the dashboard should not throw exceptions.");
    }
}
