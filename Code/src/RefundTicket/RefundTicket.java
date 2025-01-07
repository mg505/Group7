// RefundTicket.java (Controller)
package RefundTicket;

import login.User;
import java.util.List;

public class RefundTicket {

    // Refund a ticket and update the user's current tickets and expired tickets
    public boolean refundTicket(User user, String ticketId) {
        List<String> currentTickets = user.getCurrentTickets();
       
        if (currentTickets.contains(ticketId)) {
            currentTickets.remove(ticketId);  // Remove ticket from current tickets
            user.addExpiredTicket(ticketId);  // Add ticket to expired tickets
            System.out.println("Ticket ID " + ticketId + " has been refunded.");
            return true;
        } else {
            System.out.println("Ticket ID " + ticketId + " is not found in current tickets.");
            return false;
        }
    }
}