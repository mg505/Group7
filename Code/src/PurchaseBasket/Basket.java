package PurchaseBasket;

import java.util.HashMap;
import java.util.Map;

public class Basket {

    private HashMap<Integer, String[]> basketItems;  // Store ticket ID and its details
    private double totalCost;

    public Basket() {
        basketItems = new HashMap<>();
        totalCost = 0.0;
    }

    // Add ticket to the basket with details
    public void addTicket(int ticketId, String[] ticketDetails, double price) {
        basketItems.put(ticketId, ticketDetails);  // Add ticket details to the basket
        totalCost += price;  // Add price to the total cost
        System.out.println("Ticket ID " + ticketId + " added to basket.");
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

    // Calculate total cost of items in the basket
    public double calculateTotalCost() {
        return totalCost;
    }

    // Generate email body with ticket details
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
        return emailBody.toString();
    }

    // Clear the basket after checkout
    public void clearBasket() {
        basketItems.clear();
        totalCost = 0.0;
        System.out.println("Basket cleared.");
    }

    // Get all tickets in the basket
    public Map<Integer, String[]> getTickets() {
        return basketItems;
    }
}
