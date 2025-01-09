package RefundTicket;

import login.User;
import java.util.List;

public class RefundTicket {

    // Refund a ticket and update user's ticket list
    public boolean refundTicket(User user, String ticketId) {
        List<String> currentTickets = user.getCurrentTickets();

        if (currentTickets.contains(ticketId)) {
        	// Remove ticket from current tickets
            currentTickets.remove(ticketId);
            // Add ticket to expired tickets
            user.addExpiredTicket(ticketId);  
            System.out.println("Ticket ID " + ticketId + " has been refunded.");
            return true;
        } else {
            System.out.println("Ticket ID " + ticketId + " not found.");
            return false;  // Ticket not found
        }
    }
}
