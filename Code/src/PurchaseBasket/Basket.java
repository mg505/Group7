package PurchaseBasket;

import java.util.HashMap;
import java.util.Scanner;

public class Basket {
    private HashMap<Integer, String[]> tickets; // Stores ticket details
    private HashMap<Integer, Integer> basket;  // Stores ticket ID and quantity

    public Basket(HashMap<Integer, String[]> tickets, HashMap<Integer, Integer> basket) {
        this.tickets = tickets;
        this.basket = basket;
    }

    public void openBasket() {
        Scanner reader = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n------------- BASKET -------------");
            viewBasket();
            System.out.println("1. Increase quantity");
            System.out.println("2. Decrease quantity");
            System.out.println("3. Remove a ticket");
            System.out.println("4. View total cost");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = reader.nextInt();

            switch (choice) {
                case 1:
                    adjustQuantity(reader, true); // Increase quantity
                    break;
                case 2:
                    adjustQuantity(reader, false); // Decrease quantity
                    break;
                case 3:
                    removeTicket(reader);
                    break;
                case 4:
                    calculateTotal();
                    break;
                case 5:
                    System.out.println("Exiting Basket...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    private void viewBasket() {
        if (basket.isEmpty()) {
            System.out.println("Basket is empty!");
        } else {
            System.out.println("Your Basket:");
            for (Integer ticketId : basket.keySet()) {
                String[] ticket = tickets.get(ticketId);
                int quantity = basket.get(ticketId);
                System.out.println("Ticket ID: " + ticketId);
                System.out.println("Route: " + ticket[0]);
                System.out.println("Time: " + ticket[1]);
                System.out.println("Price: £" + ticket[2]);
                System.out.println("Quantity: " + quantity);
                System.out.println("---------------------------------");
            }
        }
    }

    private void adjustQuantity(Scanner reader, boolean increase) {
        System.out.print("Enter Ticket ID to " + (increase ? "increase" : "decrease") + " quantity: ");
        int ticketId = reader.nextInt();
        if (basket.containsKey(ticketId)) {
            int quantity = basket.get(ticketId);
            quantity = increase ? quantity + 1 : quantity - 1;
            if (quantity > 0) {
                basket.put(ticketId, quantity);
                System.out.println("Updated quantity for Ticket ID " + ticketId + " to " + quantity);
            } else {
                basket.remove(ticketId);
                System.out.println("Removed Ticket ID " + ticketId + " as quantity reached zero.");
            }
        } else {
            System.out.println("Ticket ID not found in the basket.");
        }
    }

    private void removeTicket(Scanner reader) {
        System.out.print("Enter Ticket ID to remove: ");
        int ticketId = reader.nextInt();
        if (basket.containsKey(ticketId)) {
            basket.remove(ticketId);
            System.out.println("Removed Ticket ID " + ticketId + " from the basket.");
        } else {
            System.out.println("Ticket ID not found in the basket.");
        }
    }

    private void calculateTotal() {
        if (basket.isEmpty()) {
            System.out.println("Basket is empty!");
            return;
        }

        double totalCost = 0.0;
        for (Integer ticketId : basket.keySet()) {
            String[] ticket = tickets.get(ticketId);
            int quantity = basket.get(ticketId);
            double price = Double.parseDouble(ticket[2]);
            totalCost += price * quantity;
        }

        System.out.println("Total Cost of Tickets: £" + totalCost);
    }
}
