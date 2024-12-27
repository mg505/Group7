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
