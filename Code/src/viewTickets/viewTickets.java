package viewTickets;

import java.util.HashMap;
import java.util.Scanner;
import PurchaseBasket.Basket;

public class viewTickets {

    private HashMap<Integer, String[]> tickets;  // Stores ticket details (route, time, price)
    private HashMap<Integer, Double> ticketPrices; // Stores ticket prices
    private Basket basket;

    public viewTickets(Basket basket) {
        this.basket = basket;
        tickets = new HashMap<>();
        ticketPrices = new HashMap<>();
        populateTickets();
    }

    // Populate tickets with IDs, routes, times, and prices
    public void populateTickets() {
        tickets.put(1, new String[]{"Route A", "10:00 AM", "50.0"});
        tickets.put(2, new String[]{"Route B", "12:00 PM", "30.0"});
        tickets.put(3, new String[]{"Route C", "02:00 PM", "80.0"});

        ticketPrices.put(1, 50.0);  // Ticket 1 costs £50
        ticketPrices.put(2, 30.0);  // Ticket 2 costs £30
        ticketPrices.put(3, 80.0);  // Ticket 3 costs £80
    }

    // Show tickets to the user
    public String showTickets() {
        StringBuilder output = new StringBuilder();

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

    // Add ticket to the basket
    public void addToBasket(int ticketId) {
        if (tickets.containsKey(ticketId)) {
            String[] ticketDetails = tickets.get(ticketId);
            double price = ticketPrices.get(ticketId);  // Get price from ticketPrices
            basket.addTicket(ticketId, ticketDetails, price);  // Add ticket to basket with details
        } else {
            System.out.println("Invalid Ticket ID!");
        }
    }
}
