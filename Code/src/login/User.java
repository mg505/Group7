package login;

import PurchaseBasket.Basket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class User {
    private String username;
    private String password;
    private List<String> currentTickets;
    private List<String> expiredTickets;
    private Basket basket;  // Each user now has their own basket
    private boolean isActive = true;

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
    

    // Display user profile details
    public void displayProfile() {
        System.out.println("User Profile:");
        System.out.println("Username: " + this.username);
        System.out.println("Password: " + this.password);
        System.out.println("Current Tickets: " + currentTickets);
        System.out.println("Expired Tickets: " + expiredTickets);
    }



    // Edit user details
    public void editProfile(String newUsername, String newPassword) {
        if (newUsername != null && !newUsername.trim().isEmpty()) {
            this.username = newUsername;
            System.out.println("Username successfully updated.");
        } else {
            System.out.println("Invalid username. No changes made.");
        }

        if (newPassword != null && !newPassword.trim().isEmpty()) {
            this.password = newPassword;
            System.out.println("Password successfully updated.");
        } else {
            System.out.println("Invalid password. No changes made.");
        }
    }

    // Logout the user and redirect to the welcome page
    public void logout() {
        System.out.println("Logging out...");
        redirectToWelcomePage();
    }

    // Redirect to the welcome/login page
    private void redirectToWelcomePage() {
        System.out.println("Redirecting to the Welcome Page...");
        new UserInterfaces.WelcomePage(); 
    }

    // Delete the user account
    public void deleteAccount() {
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.println("Are you sure you want to delete your account? This action cannot be undone.");
			System.out.println("Type 'YES' to confirm or 'NO' to cancel.");

			String input = scanner.nextLine();

			if (input.equalsIgnoreCase("YES")) {
			    this.setActive(false); // Mark the account as inactive
			    System.out.println("Your account has been deleted. Redirecting to the welcome page...");
			    redirectToWelcomePage();
			} else if (input.equalsIgnoreCase("NO")) {
			    System.out.println("Account deletion canceled.");
			} else {
			    System.out.println("Invalid input. Please try again.");
			}
		}
    }

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public static void main(String[] args) {
	    // Create a new user
	    User user = new User("john_doe", "password123");

	    // Display the profile
	    user.displayProfile();
	    // Edit the profile
	    user.editProfile("john_doe_updated", "newpassword123");
	    // Logout
	    user.logout();

	    // Delete the account (with confirmation)
	    user.deleteAccount();
	}

}
