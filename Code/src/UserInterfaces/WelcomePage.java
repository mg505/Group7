package UserInterfaces;

import login.LoginSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePage {

    private LoginSystem logInSystem;  // Instance of LoginSystem for use in UIs

    public WelcomePage() {
        this.logInSystem = new LoginSystem(); // Initialize the LoginSystem
        createWelcomePageUI();
    }

    // Create the frame for the Welcome Page
    private void createWelcomePageUI() {
        JFrame frame = new JFrame("Train Ticket Service");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);  // Increased size for a bigger screen
        frame.setLocationRelativeTo(null); // Center the frame
        frame.getContentPane().setBackground(Color.WHITE); // Set white background

        // Create a panel to hold the components (using BorderLayout)
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());  // Use BorderLayout for more control
        panel.setBackground(Color.WHITE); // Set the panel background to white

        // Create a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Train Ticket Service!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));  // Larger font size
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the label
        panel.add(welcomeLabel, BorderLayout.NORTH); // Add to the top

        // Create a description label
        JLabel descriptionLabel = new JLabel("Please choose an option to proceed:");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the label
        panel.add(descriptionLabel, BorderLayout.CENTER); // Add to the center

        // Create a panel for the buttons (to manage layout)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center buttons horizontally
        buttonPanel.setBackground(Color.WHITE); // Set the background to white

        // Add vertical space before the buttons
        buttonPanel.add(Box.createVerticalStrut(50));  // Adds vertical space before the buttons

        // Create a button for Login (bigger size)
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));  // Bigger font for the button
        loginButton.setPreferredSize(new Dimension(250, 50)); // Bigger button size
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLogInUI();  // Open Log In UI
            }
        });
        buttonPanel.add(loginButton);

        // Create a button for Sign Up (bigger size)
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));  // Bigger font for the button
        signUpButton.setPreferredSize(new Dimension(250, 50)); // Bigger button size
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUpUI();  // Open Sign Up UI
            }
        });
        buttonPanel.add(signUpButton);

        // Add the button panel to the center of the frame
        panel.add(buttonPanel, BorderLayout.CENTER);

        // Create a panel for the Exit button (to position it at the bottom-left)
        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Align the exit button to the left
        exitPanel.setBackground(Color.WHITE); // Ensure the background is white

        // Create a button to exit the application
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));  // Font for the exit button
        exitButton.setPreferredSize(new Dimension(100, 40)); // Button size
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });
        exitPanel.add(exitButton);

        // Add the exit panel to the bottom of the frame
        panel.add(exitPanel, BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Open the Log In UI
    private void openLogInUI() {
        // Create the LogInUI instance and open it
        JFrame frame = new JFrame("Log In");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        // Dispose of the current Welcome Page frame
        Window window = SwingUtilities.windowForComponent((Component) null);
        if (window != null) {
            window.dispose();
        }
        new LogInUI(logInSystem); // Open the LogInUI
    }

    // Open the Sign Up UI
    private void openSignUpUI() {
        // Create the SignUpUI instance and open it
        JFrame frame = new JFrame("Sign Up");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        // Dispose of the current Welcome Page frame
        Window window = SwingUtilities.windowForComponent((Component) null);
        if (window != null) {
            window.dispose();
        }
        new SignUpUI(logInSystem); // Open the SignUpUI
    }

    // Main method to launch the WelcomePage UI
    public static void main(String[] args) {
        // Create an instance of WelcomePage to start the UI
        new WelcomePage();
    }
}
