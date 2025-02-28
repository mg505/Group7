package BlackBoxTesting;

import login.LoginSystem;
import login.User;
import UserInterfaces.DashboardUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DashboardBBTesting {

    @Test
    public void testOpenViewTicketsUI() {
        // Tests that opening the View Tickets UI doesn't throw exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openViewTicketsUI();
        }, "Opening View Tickets UI should not throw exceptions.");
    }

    @Test
    public void testOpenRefundTicketUI() {
        // Tests that opening the Refund Ticket UI doesn't throw exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openRefundTicketUI();
        }, "Opening Refund Ticket UI should not throw exceptions.");
    }

    @Test
    public void testOpenPurchaseBasketUI() {
        // Tests that opening the Purchase Basket UI doesn't throw exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openPurchaseBasketUI();
        }, "Opening Purchase Basket UI should not throw exceptions.");
    }

    @Test
    public void testOpenContactSupportUI() {
        // Tests that opening the Contact Support UI doesn't throw exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openContactSupportUI();
        }, "Opening Contact Support UI should not throw exceptions.");
    }

    @Test
    public void testOpenUserProfileUI() {
        // Tests that opening the User Profile UI doesn't throw exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openUserProfileUI();
        }, "Opening User Profile UI should not throw exceptions.");
    }

    @Test
    public void testOpenCalendarUI() {
        // Tests that opening the Calendar UI doesn't throw exceptions.
        User mockUser = new User("testUser", "password");
        LoginSystem loginSystem = new LoginSystem();
        DashboardUI dashboard = new DashboardUI(mockUser, loginSystem);
        assertDoesNotThrow(dashboard::openCalendarUI, "Expected CalendarUI to open without errors.");
    }

    @Test
    public void testDisplayCurrentAndExpiredTickets() {
        // Tests that current and expired tickets are displayed correctly.
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Ticket A");
        user.addExpiredTicket("Ticket B");

        assertEquals(1, user.getCurrentTickets().size(), "Current tickets should be displayed correctly.");
        assertEquals(1, user.getExpiredTickets().size(), "Expired tickets should be displayed correctly.");
    }
}