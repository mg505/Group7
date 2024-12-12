package viewTickets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class viewTickets {
	
    private HashMap<Integer, String[]> tickets;
	private ArrayList<Integer> basket;

    public viewTickets(HashMap<Integer, String[]> tickets, ArrayList<Integer> basket) {
        this.tickets = tickets;
        this.basket = basket;
    }

    public String showTickets() {
    	StringBuilder output = new StringBuilder();

        // Iterate through the HashMap
        for (Entry<Integer, String[]> entry : tickets.entrySet()) {
            Integer ticketId = entry.getKey();          // Key (ticket ID)
            String[] details = entry.getValue();       // Value (ticket details array)

            // Append ticket details to output
            output.append("Ticket ID: ").append(ticketId).append("\n")
                  .append("Route: ").append(details[0]).append("\n")
                  .append("Time: ").append(details[1]).append("\n")
                  .append("Price: ").append(details[2]).append("\n")
                  .append("---------------------------------\n");
        }

        return output.toString();
    }
    
    public void addToBasket(int ticket_id) {
    	basket.add(ticket_id);
    	System.out.println("Ticket ID " + ticket_id + " added to basket.");
 
    }

	public void nav() {
		
		Scanner reader = new Scanner(System.in);
        int option;
        do {
            System.out.println("Enter 0 to go back to dashboard, or enter ticket id to add to basket:");
            option = reader.nextInt();
            if (option != 0) {
                if (tickets.containsKey(option)) {
                    addToBasket(option);
                } else {
                    System.out.println("Invalid Ticket ID! Please try again.");
                }
            }
        } while (option != 0);
       
    }
}
