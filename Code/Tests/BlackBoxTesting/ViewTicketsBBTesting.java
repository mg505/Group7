package BlackBoxTesting;


import login.LoginSystem;
import login.User;
import viewTickets.viewTickets;
import PurchaseBasket.Basket;
import UserInterfaces.ViewTicketsUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ViewTicketsBBTesting {

    @Test
    public void testViewTicketsInitialization() {
        // Setup: Create a User, Basket, and LoginSystem
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();

        // Verify ViewTicketsUI initializes without exceptions
        assertDoesNotThrow(() -> new ViewTicketsUI(testBasket, testUser, loginSystem),
                "ViewTicketsUI should initialize without exceptions.");
    }

    @Test
    public void testAddTicketToBasket() {
        // Setup: Create a User, Basket, and LoginSystem
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();

        // Add tickets to the user's ticket manager
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        // Create the ViewTicketsUI instance
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);

        // Simulate adding a ticket to the basket
        assertDoesNotThrow(() -> viewTicketsUI.addSelectedTicketToBasket(new viewTickets(testUser)),
                "Adding a ticket to the basket should not throw exceptions.");
    }

    @Test
    public void testNavigateToDashboard() {
        // Setup: Create a User, Basket, and LoginSystem
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();

        // Create the ViewTicketsUI instance
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);

        // Simulate navigation to the dashboard
        assertDoesNotThrow(viewTicketsUI::navigateToDashboard,
                "Navigating to the dashboard should not throw exceptions.");
    }
}
