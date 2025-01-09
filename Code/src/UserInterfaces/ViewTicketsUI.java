package UserInterfaces;

import viewTickets.viewTickets;
import login.LoginSystem;
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
    private User loggedInUser;
    private LoginSystem loginSystem;

    // Constructor 
    public ViewTicketsUI(Basket basket, User loggedInUser, LoginSystem loginSystem) {
        this.loggedInUser = loggedInUser;
        this.loginSystem = loginSystem;
        this.basket = basket;
        viewTickets ticketManager = new viewTickets(loggedInUser);
        this.tickets = ticketManager.getTickets();

        initialiseUI(ticketManager);
    }

    // Initialises the View Tickets UI 
    private void initialiseUI(viewTickets ticketManager) {
        frame = new JFrame("View Tickets");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Panel for displaying the list of available tickets
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

        // Panel for selecting tickets to add to the basket
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

    // Navigates to the dashboard UI
    public void navigateToDashboard() {
        frame.dispose();
        new DashboardUI(loggedInUser, loginSystem);
    }

    // Adds the selected ticket to the user's basket
    public void addSelectedTicketToBasket(viewTickets ticketManager) {
        int selectedIndex = ticketComboBox.getSelectedIndex();
        if (selectedIndex != -1) {
            Integer ticketId = (Integer) tickets.keySet().toArray()[selectedIndex];
            ticketManager.addToBasket(ticketId);  
            JOptionPane.showMessageDialog(frame, "Ticket added to basket.");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a ticket.");
        }
    }

    // Formats the tickets into a display-friendly string
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
