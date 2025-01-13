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
    // Test the formatting of tickets for display in the ViewTicketsUI
    public void testFormatTicketsForDisplay() {
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);
        testBasket.addTicket(2, new String[]{"Route B", "2:00 PM", "30.0"}, 30.0);
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);
        String formattedTickets = viewTicketsUI.formatTicketsForDisplay();
        assertTrue(formattedTickets.contains("Route A"));
        assertTrue(formattedTickets.contains("10:00 AM"));
    }

    @Test
    // Test the logic for adding a selected ticket to the basket
    public void testAddSelectedTicketToBasketLogic() {
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);
        assertEquals(1, testBasket.getTickets().size());
        viewTicketsUI.addSelectedTicketToBasket(new viewTickets(testUser));
        assertEquals(1, testBasket.getTickets().size());
    }

    @Test
    // Test the navigation logic to the dashboard from ViewTicketsUI
    public void testNavigateToDashboardLogic() {
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);
        assertDoesNotThrow(viewTicketsUI::navigateToDashboard);
    }
}
