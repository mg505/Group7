package WhiteBoxTesting;

import login.User;
import UserInterfaces.DashboardUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DashboardWBTesting {

    @Test
    public void testInitializeDashboardUI() {
        // Create a real User object
        User testUser = new User("testuser", "password123");
        testUser.addCurrentTicket("Concert A - $50");
        testUser.addExpiredTicket("Movie B - $30");

        // Create the DashboardUI instance
        DashboardUI dashboard = new DashboardUI(testUser);

        // Verify internal state (ensure no exceptions during initialization)
        assertNotNull(dashboard, "DashboardUI should be initialized.");
    }

    @Test
    public void testOpenViewTicketsUIPath() {
        // Create a real User object
        User testUser = new User("testuser", "password123");

        // Create the DashboardUI instance
        DashboardUI dashboard = new DashboardUI(testUser);

        // Simulate opening the ViewTicketsUI
        assertDoesNotThrow(() -> dashboard.openViewTicketsUI(),
                "Opening ViewTicketsUI should follow the correct path.");
    }

    @Test
    public void testOpenPurchaseBasketUIPath() {
        // Create a real User object
        User testUser = new User("testuser", "password123");

        // Create the DashboardUI instance
        DashboardUI dashboard = new DashboardUI(testUser);

        // Simulate opening the PurchaseBasketUI
        assertDoesNotThrow(() -> dashboard.openPurchaseBasketUI(),
                "Opening PurchaseBasketUI should follow the correct path.");
    }
}
