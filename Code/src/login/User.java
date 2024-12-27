package login;

import PurchaseBasket.Basket;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<String> currentTickets;
    private List<String> expiredTickets;
    private Basket basket;  // Each user now has their own basket

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.currentTickets = new ArrayList<>();
        this.expiredTickets = new ArrayList<>();
        this.basket = new Basket();  // Initialize the user's basket
    }

    // Getters and setters for username and password
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Get current tickets
    public List<String> getCurrentTickets() {
        return currentTickets;
    }

    // Add a ticket to current tickets
    public void addCurrentTicket(String ticket) {
        this.currentTickets.add(ticket);
    }

    // Get expired tickets
    public List<String> getExpiredTickets() {
        return expiredTickets;
    }

    // Add a ticket to expired tickets
    public void addExpiredTicket(String ticket) {
        this.expiredTickets.add(ticket);
    }

    // Get the user's basket
    public Basket getBasket() {
        return basket;
    }
    
    public void addPurchasedTickets(List<String> purchasedTickets) {
        if (purchasedTickets != null && !purchasedTickets.isEmpty()) {
            this.currentTickets.addAll(purchasedTickets); // Add all tickets to currentTickets
        }
    }


	
}
