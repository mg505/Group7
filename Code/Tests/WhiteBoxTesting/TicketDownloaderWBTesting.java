package WhiteBoxTesting;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import UserInterfaces.DashboardUI;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TicketDownloaderWBTesting {
	@Test
    public void testNavigationToViewTickets() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User testUser = loginSystem.registerUser("testuser", "password123");

        // Create DashboardUI instance
        DashboardUI dashboardUI = new DashboardUI(testUser, loginSystem);

        // Verify navigation to ViewTicketsUI
        assertDoesNotThrow(dashboardUI::openViewTicketsUI, "Navigating to ViewTicketsUI should not throw exceptions.");
    }

    @Test
    public void testNavigationToRefundTickets() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User testUser = loginSystem.registerUser("testuser", "password123");

        // Create DashboardUI instance
        DashboardUI dashboardUI = new DashboardUI(testUser, loginSystem);

        // Verify navigation to RefundTicketUI
        assertDoesNotThrow(dashboardUI::openRefundTicketUI, "Navigating to RefundTicketUI should not throw exceptions.");
    }

    @Test
    public void testNavigationToPurchaseBasket() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User testUser = loginSystem.registerUser("testuser", "password123");

        // Create DashboardUI instance
        DashboardUI dashboardUI = new DashboardUI(testUser, loginSystem);

        // Verify navigation to PurchaseBasketUI
        assertDoesNotThrow(dashboardUI::openPurchaseBasketUI, "Navigating to PurchaseBasketUI should not throw exceptions.");
    }

}
