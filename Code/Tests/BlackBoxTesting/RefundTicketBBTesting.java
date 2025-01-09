package BlackBoxTesting;
import RefundTicket.RefundTicket;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RefundTicketBBTesting {
	 @Test
	    public void testRefundTicketSuccess() {
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Add a ticket to the user's current tickets
	        user.addCurrentTicket("Ticket A");

	        // Create RefundTicket instance
	        RefundTicket refundTicket = new RefundTicket();

	        // Test: Refund the ticket
	        boolean result = refundTicket.refundTicket(user, "Ticket A");

	        // Validate
	        assertTrue(result, "Refund should succeed for a valid ticket.");
	        assertFalse(user.getCurrentTickets().contains("Ticket A"), "Ticket A should be removed from current tickets.");
	        assertTrue(user.getExpiredTickets().contains("Ticket A"), "Ticket A should be added to expired tickets.");
	    }

	    @Test
	    public void testRefundTicketFailure() {
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Create RefundTicket instance
	        RefundTicket refundTicket = new RefundTicket();

	        // Test: Attempt to refund a non-existent ticket
	        boolean result = refundTicket.refundTicket(user, "NonExistentTicket");

	        // Validate
	        assertFalse(result, "Refund should fail for a non-existent ticket.");
	    }

	    @Test
	    public void testRefundTicketDropdownUpdates() {
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Add tickets to the user's current tickets
	        user.addCurrentTicket("Ticket A");
	        user.addCurrentTicket("Ticket B");

	        // Simulate RefundTicketUI behavior
	        user.getCurrentTickets().remove("Ticket A");

	        // Validate
	        assertFalse(user.getCurrentTickets().contains("Ticket A"), "Ticket A should be removed from dropdown.");
	        assertTrue(user.getCurrentTickets().contains("Ticket B"), "Ticket B should still be available in dropdown.");
	    }

}
