package WhiteBoxTesting;

import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;
import UserInterfaces.DashboardUI;

import static org.junit.jupiter.api.Assertions.*;

public class TicketDownloaderWBTesting {

    @Test
    // Test navigation to the ViewTicketsUI from the DashboardUI
    public void testNavigationToViewTickets() {
        LoginSystem loginSystem = new LoginSystem();
        User testUser = loginSystem.registerUser("testuser", "password123");
        DashboardUI dashboardUI = new DashboardUI(testUser, loginSystem);
        assertDoesNotThrow(dashboardUI::openViewTicketsUI);
    }

    @Test
    // Test navigation to the RefundTicketUI from the DashboardUI
    public void testNavigationToRefundTickets() {
        LoginSystem loginSystem = new LoginSystem();
        User testUser = loginSystem.registerUser("testuser", "password123");
        DashboardUI dashboardUI = new DashboardUI(testUser, loginSystem);
        assertDoesNotThrow(dashboardUI::openRefundTicketUI);
    }

    @Test
    // Test navigation to the PurchaseBasketUI from the DashboardUI
    public void testNavigationToPurchaseBasket() {
        LoginSystem loginSystem = new LoginSystem();
        User testUser = loginSystem.registerUser("testuser", "password123");
        DashboardUI dashboardUI = new DashboardUI(testUser, loginSystem);
        assertDoesNotThrow(dashboardUI::openPurchaseBasketUI);
    }
}
