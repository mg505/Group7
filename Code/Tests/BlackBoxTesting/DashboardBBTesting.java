package BlackBoxTesting;
import login.User;
import UserInterfaces.DashboardUI;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DashboardBBTesting {

    @Test
    public void testCreateDashboardUI() {
        // Create a real User object
        User testUser = new User("testuser", "password123");
        testUser.addCurrentTicket("Concert A - $50");
        testUser.addExpiredTicket("Movie B - $30");

        // Create the DashboardUI instance
        DashboardUI dashboard = new DashboardUI(testUser);

        // Verify the UI initializes without exceptions
        assertNotNull(dashboard, "DashboardUI should initialize successfully.");
    }

    @Test
    public void testOpenViewTicketsUI() {
        // Create a real User object
        User testUser = new User("testuser", "password123");

        // Create the DashboardUI instance
        DashboardUI dashboard = new DashboardUI(testUser);

        // Simulate opening the ViewTicketsUI
        assertDoesNotThrow(() -> dashboard.openViewTicketsUI(),
                "Opening ViewTicketsUI should not throw exceptions.");
    }

    @Test
    public void testOpenPurchaseBasketUI() {
        // Create a real User object
        User testUser = new User("testuser", "password123");

        // Create the DashboardUI instance
        DashboardUI dashboard = new DashboardUI(testUser);

        // Simulate opening the PurchaseBasketUI
        assertDoesNotThrow(() -> dashboard.openPurchaseBasketUI(),
                "Opening PurchaseBasketUI should not throw exceptions.");
    }
}