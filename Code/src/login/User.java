package login;

import PurchaseBasket.Basket;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<String> currentTickets;
    private List<String> expiredTickets;
    private Basket basket;
    private boolean isActive;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.currentTickets = new ArrayList<>();
        this.expiredTickets = new ArrayList<>();
        this.basket = new Basket();
     // Set account active by default
        this.isActive = true; 
    }

    // Getters and setters
    public String getUsername() {
    	return username; }
    
    public void setUsername(String username) { 
    	this.username = username; }
    
    public String getPassword() { 
    	return password; }
    
    public void setPassword(String password) {
    	this.password = password; }
    
    public List<String> getCurrentTickets() { 
    	return currentTickets; }
    
    public void addCurrentTicket(String ticket) {
    	this.currentTickets.add(ticket); }
    
    public List<String> getExpiredTickets() { 
    	return expiredTickets; }
    
    public void addExpiredTicket(String ticket) { 
    	this.expiredTickets.add(ticket); }
    
    public Basket getBasket() {
    	return basket; }
    
    public boolean isActive() { 
    	return isActive; }
    
    public void deactivateAccount() { 
    	this.isActive = false; }
    
    public void reactivateAccount() {
    	this.isActive = true; }

    // Methods to get ticket counts
    public String getNumberOfExpiredTickets() {
        return String.valueOf(expiredTickets.size());
    }

    public String getNumberOfCurrentTickets() {
        return String.valueOf(currentTickets.size());
    }

    // Move ticket from current to expired
    public void expireTicket(String ticket) {
        if (currentTickets.contains(ticket)) {
            currentTickets.remove(ticket);
            expiredTickets.add(ticket);
        }
    }

    // Remove ticket from current list
    public void removeCurrentTicket(String ticket) {
        currentTickets.remove(ticket);
    }

    // Remove ticket from expired list
    public void removeExpiredTicket(String ticket) {
        expiredTickets.remove(ticket);
    }

    // Print user details 
    public void printUserDetails() {
        System.out.println("Username: " + username);
        System.out.println("Active: " + isActive);
        System.out.println("Current Tickets: " + currentTickets);
        System.out.println("Expired Tickets: " + expiredTickets);
    }
}
