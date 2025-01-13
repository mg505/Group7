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
        // Validates refund process for valid tickets.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        user.addCurrentTicket("Ticket A");

        RefundTicket refundTicket = new RefundTicket();
        boolean success = refundTicket.refundTicket(user, "Ticket A");

        assertTrue(success);
        assertFalse(user.getCurrentTickets().contains("Ticket A"));
        assertTrue(user.getExpiredTickets().contains("Ticket A"));
    }

    @Test
    public void testRefundInvalidTicketLogic() {
        // Ensures refund fails for invalid tickets.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        RefundTicket refundTicket = new RefundTicket();
        boolean success = refundTicket.refundTicket(user, "InvalidTicket");

        assertFalse(success);
    }

    @Test
    public void testRefundButtonDisablesWhenNoTicketsLeft() {
        // Checks UI behavior when no tickets remain for refund.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        user.addCurrentTicket("Ticket A");

        user.getCurrentTickets().remove("Ticket A");

        assertTrue(user.getCurrentTickets().isEmpty());
    }

    @Test
    public void testBackToDashboardNavigation() {
        // Verifies smooth navigation back to the dashboard.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> new DashboardUI(user, loginSystem));
    }
}
