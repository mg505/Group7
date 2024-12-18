package UserInterfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage {

    public static void main(String[] args) {
        // Create the frame for the Welcome Page
        JFrame frame = new JFrame("Train Ticket Service");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);  // Increased size for a bigger screen
        frame.setLocationRelativeTo(null); // Center the frame

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Train Ticket Service!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Larger font size
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(welcomeLabel);

        // Create a description label
        JLabel descriptionLabel = new JLabel("Please choose an option to proceed:");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(descriptionLabel);

        // Create a button for Login
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setPreferredSize(new Dimension(200, 40)); // Set button size
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Login functionality to be added...");
                // You can add functionality to open the login page here
            }
        });
        panel.add(loginButton);

        // Create a button for Sign Up
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signUpButton.setPreferredSize(new Dimension(200, 40)); // Set button size
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Sign Up functionality to be added...");
                // You can add functionality to open the sign-up page here
            }
        });
        panel.add(signUpButton);

        // Add some vertical space
        panel.add(Box.createVerticalStrut(20));

        // Create a button to exit the application
        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setPreferredSize(new Dimension(200, 40)); // Set button size
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });
        panel.add(exitButton);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
