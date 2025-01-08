package UserInterfaces;

import login.User;
import javax.swing.*;
import java.awt.*;
import PurchaseBasket.Basket;

public class DashboardUI {

    private User loggedInUser;  // Store the logged-in user
    private JFrame frame;
    private Basket basket;

    public DashboardUI(User user) {
        this.loggedInUser = user;
        this.basket = loggedInUser.getBasket();  // Initialize basket from the logged-in user
        createDashboardUI();
    }

    public void createDashboardUI() {
        // Create and configure the main frame
        frame = new JFrame("Ticket Booking Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Create a navigation button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6)); // Adjusted grid for the new Refund Tickets button
        buttonPanel.setBackground(Color.WHITE);

        JButton homeButton = new JButton("Home");
        JButton viewTicketsButton = new JButton("View Tickets");
        JButton refundTicketsButton = new JButton("Refund Tickets"); // New Refund Tickets button
        JButton aboutButton = new JButton("About");
        JButton basketButton = new JButton("Basket");
        JButton profileButton = new JButton("Profile");
        JButton contactSupportButton = new JButton("Contact Support");

        // Add action listeners for buttons
        homeButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Home functionality to be added..."));
        viewTicketsButton.addActionListener(e -> openViewTicketsUI());
        refundTicketsButton.addActionListener(e -> openRefundTicketUI()); // Open RefundTicketUI
        aboutButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "About functionality to be added..."));
        basketButton.addActionListener(e -> openPurchaseBasketUI());
        profileButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Profile functionality to be added..."));
        contactSupportButton.addActionListener(e -> openContactSupportUI());

        // Add buttons to the navigation panel
        buttonPanel.add(homeButton);
        buttonPanel.add(viewTicketsButton);
        buttonPanel.add(refundTicketsButton); // Add Refund Tickets button
        buttonPanel.add(aboutButton);
        buttonPanel.add(basketButton);
        buttonPanel.add(profileButton);
        buttonPanel.add(contactSupportButton);

        // Create the ticket display panel
        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        ticketPanel.setBackground(Color.WHITE);

        JLabel currentTicketsLabel = new JLabel("Current Tickets:");
        currentTicketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketPanel.add(currentTicketsLabel);

        for (String ticket : loggedInUser.getCurrentTickets()) {
            JLabel ticketLabel = new JLabel("- " + ticket);
            ticketPanel.add(ticketLabel);
        }

        JLabel expiredTicketsLabel = new JLabel("Expired Tickets:");
        expiredTicketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketPanel.add(expiredTicketsLabel);

        for (String ticket : loggedInUser.getExpiredTickets()) {
            JLabel ticketLabel = new JLabel("- " + ticket);
            ticketPanel.add(ticketLabel);
        }

        // Add panels to the frame
        frame.add(buttonPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(ticketPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);  // Make the frame visible
    }

    // Opens the RefundTicketUI
    public void openRefundTicketUI() {
        frame.dispose();  // Dispose the current DashboardUI
        new RefundTicketUI(loggedInUser);  // Open RefundTicketUI
    }

    // Opens the ticket browsing UI
    public void openViewTicketsUI() {
        frame.dispose();  // Dispose the current DashboardUI
        new ViewTicketsUI(basket, loggedInUser); // Pass loggedInUser for ticket functionality
    }

    // Opens the purchase basket UI
    public void openPurchaseBasketUI() {
        frame.dispose();  // Dispose the current DashboardUI
        new PurchaseBasketUI(loggedInUser, basket);  // Correctly pass both User and Basket
    }
    
    // Opens the contact support UI
    public void openContactSupportUI() {
        frame.dispose(); // Optional: Dispose of the dashboard if needed
        new ContactSupportUI(); // Open the Contact Support UI
    }
}
