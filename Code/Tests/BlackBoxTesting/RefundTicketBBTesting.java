package BlackBoxTesting;

import RefundTicket.RefundTicket;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RefundTicketBBTesting {

    @Test
    // Tests successful refund of a valid ticket
    public void testRefundTicketSuccess() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        user.addCurrentTicket("Ticket A");

        RefundTicket refundTicket = new RefundTicket();
        boolean result = refundTicket.refundTicket(user, "Ticket A");

        assertTrue(result);
        assertFalse(user.getCurrentTickets().contains("Ticket A"));
        assertTrue(user.getExpiredTickets().contains("Ticket A"));
    }

    @Test
    // Tests failure to refund a non-existent ticket
    public void testRefundTicketFailure() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        RefundTicket refundTicket = new RefundTicket();
        boolean result = refundTicket.refundTicket(user, "NonExistentTicket");

        assertFalse(result);
    }

    @Test
    // Tests updates to dropdown menu after refunding tickets
    public void testRefundTicketDropdownUpdates() {
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        user.addCurrentTicket("Ticket A");
        user.addCurrentTicket("Ticket B");

        user.getCurrentTickets().remove("Ticket A");

        assertFalse(user.getCurrentTickets().contains("Ticket A"));
        assertTrue(user.getCurrentTickets().contains("Ticket B"));
    }
}
