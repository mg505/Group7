package UserInterfaces;

import login.User;
import login.LoginSystem;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import PurchaseBasket.Basket;
import emailServices.EmailService;

public class PurchaseBasketUI {

    private JFrame frame;  
    private JTextArea basketDisplayArea; 
    private JLabel totalCostLabel;  
    private User currentUser; 
    private Basket basket;  
    private LoginSystem loginSystem;  

    // Constructor 
    public PurchaseBasketUI(User currentUser, Basket basket, LoginSystem loginSystem) {
        this.currentUser = currentUser;
        this.basket = basket;
        this.loginSystem = loginSystem;
        initializeUI(); 
    }

    // Initialises the purchase basket UI
    private void initializeUI() {
        frame = new JFrame("Ticket Purchase Basket");  
        frame.setSize(600, 400);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setLocationRelativeTo(null); 
        frame.setLayout(new BorderLayout());

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Basket Contents"));

        basketDisplayArea = new JTextArea();  
        basketDisplayArea.setEditable(false); 
        basketDisplayArea.setFont(new Font("Arial", Font.PLAIN, 14)); 
        JScrollPane scrollPane = new JScrollPane(basketDisplayArea);  
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(displayPanel, BorderLayout.CENTER);

        JPanel totalPanel = new JPanel();
        totalCostLabel = new JLabel("Total Cost: £0.00"); 
        totalPanel.add(totalCostLabel);  
        frame.add(totalPanel, BorderLayout.NORTH);

        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new GridLayout(1, 3, 10, 10)); 

        JButton proceedButton = new JButton("Proceed with Purchase");
        JButton clearButton = new JButton("Clear Basket");  
        JButton homeButton = new JButton("Home");  

        homeButton.addActionListener(e -> navigateToDashboard());  
        proceedButton.addActionListener(e -> proceedWithPurchase());
        clearButton.addActionListener(e -> clearBasket()); 

        actionPanel.add(homeButton);
        actionPanel.add(proceedButton);
        actionPanel.add(clearButton);

        frame.add(actionPanel, BorderLayout.SOUTH);

        displayBasketContents();  

        frame.setVisible(true); 
    }

    // Display the contents of the basket and the total cost
    public void displayBasketContents() {
        StringBuilder basketContent = new StringBuilder();
        double totalCost = 0;
        
        // Get the tickets in the basket
        Map<Integer, String[]> tickets = basket.getTickets();  

        for (Integer ticketId : tickets.keySet()) {  
            String[] ticketDetails = tickets.get(ticketId);
            basketContent.append("Route: ").append(ticketDetails[0])
                    .append(", Time: ").append(ticketDetails[1])
                    .append(", Price: £").append(ticketDetails[2]).append("\n");

            totalCost += Double.parseDouble(ticketDetails[2]); 
        }

        basketDisplayArea.setText(basketContent.toString());  
        totalCostLabel.setText("Total Cost: £" + totalCost); 
    }

    // Clear the basket and update the display
    public void clearBasket() {
        basket.clearBasket();  
        displayBasketContents(); 
        JOptionPane.showMessageDialog(frame, "Basket cleared.");
    }

    // Proceed with the purchase and handle email confirmation if needed
    public void proceedWithPurchase() {
        int confirmation = JOptionPane.showConfirmDialog(frame, "Would you like to provide an email for confirmation?", "Email Confirmation", JOptionPane.YES_NO_OPTION);

        if (confirmation == JOptionPane.YES_OPTION) {
            String email = JOptionPane.showInputDialog(frame, "Enter your email address:");
            if (email != null && !email.isEmpty()) {
            	// Send an email confirmation
                sendEmailConfirmation(email);  
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid email. Proceeding without email.");
            }
        }

        // Checkout the basket and create a list of purchased tickets
        List<String> purchasedTickets = new ArrayList<>();
        for (String[] ticketDetails : basket.checkoutBasket().values()) {
            purchasedTickets.add("Route: " + ticketDetails[0] + ", Time: " + ticketDetails[1] + ", Price: £" + ticketDetails[2]);
        }

       
        addPurchasedTickets(purchasedTickets);

        JOptionPane.showMessageDialog(frame, "Purchase complete. Tickets added to your account.");
        displayBasketContents(); 

        navigateToDashboard();  
    }

    // Add the purchased tickets to the user's current tickets
    public void addPurchasedTickets(List<String> purchasedTickets) {
        for (String ticket : purchasedTickets) {
            currentUser.addCurrentTicket(ticket);  
        }
        JOptionPane.showMessageDialog(frame, "Tickets successfully added to your current tickets.");
    }

    // Send an email confirmation 
    public void sendEmailConfirmation(String email) {
        EmailService emailService = new EmailService();
        String subject = "Train Ticket Confirmation";
        String messageBody = emailService.generateTicketConfirmationMessage(basket.getTickets(), basket.calculateTotalCost());

        try {
        	// Send the email
            emailService.sendEmail(email, subject, messageBody);  
            JOptionPane.showMessageDialog(frame, "Email sent successfully to: " + email);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Failed to send email. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Navigate back to the dashboard
    private void navigateToDashboard() {
        frame.dispose(); 
        new DashboardUI(currentUser, loginSystem); 
    }
}
