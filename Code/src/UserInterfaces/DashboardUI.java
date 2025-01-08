package UserInterfaces;

import login.LoginSystem;
import login.User;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import PurchaseBasket.Basket;
import TicketDownloadCode.TicketDownloader;

public class DashboardUI {

    private User loggedInUser;  // Store the logged-in user
    private JFrame frame;
    private Basket basket;
    private LoginSystem loginSystem;

    // Constructor
    public DashboardUI(User loggedInUser, LoginSystem loginSystem) {
        this.loggedInUser = loggedInUser;
        this.basket = loggedInUser.getBasket();  
        this.loginSystem = loginSystem; // Initialize LoginSystem
        createDashboardUI();
    }

    public void createDashboardUI() {
        // Create and configure the main frame
        frame = new JFrame("Ticket Booking Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);  // Adjusted size for better UI
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Create a navigation button panel
        JPanel buttonPanel = new JPanel(new GridLayout(1, 7)); // Adjusted grid for all buttons
        buttonPanel.setBackground(Color.WHITE);

        // Navigation buttons
        JButton homeButton = new JButton("Home");
        JButton viewTicketsButton = new JButton("View Tickets");
        JButton refundTicketsButton = new JButton("Refund Tickets");
        JButton aboutButton = new JButton("About");
        JButton basketButton = new JButton("Basket");
        JButton profileButton = new JButton("Profile");
        JButton contactSupportButton = new JButton("Contact Support");

        // Add action listeners for buttons
        homeButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Home functionality to be added..."));
        viewTicketsButton.addActionListener(e -> openViewTicketsUI());
        refundTicketsButton.addActionListener(e -> openRefundTicketUI());
        aboutButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "About functionality to be added..."));
        basketButton.addActionListener(e -> openPurchaseBasketUI());
        profileButton.addActionListener(e -> openUserProfileUI());
        contactSupportButton.addActionListener(e -> openContactSupportUI());

        // Add buttons to the navigation panel
 
        buttonPanel.add(viewTicketsButton);
        buttonPanel.add(refundTicketsButton);
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

        // Add current tickets to the panel with download buttons
        for (String ticket : loggedInUser.getCurrentTickets()) {
            JPanel ticketItemPanel = new JPanel();
            ticketItemPanel.setLayout(new BorderLayout());
            ticketItemPanel.setBackground(Color.WHITE);

            JLabel ticketLabel = new JLabel("<html><b>Ticket:</b> " + ticket + "</html>");
            JButton downloadButton = new JButton("Download");

            // Style the download button
            downloadButton.setFont(new Font("Arial", Font.BOLD, 12));
            downloadButton.setFocusPainted(false);
            downloadButton.setBackground(new Color(70, 130, 180));
            downloadButton.setForeground(Color.WHITE);
            downloadButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            downloadButton.setPreferredSize(new Dimension(100, 0));

            // Add action listener to download button
            downloadButton.addActionListener(e -> {
                String ticketId = "123";  // Replace with actual ticket ID
                String route = "Route A";  // Replace with actual route
                String time = "10:00 AM";  // Replace with actual time
                String price = "50.00";    // Replace with actual price
                TicketDownloader.downloadTicket(frame, ticketId, route, time, price);
            });

            ticketItemPanel.add(ticketLabel, BorderLayout.CENTER);
            ticketItemPanel.add(downloadButton, BorderLayout.EAST);
            ticketItemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            ticketPanel.add(ticketItemPanel);
        }

        JLabel expiredTicketsLabel = new JLabel("Expired Tickets:");
        expiredTicketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketPanel.add(expiredTicketsLabel);

        // Add expired tickets to the panel (no download button for expired tickets)
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

    // Opens the ticket browsing UI
    public void openViewTicketsUI() {
        frame.dispose();
        new ViewTicketsUI(basket, loggedInUser, loginSystem); // Pass LoginSystem to the next UI
    }

    // Opens the RefundTicketUI
    public void openRefundTicketUI() {
        frame.dispose();
        new RefundTicketUI(loggedInUser, loginSystem); // Pass LoginSystem to the next UI
    }

    // Opens the purchase basket UI
    public void openPurchaseBasketUI() {
        frame.dispose();
        new PurchaseBasketUI(loggedInUser, basket, loginSystem); // Pass LoginSystem to the next UI
    }

    // Opens the contact support UI
    public void openContactSupportUI() {
        frame.dispose();
        new ContactSupportUI(loggedInUser, loginSystem); // Pass LoginSystem to the next UI
    }

    // Opens the user profile UI
    public void openUserProfileUI() {
        frame.dispose();
        new UserProfileUI(loggedInUser, loginSystem); // Pass LoginSystem to the next UI
    }
}
