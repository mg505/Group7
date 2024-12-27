package BlackBoxTesting;


import login.User;
import viewTickets.viewTickets;
import PurchaseBasket.Basket;
import UserInterfaces.ViewTicketsUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ViewTicketsBBTesting {

    @Test
    public void testViewTicketsInitialization() {
        // Setup: Create a User and Basket with test data
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();

        // Add tickets to the user's ticket manager
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);
        testBasket.addTicket(2, new String[]{"Route B", "2:00 PM", "30.0"}, 30.0);

        // Create the ViewTicketsUI instance
        assertDoesNotThrow(() -> new ViewTicketsUI(testBasket, testUser),
                "ViewTicketsUI should initialize without exceptions.");
    }

    @Test
    public void testAddTicketToBasket() {
        // Setup: Create a User and Basket with test data
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();

        // Add tickets to the user's ticket manager
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);
        testBasket.addTicket(2, new String[]{"Route B", "2:00 PM", "30.0"}, 30.0);

        // Create the ViewTicketsUI instance
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser);

        // Simulate adding a ticket to the basket
        assertDoesNotThrow(() -> viewTicketsUI.addSelectedTicketToBasket(new viewTickets(testUser)),
                "Adding a ticket to the basket should not throw exceptions.");
    }

    @Test
    public void testNavigateToDashboard() {
        // Setup: Create a User and Basket
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();

        // Create the ViewTicketsUI instance
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser);

        // Simulate navigation to the dashboard
        assertDoesNotThrow(() -> viewTicketsUI.navigateToDashboard(),
                "Navigating to the dashboard should not throw exceptions.");
    }
}

