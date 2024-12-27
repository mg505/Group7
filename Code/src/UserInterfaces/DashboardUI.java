package UserInterfaces;

import login.User;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import PurchaseBasket.Basket;
import TicketDownloadCode.TicketDownloader; // Import the TicketDownloader class

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
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        buttonPanel.setBackground(Color.WHITE);

        JButton homeButton = new JButton("Home");
        JButton viewTicketsButton = new JButton("View Tickets");
        JButton aboutButton = new JButton("About");
        JButton basketButton = new JButton("Basket");
        JButton profileButton = new JButton("Profile");

        // Add action listeners for buttons
        homeButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Home functionality to be added..."));
        viewTicketsButton.addActionListener(e -> openViewTicketsUI());
        aboutButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "About functionality to be added..."));
        basketButton.addActionListener(e -> openPurchaseBasketUI());
        profileButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Profile functionality to be added..."));

        // Add buttons to the navigation panel
        buttonPanel.add(homeButton);
        buttonPanel.add(viewTicketsButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(basketButton);
        buttonPanel.add(profileButton);

        // Create the ticket display panel
        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        ticketPanel.setBackground(Color.WHITE);

        JLabel currentTicketsLabel = new JLabel("Current Tickets:");
        currentTicketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketPanel.add(currentTicketsLabel);

        // Add current tickets to the panel with download buttons
        for (String ticket : loggedInUser.getCurrentTickets()) {
            // Assuming each ticket has an ID, route, time, and price
            String ticketId = "123";  // Replace with actual ticket ID
            String route = "Route A";  // Replace with actual route
            String time = "10:00 AM";  // Replace with actual time
            String price = "50.00";    // Replace with actual price

            JPanel ticketItemPanel = new JPanel();
            ticketItemPanel.setLayout(new BorderLayout());
            ticketItemPanel.setBackground(Color.WHITE);

            // Create ticket details and button
            JLabel ticketLabel = new JLabel("<html><b>Ticket:</b> " + ticket + "</html>");
            JButton downloadButton = new JButton("Download");

            // Set a fixed size for the download button
            downloadButton.setPreferredSize(new Dimension(40, 40)); // Fixed width and height

            // Add action listener to download button
            downloadButton.addActionListener(e -> 
                TicketDownloader.downloadTicket(frame, ticketId, route, time, price)
            );

            // Add details and button to the ticket panel
            ticketItemPanel.add(ticketLabel, BorderLayout.CENTER);
            ticketItemPanel.add(downloadButton, BorderLayout.EAST);

            // Add some spacing
            ticketItemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            ticketPanel.add(ticketItemPanel);
        }

        JLabel expiredTicketsLabel = new JLabel("Expired Tickets:");
        expiredTicketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketPanel.add(expiredTicketsLabel);

        // Add expired tickets to the panel (no download button for expired tickets in this case)
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
        frame.dispose();  // Dispose the current DashboardUI
        new ViewTicketsUI(basket, loggedInUser); // Pass loggedInUser for ticket functionality
    }

    // Opens the purchase basket UI
    public void openPurchaseBasketUI() {
        frame.dispose();  // Dispose the current DashboardUI
        // Pass the logged-in user and the basket to the PurchaseBasketUI
        new PurchaseBasketUI(loggedInUser, basket);  // Correctly pass both User and Basket
    }
}
