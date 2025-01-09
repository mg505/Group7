package UserInterfaces;

import login.User;
import login.LoginSystem;
import javax.swing.*;
import RefundTicket.RefundTicket;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefundTicketUI {

    private User loggedInUser;  
    private JFrame frame; 
    private RefundTicket refundTicket; 
    private LoginSystem loginSystem;  

    // Constructor 
    public RefundTicketUI(User user, LoginSystem loginSystem) {
        this.loggedInUser = user;
        this.loginSystem = loginSystem;
        this.refundTicket = new RefundTicket();  
        // Initialise the refund UI
        createRefundTicketUI();
    }

    // Creates the Refund Ticket UI
    public void createRefundTicketUI() {
        frame = new JFrame("Refund Tickets"); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        frame.setSize(600, 400); 
        frame.setLocationRelativeTo(null); 
        frame.setLayout(new BorderLayout());  

        // Create the refund panel
        JPanel refundPanel = new JPanel();
        refundPanel.setLayout(new BoxLayout(refundPanel, BoxLayout.Y_AXIS));
        refundPanel.setBackground(Color.WHITE);

        JLabel refundLabel = new JLabel("Refund Tickets:"); 
        refundLabel.setFont(new Font("Arial", Font.BOLD, 16));  
        refundPanel.add(refundLabel);

        // Create a drop-down menu to select a ticket to refund
        String[] tickets = loggedInUser.getCurrentTickets().toArray(new String[0]);
        JComboBox<String> ticketDropdown = new JComboBox<>(tickets);
        refundPanel.add(ticketDropdown);

        // Create a button to refund the selected ticket
        JButton refundButton = new JButton("Refund Ticket");
        refundButton.setEnabled(tickets.length > 0);
        refundPanel.add(refundButton);

        // Action listener for the refund button
        refundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedTicket = (String) ticketDropdown.getSelectedItem();
                if (selectedTicket != null) {
                    boolean success = refundTicket.refundTicket(loggedInUser, selectedTicket); 
                    if (success) {
                        JOptionPane.showMessageDialog(frame, "Ticket " + selectedTicket + " has been successfully refunded.");
                        // Update the dropdown and disable the refund button if no tickets are left
                        ticketDropdown.removeItem(selectedTicket);
                        if (ticketDropdown.getItemCount() == 0) {
                            refundButton.setEnabled(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to refund ticket " + selectedTicket + ".");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select a ticket to refund.");
                }
            }
        });

        // Create a button to go back to the Dashboard
        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> {
            frame.dispose();  
            new DashboardUI(loggedInUser, loginSystem); 
        });
        refundPanel.add(backButton);

        // Add the refund panel to the frame
        frame.add(refundPanel, BorderLayout.CENTER);

        frame.setVisible(true); 
    }
}
