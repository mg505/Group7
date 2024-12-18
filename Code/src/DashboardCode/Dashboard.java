package DashboardCode;

import java.util.ArrayList;
import java.util.List;

public class Dashboard {

    private List<String> currentTickets;
    private List<String> expiredTickets;

    public Dashboard() {
        currentTickets = new ArrayList<>();
        expiredTickets = new ArrayList<>();

        // Example of expired tickets
        expiredTickets.add("Theater Play - $30 - Date: 10/11/2024");
    }

    public void displayDashboard() {
        System.out.println("*****************************************");
        System.out.println("*       Ticket Booking Dashboard        *");
        System.out.println("*****************************************");
        System.out.println("1. Home");
        System.out.println("2. Buy Tickets");
        System.out.println("3. About");
        System.out.println("4. Basket");
        System.out.println("5. Profile");
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("Current Tickets:");
        for (String ticket : currentTickets) {
            System.out.println("- " + ticket);
        }
        System.out.println();

        System.out.println("-----------------------------------------");
        System.out.println("Expired Tickets:");
        for (String ticket : expiredTickets) {
            System.out.println("- " + ticket);
        }
        System.out.println();
    }

    // Add purchased tickets to current tickets
    public void addPurchasedTickets(List<String> purchasedTickets) {
        currentTickets.addAll(purchasedTickets);
    }

    // Get the current tickets
    public List<String> getCurrentTickets() {
        return currentTickets;
    }
}
