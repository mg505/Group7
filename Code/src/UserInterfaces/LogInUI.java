package UserInterfaces;

import login.LoginSystem;
import login.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInUI {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;  // Changed from registerButton to backButton
    private LoginSystem logInSystem;

    public LogInUI(LoginSystem logInSystem) {
        this.logInSystem = logInSystem;
        initializeUI();
    }

    // Initialize the UI components
    private void initializeUI() {
        // Create the frame
        frame = new JFrame("Log In");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        
        loginButton = new JButton("Log In");
        backButton = new JButton("Back");  // Button to go back to the welcome page

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWelcomePage();  // Go back to the welcome page
            }
        });

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(backButton);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Handle the login action
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both username and password.");
            return;
        }

        // Validate credentials using the LogInSystem class
        if (logInSystem.validateLogin(username, password)) {
            JOptionPane.showMessageDialog(frame, "Log In successful!");
            openMainDashboard();
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials. Please try again.");
        }
    }

    // Open the main dashboard after successful login
    private void openMainDashboard() {
        frame.dispose(); // Close the login window

        // Here you can open the main dashboard or other UI
        // For demo purposes, we are just showing a simple message
        JOptionPane.showMessageDialog(null, "Welcome to the Dashboard!");
    }

    // Open the Welcome Page
    private void openWelcomePage() {
        frame.dispose(); // Close the login window
        new WelcomePage();  // Open the welcome page UI
    }

    public static void main(String[] args) {
        LoginSystem logInSystem = new LoginSystem();
        new LogInUI(logInSystem);  // Launch the Log In UI with the backend authentication
    }
}
