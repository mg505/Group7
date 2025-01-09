package UserInterfaces;

import TicketDownloadCode.TicketDownloader; // Import the TicketDownloader class
import login.LoginSystem;
import login.User;
import javax.swing.*;
import java.awt.*;
import PurchaseBasket.Basket;
import Calendar.Calendar;

public class DashboardUI {

    private User loggedInUser;
    private JFrame frame;
    private Basket basket;
    private LoginSystem loginSystem;

    // Constructor to initialise the Dashboard UI
    public DashboardUI(User loggedInUser, LoginSystem loginSystem) {
        this.loggedInUser = loggedInUser;
        this.basket = loggedInUser.getBasket();
        this.loginSystem = loginSystem;
        createDashboardUI();
    }

    // Method to create the main Dashboard UI
    public void createDashboardUI() {
        // Create and configure the main dashboard frame
        frame = new JFrame("Ticket Booking Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Create a panel for navigation buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6));
        buttonPanel.setBackground(Color.WHITE);

        // Navigation buttons for dashboard
        JButton viewTicketsButton = new JButton("View Tickets");
        JButton refundTicketsButton = new JButton("Refund Tickets");
        JButton basketButton = new JButton("Basket");
        JButton profileButton = new JButton("Profile");
        JButton contactSupportButton = new JButton("Contact Support");
        JButton calendarButton = new JButton("View Calendar");

        // Action listeners to buttons
        viewTicketsButton.addActionListener(e -> openViewTicketsUI());
        refundTicketsButton.addActionListener(e -> openRefundTicketUI());
        basketButton.addActionListener(e -> openPurchaseBasketUI());
        profileButton.addActionListener(e -> openUserProfileUI());
        contactSupportButton.addActionListener(e -> openContactSupportUI());
        calendarButton.addActionListener(e -> openCalendarUI());

        // Add buttons to navigation panel
        buttonPanel.add(viewTicketsButton);
        buttonPanel.add(refundTicketsButton);
        buttonPanel.add(basketButton);
        buttonPanel.add(profileButton);
        buttonPanel.add(contactSupportButton);
        buttonPanel.add(calendarButton);

        // Create the panel for displaying tickets
        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS));
        ticketPanel.setBackground(Color.WHITE);

        // Current Tickets Section
        JLabel currentTicketsLabel = new JLabel("Current Tickets:");
        currentTicketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketPanel.add(currentTicketsLabel);

        // Iterate over the current tickets and display them with a download button
        for (String ticket : loggedInUser.getCurrentTickets()) {
            JPanel ticketItemPanel = new JPanel(new BorderLayout());
            ticketItemPanel.setBackground(Color.WHITE);

            // Display the ticket information as a label
            JLabel ticketLabel = new JLabel("<html><b>Ticket:</b> " + ticket + "</html>");

            // Download button with functionality
            JButton downloadButton = new JButton("Download");
            downloadButton.setFont(new Font("Arial", Font.BOLD, 12));
            downloadButton.setFocusPainted(false);
            downloadButton.setBackground(new Color(70, 130, 180));
            downloadButton.setForeground(Color.WHITE);
            downloadButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
            downloadButton.setPreferredSize(new Dimension(100, 0));

            // Add action listener to download the ticket when the button is clicked
            downloadButton.addActionListener(e -> {
                // Directly pass the ticket string without splitting
                TicketDownloader.downloadTicket(frame, ticket);
            });

           

            ticketItemPanel.add(ticketLabel, BorderLayout.CENTER);
            ticketItemPanel.add(downloadButton, BorderLayout.EAST);
            ticketItemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            ticketPanel.add(ticketItemPanel);
        }

        // Expired Tickets Section
        JLabel expiredTicketsLabel = new JLabel("Expired Tickets:");
        expiredTicketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketPanel.add(expiredTicketsLabel);

        // Add expired tickets to its section
        for (String ticket : loggedInUser.getExpiredTickets()) {
            JLabel ticketLabel = new JLabel("- " + ticket);
            ticketPanel.add(ticketLabel);
        }

        // Add components to the frame
        frame.add(buttonPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(ticketPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // Navigation methods to the other  UIs
    public void openViewTicketsUI() {
        frame.dispose();
        new ViewTicketsUI(basket, loggedInUser, loginSystem);
    }

    public void openRefundTicketUI() {
        frame.dispose();
        new RefundTicketUI(loggedInUser, loginSystem);
    }

    public void openPurchaseBasketUI() {
        frame.dispose();
        new PurchaseBasketUI(loggedInUser, basket, loginSystem);
    }

    public void openContactSupportUI() {
        frame.dispose();
        new ContactSupportUI(loggedInUser, loginSystem);
    }

    public void openUserProfileUI() {
        frame.dispose();
        new UserProfileUI(loggedInUser, loginSystem);
    }

    public void openCalendarUI() {
        frame.dispose();
        new CalendarUI(new Calendar(), loggedInUser, loginSystem);
    }
}
