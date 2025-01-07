package UserInterfaces;


// RefundTicketUI.java (Interface)

import login.User;
import javax.swing.*;

import RefundTicket.RefundTicket;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefundTicketUI {

   private User loggedInUser;
   private JFrame frame;
   private RefundTicket refundTicket;

   public RefundTicketUI(User user) {
       this.loggedInUser = user;
       this.refundTicket = new RefundTicket();
       createRefundTicketUI();
   }

   public void createRefundTicketUI() {
	    // Create and configure the main frame
	    frame = new JFrame("Refund Tickets");
	    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    frame.setSize(600, 400);
	    frame.setLocationRelativeTo(null);
	    frame.setLayout(new BorderLayout());

	    // Create a panel for ticket refund
	    JPanel refundPanel = new JPanel();
	    refundPanel.setLayout(new BoxLayout(refundPanel, BoxLayout.Y_AXIS));
	    refundPanel.setBackground(Color.WHITE);

	    JLabel refundLabel = new JLabel("Refund Tickets:");
	    refundLabel.setFont(new Font("Arial", Font.BOLD, 16));
	    refundPanel.add(refundLabel);

	    // Create a drop-down menu for selecting a ticket to refund
	    String[] tickets = loggedInUser.getCurrentTickets().toArray(new String[0]);
	    JComboBox<String> ticketDropdown = new JComboBox<>(tickets);
	    refundPanel.add(ticketDropdown);

	    // Create a button for processing the refund
	    JButton refundButton = new JButton("Refund Ticket");
	    refundButton.setEnabled(tickets.length > 0); // Disable button if no tickets are available
	    refundPanel.add(refundButton);

	    refundButton.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            String selectedTicket = (String) ticketDropdown.getSelectedItem();
	            if (selectedTicket != null) {
	                boolean success = refundTicket.refundTicket(loggedInUser, selectedTicket);
	                if (success) {
	                    JOptionPane.showMessageDialog(frame, "Ticket " + selectedTicket + " has been successfully refunded.");
	                    // Update the dropdown after refund
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

	    // Add a back button to return to the Dashboard
	    JButton backButton = new JButton("Back to Dashboard");
	    backButton.addActionListener(e -> {
	        frame.dispose();  // Dispose the current RefundTicketUI
	        new DashboardUI(loggedInUser);  // Open the DashboardUI
	    });
	    refundPanel.add(backButton);

	    // Add the refund panel to the frame
	    frame.add(refundPanel, BorderLayout.CENTER);

	    frame.setVisible(true);
	}
}