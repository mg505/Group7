package UserInterfaces;
import login.LoginSystem;
import login.User;
import viewTickets.viewTickets;
import PurchaseBasket.Basket;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTicketsUI {

    private JFrame frame;
    private JTextArea ticketsDisplayArea;
    private JButton addButton;
    private JComboBox<String> ticketComboBox;
    private Basket basket;
    private viewTickets viewTickets;

    public ViewTicketsUI(Basket basket) {
        this.basket = basket;
        viewTickets = new viewTickets(basket);  // Initialize the backend class
        initializeUI();
    }

    // Initialize the UI components
    private void initializeUI() {
        frame = new JFrame("View Tickets");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(new BorderLayout());

        // Create the panel for displaying available tickets
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder("Available Tickets"));

        ticketsDisplayArea = new JTextArea();
        ticketsDisplayArea.setEditable(false);
        ticketsDisplayArea.setFont(new Font("Arial", Font.PLAIN, 14));
        ticketsDisplayArea.setText(viewTickets.showTickets());  // Display available tickets in the text area
        JScrollPane scrollPane = new JScrollPane(ticketsDisplayArea);
        displayPanel.add(scrollPane, BorderLayout.CENTER);

        // Add the display panel to the frame
        frame.add(displayPanel, BorderLayout.CENTER);

        // Create a panel for ticket selection and the "Add to Basket" button
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new FlowLayout());

        // Create a combo box to display available ticket IDs
        ticketComboBox = new JComboBox<>();
        for (Integer ticketId : viewTickets.getTickets().keySet()) {
            ticketComboBox.addItem("Ticket ID: " + ticketId);
        }

        // Create the add button
        addButton = new JButton("Add to Basket");

        // Add action listener for the button
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addSelectedTicketToBasket();
            }
        });

        // Add combo box and button to the selection panel
        selectionPanel.add(ticketComboBox);
        selectionPanel.add(addButton);

        // Add selection panel to the frame
        frame.add(selectionPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Add the selected ticket to the basket
    private void addSelectedTicketToBasket() {
        int selectedIndex = ticketComboBox.getSelectedIndex() + 1; // ComboBox is 0-indexed, but tickets start from 1
        viewTickets.addToBasket(selectedIndex);  // Add the selected ticket to the basket
        JOptionPane.showMessageDialog(frame, "Ticket added to basket.");
    }

    public static void main(String[] args) {
        Basket basket = new Basket(); // Create an instance of Basket
        new ViewTicketsUI(basket);  // Launch the UI to view tickets
    }
}