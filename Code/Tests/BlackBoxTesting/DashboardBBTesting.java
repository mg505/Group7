package BlackBoxTesting;
import login.LoginSystem;
import login.User;
import UserInterfaces.DashboardUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class DashboardBBTesting {

    @Test
    public void testOpenViewTicketsUI() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate opening the View Tickets UI
        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openViewTicketsUI();
        }, "Opening View Tickets UI should not throw exceptions.");
    }

    @Test
    public void testOpenRefundTicketUI() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate opening the Refund Ticket UI
        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openRefundTicketUI();
        }, "Opening Refund Ticket UI should not throw exceptions.");
    }

    @Test
    public void testOpenPurchaseBasketUI() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate opening the Purchase Basket UI
        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openPurchaseBasketUI();
        }, "Opening Purchase Basket UI should not throw exceptions.");
    }

    @Test
    public void testOpenContactSupportUI() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate opening the Contact Support UI
        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openContactSupportUI();
        }, "Opening Contact Support UI should not throw exceptions.");
    }

    @Test
    public void testOpenUserProfileUI() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate opening the User Profile UI
        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openUserProfileUI();
        }, "Opening User Profile UI should not throw exceptions.");
    }
    
    @Test
    public void testOpenCalendarUI() {
        User mockUser = new User("testUser", "password");
        LoginSystem loginSystem = new LoginSystem();
        DashboardUI dashboard = new DashboardUI(mockUser, loginSystem);
        assertDoesNotThrow(dashboard::openCalendarUI, "Expected CalendarUI to open without errors.");
    }

    @Test
    public void testDisplayCurrentAndExpiredTickets() {
        // Setup: Create a User with tickets
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Ticket A");
        user.addExpiredTicket("Ticket B");

        // Test: Check ticket display logic
        assertEquals(1, user.getCurrentTickets().size(), "Current tickets should be displayed correctly.");
        assertEquals(1, user.getExpiredTickets().size(), "Expired tickets should be displayed correctly.");
    }
    
}
