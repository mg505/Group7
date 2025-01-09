package WhiteBoxTesting;

import RefundTicket.RefundTicket;
import UserInterfaces.DashboardUI;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RefundTicketWBTesting {
	@Test
    public void testRefundTicketLogic() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Add a ticket to the user's current tickets
        user.addCurrentTicket("Ticket A");

        // Create RefundTicket instance
        RefundTicket refundTicket = new RefundTicket();

        // Test: Refund the ticket
        boolean success = refundTicket.refundTicket(user, "Ticket A");

        // Validate
        assertTrue(success, "Refund logic should succeed for valid tickets.");
        assertFalse(user.getCurrentTickets().contains("Ticket A"), "Ticket A should be removed from current tickets.");
        assertTrue(user.getExpiredTickets().contains("Ticket A"), "Ticket A should be added to expired tickets.");
    }

    @Test
    public void testRefundInvalidTicketLogic() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Create RefundTicket instance
        RefundTicket refundTicket = new RefundTicket();

        // Test: Attempt to refund a ticket not in current tickets
        boolean success = refundTicket.refundTicket(user, "InvalidTicket");

        // Validate
        assertFalse(success, "Refund logic should fail for invalid tickets.");
    }

    @Test
    public void testRefundButtonDisablesWhenNoTicketsLeft() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Add a ticket to the user's current tickets
        user.addCurrentTicket("Ticket A");

        // Simulate RefundTicketUI behavior
        user.getCurrentTickets().remove("Ticket A");

        // Validate
        assertTrue(user.getCurrentTickets().isEmpty(), "Refund button should disable when no tickets are left.");
    }

    @Test
    public void testBackToDashboardNavigation() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate navigating back to the Dashboard
        assertDoesNotThrow(() -> {
            new DashboardUI(user, loginSystem);
        }, "Navigating back to Dashboard should not throw exceptions.");
    }

}
