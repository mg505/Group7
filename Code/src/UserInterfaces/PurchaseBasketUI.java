package UserInterfaces;

import PurchaseBasket.Basket;
import DashboardCode.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class PurchaseBasketUI {

    private JFrame frame;
    private JTextArea basketDisplayArea;
    private JLabel totalCostLabel;
    private Basket basket;
    private DashboardUI dashboardUI;

    // Constructor accepting DashboardUI instance
    public PurchaseBasketUI(DashboardUI dashboard) {
        this.dashboardUI = dashboard;  // Store the DashboardUI instance
        this.basket = dashboard.getBasket();  // Get the basket from DashboardUI
        initializeUI();
    }

    private void initializeUI() {
        // Create the frame
        frame = new JFrame("Ticket Purchase Basket");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(new BorderLayout());

        // Panel for displaying basket contents
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Basket Contents"));

        basketDisplayArea = new JTextArea();
        basketDisplayArea.setEditable(false);
        basketDisplayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(basketDisplayArea);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the display panel to the frame
        frame.add(displayPanel, BorderLayout.CENTER);

        // Panel for total cost
        JPanel totalPanel = new JPanel();
        totalCostLabel = new JLabel("Total Cost: £0.00");
        totalPanel.add(totalCostLabel);
        frame.add(totalPanel, BorderLayout.NORTH);

        // Panel for actions (buttons)
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 3, 10, 10));

        JButton proceedButton = new JButton("Proceed with Purchase");
        JButton clearButton = new JButton("Clear Basket");
        JButton homeButton = new JButton("Home");

        // Add action listeners for buttons
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToDashboard();  // Go back to Dashboard
            }
        });

        proceedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proceedWithPurchase();
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearBasket();
            }
        });

        // Add buttons to the action panel
        actionPanel.add(homeButton);
        actionPanel.add(proceedButton);
        actionPanel.add(clearButton);

        // Add action panel to the frame
        frame.add(actionPanel, BorderLayout.SOUTH);

        // Update the display of the basket
        displayBasketContents();

        // Make the frame visible
        frame.setVisible(true);
    }

    private void displayBasketContents() {
        StringBuilder basketContent = new StringBuilder();
        double totalCost = 0;

        // Loop through the basket and display each ticket's details (Route, Time, Price)
        for (Integer ticketId : basket.getTickets().keySet()) {
            String[] ticketDetails = basket.getTickets().get(ticketId);
            basketContent.append("Route: ").append(ticketDetails[0])
                    .append(", Time: ").append(ticketDetails[1])
                    .append(", Price: £").append(ticketDetails[2]).append("\n");

            // Add the ticket price to the total cost
            totalCost += Double.parseDouble(ticketDetails[2]);
        }

        basketDisplayArea.setText(basketContent.toString());
        totalCostLabel.setText("Total Cost: £" + totalCost);
    }

    private void clearBasket() {
        basket.clearBasket();  // Clear the basket
        basketDisplayArea.setText("");  // Clear the display area
        totalCostLabel.setText("Total Cost: £0.00");  // Reset the total cost label
        JOptionPane.showMessageDialog(frame, "Basket cleared.");
    }

    private void proceedWithPurchase() {
        int confirmation = JOptionPane.showConfirmDialog(frame, "Would you like to provide an email for confirmation?", "Email Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            String email = JOptionPane.showInputDialog(frame, "Enter your email address:");

            if (email != null && !email.isEmpty()) {
                sendEmailConfirmation(email);
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid email. Proceeding without email.");
            }
        }

        // Create a list to store the purchased tickets
        List<String> purchasedTickets = new ArrayList<>();

        // Add tickets from the basket to the purchasedTickets list
        for (Integer ticketId : basket.getTickets().keySet()) {
            String[] details = basket.getTickets().get(ticketId);
            purchasedTickets.add("Route: " + details[0] + ", Time: " + details[1] + ", Price: £" + details[2]);
        }

        // Add the purchased tickets to the DashboardUI
        dashboardUI.addPurchasedTickets(purchasedTickets);  // This adds to the currentTickets list in DashboardUI

        // Inform the user that the purchase is complete
        JOptionPane.showMessageDialog(frame, "Purchase complete. Tickets added to your current tickets.");
        
        // Optionally, clear the basket after purchase
        clearBasket();
    }



    private void sendEmailConfirmation(String email) {
        // Simulate sending an email (you can replace this with actual email logic)
        JOptionPane.showMessageDialog(frame, "Email sent to: " + email);
    }

    private void navigateToDashboard() {
        // Navigate back to the Dashboard UI
        frame.dispose();  // Close the current PurchaseBasketUI frame
        new DashboardUI();  // Create and display a new DashboardUI instance (replace with actual navigation if necessary)
    }

    public static void main(String[] args) {
        DashboardUI dashboardUI = new DashboardUI();  // Create a new DashboardUI instance
        new PurchaseBasketUI(dashboardUI);  // Launch the PurchaseBasketUI with the dashboard
    }
}
