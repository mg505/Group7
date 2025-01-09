package WhiteBoxTesting;

import login.User;
import UserInterfaces.DashboardUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import login.LoginSystem;

public class DashboardWBTesting {

    @Test
    public void testCreateDashboardUILayout() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate creating the Dashboard UI
        DashboardUI dashboardUI = new DashboardUI(user, loginSystem);

        // Validate UI components
        assertNotNull(dashboardUI, "Dashboard UI should be initialized successfully.");
    }

    @Test
    public void testDownloadButtonLogic() {
        // Setup: Create a User with tickets
        User user = new User("testuser", "password123");
        user.addCurrentTicket("123, Route A, 10:00 AM, 50.00");  // Full ticket string as displayed in the UI

        // Simulate downloading a ticket
        assertDoesNotThrow(() -> {
            // Simulate the download ticket logic
            String ticketDetails = "123, Route A, 10:00 AM, 50.00";  // Full ticket string
            TicketDownloadCode.TicketDownloader.downloadTicket(null, ticketDetails);
        }, "Downloading a ticket should not throw exceptions.");
    }

    @Test
    public void testOpenViewTicketsUILogic() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate opening the View Tickets UI
        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openViewTicketsUI();
        }, "Opening View Tickets UI should not throw exceptions.");
    }

    @Test
    public void testOpenRefundTicketUILogic() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate opening the Refund Ticket UI
        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem).openRefundTicketUI();
        }, "Opening Refund Ticket UI should not throw exceptions.");
    }

    @Test
    public void testDisplayCurrentTickets() {
        // Setup: Create a User with tickets
        User user = new User("testuser", "password123");
        user.addCurrentTicket("Ticket A");

        // Validate current tickets display logic
        assertEquals(1, user.getCurrentTickets().size(), "Current tickets should be displayed correctly.");
        assertTrue(user.getCurrentTickets().contains("Ticket A"), "Ticket A should be in the current tickets list.");
    }

    
    @Test
    public void testDisplayExpiredTickets() {
        // Setup: Create a User with expired tickets
        User user = new User("testuser", "password123");
        user.addExpiredTicket("Ticket B");

        // Validate expired tickets display logic
        assertEquals(1, user.getExpiredTickets().size(), "Expired tickets should be displayed correctly.");
        assertTrue(user.getExpiredTickets().contains("Ticket B"), "Ticket B should be in the expired tickets list.");
    }
    
    @Test
    public void testPath_OpenCalendarUI() {
        User mockUser = new User("testUser", "password");
        LoginSystem loginSystem = new LoginSystem();
        DashboardUI dashboard = new DashboardUI(mockUser, loginSystem);
        assertDoesNotThrow(dashboard::openCalendarUI, "Path: openCalendarUI() executed.");
    }
}
