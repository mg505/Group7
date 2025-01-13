package WhiteBoxTesting;

import login.User;
import UserInterfaces.DashboardUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import login.LoginSystem;

public class DashboardWBTesting {

    @Test
    public void testCreateDashboardUILayout() {
        // Tests dashboard UI initialization.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        DashboardUI dashboardUI = new DashboardUI(user, loginSystem);
        assertNotNull(dashboardUI, "Dashboard UI should be initialized successfully.");
    }

    @Test
    public void testDownloadButtonLogic() {
        // Validates ticket download logic.
        User user = new User("testuser", "password123");
        user.addCurrentTicket("123, Route A, 10:00 AM, 50.00");

        assertDoesNotThrow(() -> {
            String ticketDetails = "123, Route A, 10:00 AM, 50.00";
            TicketDownloadCode.TicketDownloader.downloadTicket(null, ticketDetails);
        }, "Downloading a ticket should not throw exceptions.");
    }

    @Test
    public void testOpenViewTicketsUILogic() {
        // Ensures View Tickets UI opens correctly.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openViewTicketsUI();
        }, "Opening View Tickets UI should not throw exceptions.");
    }

    @Test
    public void testOpenRefundTicketUILogic() {
        // Ensures Refund Ticket UI opens correctly.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openRefundTicketUI();
        }, "Opening Refund Ticket UI should not throw exceptions.");
    }

    @Test
    public void testDisplayCurrentTickets() {
        // Validates current tickets display.
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Ticket A");

        assertEquals(1, user.getCurrentTickets().size(), "Current tickets should be displayed correctly.");
        assertTrue(user.getCurrentTickets().contains("Ticket A"), "Ticket A should be in the current tickets list.");
    }

    @Test
    public void testDisplayExpiredTickets() {
        // Validates expired tickets display.
        User user = new User("testuser", "password123");
        user.addExpiredTicket("Ticket B");

        assertEquals(1, user.getExpiredTickets().size(), "Expired tickets should be displayed correctly.");
        assertTrue(user.getExpiredTickets().contains("Ticket B"), "Ticket B should be in the expired tickets list.");
    }

    @Test
    public void testPath_OpenCalendarUI() {
        // Ensures Calendar UI opens without errors.
        User mockUser = new User("testUser", "password");
        LoginSystem loginSystem = new LoginSystem();
        DashboardUI dashboard = new DashboardUI(mockUser, loginSystem);
        assertDoesNotThrow(dashboard::openCalendarUI, "Path: openCalendarUI() executed.");
    }
}