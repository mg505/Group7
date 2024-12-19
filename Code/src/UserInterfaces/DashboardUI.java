package UserInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import PurchaseBasket.Basket;  // Import Basket class for basket functionality
import viewTickets.viewTickets; 
import UserInterfaces.PurchaseBasketUI;  // Import the PurchaseBasketUI class for the basket

public class DashboardUI {

    private List<String> currentTickets;
    private List<String> expiredTickets;
    private JFrame frame;  // Make frame a class-level field
    private Basket basket;  // Basket object to hold the user's basket

    public DashboardUI() {
        currentTickets = new ArrayList<>();
        expiredTickets = new ArrayList<>();
        basket = new Basket();  // Initialize the basket

        // Example tickets
        //currentTickets.add("Concert A - $50 - Date: 15/12/2024");
        //currentTickets.add("Movie Premiere - $25 - Date: 20/12/2024");

        expiredTickets.add("Theater Play - $30 - Date: 10/11/2024");

        // Create and display the dashboard UI
        createDashboardUI();
    }

    public void createDashboardUI() {
        // Create the frame for the Dashboard
        frame = new JFrame("Ticket Booking Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);  // Set the size of the window
        frame.setLocationRelativeTo(null); // Center the frame

        // Set layout for the frame
        frame.setLayout(new BorderLayout());

        // Create a panel for the buttons (to manage layout)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 5)); // Horizontal buttons
        buttonPanel.setBackground(Color.WHITE);

        // Create buttons for different functionalities
        JButton homeButton = new JButton("Home");
        JButton buyTicketsButton = new JButton("Buy Tickets");
        JButton aboutButton = new JButton("About");
        JButton basketButton = new JButton("Basket");
        JButton profileButton = new JButton("Profile");

        // Add Action Listeners to buttons
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Home functionality to be added...");
            }
        });

        buyTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewTicketsUI();  // Open ViewTicketsUI when clicked
            }
        });

        basketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openPurchaseBasketUI();  // Open the PurchaseBasketUI when the "Basket" button is clicked
            }
        });

        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Profile functionality to be added...");
            }
        });

        // Add buttons to the button panel
        buttonPanel.add(homeButton);
        buttonPanel.add(buyTicketsButton);
        buttonPanel.add(aboutButton);
        buttonPanel.add(basketButton);
        buttonPanel.add(profileButton);

        // Create a panel to display tickets
        JPanel ticketPanel = new JPanel();
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.Y_AXIS)); // Vertical layout for tickets
        ticketPanel.setBackground(Color.WHITE);

        // Display current tickets
        JLabel currentTicketsLabel = new JLabel("Current Tickets:");
        currentTicketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketPanel.add(currentTicketsLabel);

        for (String ticket : currentTickets) {
            JLabel ticketLabel = new JLabel("- " + ticket);
            ticketPanel.add(ticketLabel);
        }

        // Display expired tickets
        JLabel expiredTicketsLabel = new JLabel("Expired Tickets:");
        expiredTicketsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        ticketPanel.add(expiredTicketsLabel);

        for (String ticket : expiredTickets) {
            JLabel ticketLabel = new JLabel("- " + ticket);
            ticketPanel.add(ticketLabel);
        }

        // Add button panel to the top of the frame
        frame.add(buttonPanel, BorderLayout.NORTH);
        // Add ticket panel to the center of the frame
        JScrollPane scrollPane = new JScrollPane(ticketPanel); // Add scroll functionality for tickets
        frame.add(scrollPane, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Get Basket from DashboardUI
    public Basket getBasket() {
        return basket;
    }

    // This method adds purchased tickets to the currentTickets list
    public void addPurchasedTickets(List<String> purchasedTickets) {
        for (String ticket : purchasedTickets) {
            currentTickets.add(ticket);  // Add the purchased ticket to currentTickets list
        }

        // Optionally, update the UI or inform the user that the purchase is successful
        JOptionPane.showMessageDialog(frame, "Tickets successfully added to your current tickets.");
        createDashboardUI();  // Recreate the UI to reflect the changes (optional)
    }

    // Opens the ViewTicketsUI
    private void openViewTicketsUI() {
        new ViewTicketsUI(basket, this);  // Pass 'this' (current instance of DashboardUI) to ViewTicketsUI constructor
    }

    // Opens the PurchaseBasketUI to manage the basket
    private void openPurchaseBasketUI() {
        new PurchaseBasketUI(this);  // Pass the current instance of DashboardUI to PurchaseBasketUI
    }

    // Main method to launch the DashboardUI
    public static void main(String[] args) {
        new DashboardUI();  // Initialize and display the Dashboard UI
    }
}
