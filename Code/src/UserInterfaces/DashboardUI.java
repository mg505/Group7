package UserInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class DashboardUI {

    private List<String> currentTickets;
    private List<String> expiredTickets;

    public DashboardUI() {
        currentTickets = new ArrayList<>();
        expiredTickets = new ArrayList<>();

        // Example tickets
        currentTickets.add("Concert A - $50 - Date: 15/12/2024");
        currentTickets.add("Movie Premiere - $25 - Date: 20/12/2024");

        expiredTickets.add("Theater Play - $30 - Date: 10/11/2024");
    }

    public void createDashboardUI() {
        // Create the frame for the Dashboard
        JFrame frame = new JFrame("Ticket Booking Dashboard");
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

        // Add Action Listeners to buttons (for now they just show a message)
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Home functionality to be added...");
            }
        });

        buyTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Buy Tickets functionality to be added...");
            }
        });

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "About functionality to be added...");
            }
        });

        basketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Basket functionality to be added...");
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
        frame.add(ticketPanel, BorderLayout.CENTER);

        // Make the frame visible
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create and display the Dashboard UI
        DashboardUI dashboardUI = new DashboardUI();
        dashboardUI.createDashboardUI();
    }
}
