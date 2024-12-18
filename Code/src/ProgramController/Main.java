package ProgramController;

import java.util.Scanner;
import DashboardCode.Dashboard;
import PurchaseBasket.Basket;
import emailServices.EmailService;
import login.LoginSystem;
import viewTickets.viewTickets;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LoginSystem loginSystem = new LoginSystem();
        Dashboard dashboard = new Dashboard();
        Basket basket = new Basket();
        viewTickets viewTicketsPage = new viewTickets(basket);  // Pass basket to viewTickets
        EmailService emailService = new EmailService();

        System.out.println("Welcome to the Ticket Booking System!");

        // Login process
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (!loginSystem.validateLogin(username, password)) {
            System.out.println("Invalid credentials. Exiting...");
            return;
        }
        System.out.println("Login successful. Welcome, " + username + "!");

        // Main menu
        int choice;
        do {
            dashboard.displayDashboard();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 2: // Buy Tickets
                    System.out.println(viewTicketsPage.showTickets());  // Show tickets
                    System.out.print("Enter ticket ID to add to basket: ");
                    int ticketId = scanner.nextInt();
                    viewTicketsPage.addToBasket(ticketId);  // Add ticket to basket
                    break;

                case 4: // Basket
                    basket.displayBasket();  // Display basket
                    System.out.print("Proceed to checkout? (yes/no): ");
                    String proceed = scanner.next();
                    if (proceed.equalsIgnoreCase("yes")) {
                        System.out.print("Enter your email: ");
                        String email = scanner.next();
                        double totalCost = basket.calculateTotalCost();
                        String emailBody = emailService.generateTicketConfirmationMessage(basket.getTickets(), totalCost);
                        emailService.sendEmail(email, "Ticket Confirmation", emailBody);
                        System.out.println("Checkout successful! Confirmation email sent.");
                        basket.clearBasket();  // Clear the basket after checkout
                    }
                    break;

                case 5: // Exit
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
