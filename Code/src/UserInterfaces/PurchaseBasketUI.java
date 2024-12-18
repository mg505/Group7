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
    private JTextField routeField, timeField, priceField;
    private JLabel totalCostLabel;
    private Basket basket;
    private Dashboard dashboard;

    public PurchaseBasketUI(Dashboard dashboard) {
        this.dashboard = dashboard;
        basket = new Basket();
        initializeUI();
    }

    private void initializeUI() {
        // Create the frame
        frame = new JFrame("Ticket Purchase Basket");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(new BorderLayout());

        // Create the panel for the basket display
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

        // Create the panel for adding tickets and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(1, 3, 10, 10));  // Adjusted to hold 3 buttons

        // Buttons
        JButton homeButton = new JButton("Home");
        JButton proceedButton = new JButton("Proceed with Purchase");
        JButton clearButton = new JButton("Clear Basket");

        // Add action listeners for buttons
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DashboardUI();
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

        // Add buttons to the input panel
        inputPanel.add(homeButton);
        inputPanel.add(proceedButton);
        inputPanel.add(clearButton);

        // Add input panel to the frame
        frame.add(inputPanel, BorderLayout.SOUTH);

        // Create a panel for displaying the total cost
        JPanel totalPanel = new JPanel();
        totalCostLabel = new JLabel("Total Cost: £0.00");
        totalPanel.add(totalCostLabel);
        frame.add(totalPanel, BorderLayout.NORTH);

        // Make the frame visible
        frame.setVisible(true);
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

        // Add tickets to current tickets in the Dashboard
        List<String> purchasedTickets = new ArrayList<>();
        for (Integer ticketId : basket.getTickets().keySet()) {
            String[] details = basket.getTickets().get(ticketId);
            purchasedTickets.add("Route: " + details[0] + ", Time: " + details[1] + ", Price: £" + details[2]);
        }

        dashboard.addPurchasedTickets(purchasedTickets);
        JOptionPane.showMessageDialog(frame, "Purchase complete. Tickets added to your current tickets.");
        clearBasket();  // Clear the basket after purchase
    }

    private void sendEmailConfirmation(String email) {
        // Simulate sending email (this can be replaced with actual email sending logic)
        JOptionPane.showMessageDialog(frame, "Email sent to: " + email);
    }

    private void clearBasket() {
        basket.clearBasket();
        basketDisplayArea.setText("");
        totalCostLabel.setText("Total Cost: £0.00");
        JOptionPane.showMessageDialog(frame, "Basket cleared.");
    }

    private void DashboardUI() {
        // Placeholder method to navigate back to the Dashboard UI
        JOptionPane.showMessageDialog(frame, "Navigating to Dashboard...");
        // Here you can implement actual navigation to the Dashboard UI if needed
    }

    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        new PurchaseBasketUI(dashboard);  // Launch the UI with the dashboard
    }
}
