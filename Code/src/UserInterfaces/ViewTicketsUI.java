package UserInterfaces;

import viewTickets.viewTickets;
import login.User;
import PurchaseBasket.Basket;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ViewTicketsUI {

    private JFrame frame;
    private JTextArea ticketsDisplayArea;
    private JComboBox<String> ticketComboBox;
    private Map<Integer, String[]> tickets;
    private Basket basket;
    private User user;

    public ViewTicketsUI(Basket basket, User loggedInUser) {
        this.basket = basket;
        this.user = loggedInUser;
        viewTickets ticketManager = new viewTickets(loggedInUser);
        this.tickets = ticketManager.getTickets();

        initializeUI(ticketManager);
    }

    private void initializeUI(viewTickets ticketManager) {
        frame = new JFrame("View Tickets");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Available Tickets"));

        ticketsDisplayArea = new JTextArea();
        ticketsDisplayArea.setEditable(false);
        ticketsDisplayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        ticketsDisplayArea.setText(formatTicketsForDisplay());
        JScrollPane scrollPane = new JScrollPane(ticketsDisplayArea);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(displayPanel, BorderLayout.CENTER);

        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout());

        ticketComboBox = new JComboBox<>();
        for (Integer ticketId : tickets.keySet()) {
            String[] ticketDetails = tickets.get(ticketId);
            String displayText = "Ticket ID: " + ticketId + " | Route: " + ticketDetails[0] + " | Time: " + ticketDetails[1] + " | Price: £" + ticketDetails[2];
            ticketComboBox.addItem(displayText);
        }

        JButton addButton = new JButton("Add to Basket");
        JButton homeButton = new JButton("Home");

        addButton.addActionListener(e -> addSelectedTicketToBasket(ticketManager));
        homeButton.addActionListener(e -> navigateToDashboard());

        selectionPanel.add(ticketComboBox);
        selectionPanel.add(addButton);
        selectionPanel.add(homeButton);

        frame.add(selectionPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void navigateToDashboard() {
        frame.dispose();  // Close the current ViewTicketsUI frame

        // Create a new instance of DashboardUI, passing the logged-in user
        new DashboardUI(this.user);  // Assuming loggedInUser is the user you are passing to the dashboard
    }

    public void addSelectedTicketToBasket(viewTickets ticketManager) {
        int selectedIndex = ticketComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            Integer ticketId = (Integer) tickets.keySet().toArray()[selectedIndex];
            ticketManager.addToBasket(ticketId);  // Add ticket to the basket
            JOptionPane.showMessageDialog(frame, "Ticket added to basket.");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a ticket.");
        }
    }

    // This method will format and display the tickets as a string
    public String formatTicketsForDisplay() {
        StringBuilder output = new StringBuilder();
        for (Integer ticketId : tickets.keySet()) {
            String[] details = tickets.get(ticketId);
            output.append("Ticket ID: ").append(ticketId)
                  .append("\nRoute: ").append(details[0])
                  .append("\nTime: ").append(details[1])
                  .append("\nPrice: £").append(details[2])
                  .append("\n---------------------------------\n");
        }
        return output.toString();
    }
}
