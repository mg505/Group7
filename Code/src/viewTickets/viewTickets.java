package viewTickets;

import java.util.HashMap;
import login.User;

public class viewTickets {

    
    private HashMap<Integer, String[]> tickets;
    private HashMap<Integer, Double> ticketPrices;
    private User user;

    // Constructor that initializes the viewTickets instance with a User object and populates the tickets
    public viewTickets(User user) {
        this.user = user;
        tickets = new HashMap<>();  
        ticketPrices = new HashMap<>();  
        populateTickets(); 
    }

    // Populates the tickets HashMap with ticket details
    public void populateTickets() {
        // Populating the tickets map with ticket data
        tickets.put(1, new String[]{"Route A", "10:00 AM", "50.0"});
        tickets.put(2, new String[]{"Route B", "12:00 PM", "30.0"});
        tickets.put(3, new String[]{"Route C", "02:00 PM", "80.0"});

        // Populating the ticketPrices map with the corresponding prices for each ticket ID
        ticketPrices.put(1, 50.0);  
        ticketPrices.put(2, 30.0);  
        ticketPrices.put(3, 80.0); 
    }

    // Shows all available tickets 
    public String showTickets() {
        StringBuilder output = new StringBuilder();  

        // Iterate over each ticket in the tickets map
        for (Integer ticketId : tickets.keySet()) {
            String[] details = tickets.get(ticketId);  
            output.append("Ticket ID: ").append(ticketId)  
                  .append("\nRoute: ").append(details[0])  
                  .append("\nTime: ").append(details[1])  
                  .append("\nPrice: £").append(details[2])
                  .append("\n---------------------------------\n");  
        }

        return output.toString(); 
    }

    // Adds a selected ticket to the user's basket based on the ticket ID
    public void addToBasket(int ticketId) {
        // Check if the ticket ID exists in the tickets map
        if (tickets.containsKey(ticketId)) {
            String[] ticketDetails = tickets.get(ticketId); 
            double price = ticketPrices.get(ticketId); 
            user.getBasket().addTicket(ticketId, ticketDetails, price);
        } else {
            // Print an error message if the ticket ID does not exist
            System.out.println("Invalid Ticket ID!");
        }
    }

    // Getter method to retrieve the tickets map 
    public HashMap<Integer, String[]> getTickets() {
        return tickets;
    }

    // Setter method to set the tickets map 
    public void setTickets(HashMap<Integer, String[]> tickets) {
        this.tickets = tickets;
    }
}
