package PurchaseBasket;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    private HashMap<Integer, String[]> basketItems;  
    private double totalCost; 

    public Basket() {
        basketItems = new HashMap<>();
        totalCost = 0.0;
    }

    // Add ticket to the basket with its details
    public void addTicket(int ticketId, String[] ticketDetails, double price) {
        basketItems.put(ticketId, ticketDetails);  
        totalCost += price;  
        System.out.println("Ticket ID " + ticketId + " added to basket.");
    }

    // Checkout and clear the basket
    public HashMap<Integer, String[]> checkoutBasket() {
        HashMap<Integer, String[]> purchasedTickets = new HashMap<>(basketItems);  
        clearBasket(); 
        return purchasedTickets;  
    }

    // Display all items in the basket
    public void displayBasket() {
        if (basketItems.isEmpty()) {
            System.out.println("Your basket is empty.");
        } else {
            System.out.println("Tickets in Basket:");
            for (Integer ticketId : basketItems.keySet()) {
                String[] details = basketItems.get(ticketId);
                System.out.println("Ticket ID: " + ticketId + " - Route: " + details[0] + " - Time: " + details[1] + " - Price: £" + details[2]);
            }
        }
    }

    // Get the total cost of tickets in the basket
    public double calculateTotalCost() {
        return totalCost;  
    }

    // Generate email body for the ticket details
    public String generateEmailBody(double totalCost) {
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Thank you for your purchase! Here are your ticket details:\n\n");

        for (Integer ticketId : basketItems.keySet()) {
            String[] details = basketItems.get(ticketId);
            emailBody.append("Ticket ID: ").append(ticketId)
                     .append(" - Route: ").append(details[0])
                     .append(" - Time: ").append(details[1])
                     .append(" - Price: £").append(details[2]).append("\n");
        }

        emailBody.append("\nTotal Cost: £").append(totalCost);
     // Return email body
        return emailBody.toString();  
    }

    // Clear the basket
    public void clearBasket() {
        basketItems.clear(); 
        // Reset total cost
        totalCost = 0.0;  
        System.out.println("Basket cleared.");
    }

    // Get all tickets in the basket
    public Map<Integer, String[]> getTickets() {
        return basketItems; 
    }
}
