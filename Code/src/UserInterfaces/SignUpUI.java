package UserInterfaces;

import login.LoginSystem;
import login.User;

import javax.swing.*;
import java.awt.*;

public class SignUpUI {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginSystem logInSystem;

    // Constructor to initialise the SignUpUI 
    public SignUpUI(LoginSystem logInSystem) {
        this.logInSystem = logInSystem;
        initialiseUI();
    }

    // Initialises SignUp UI
    private void initialiseUI() {
        frame = new JFrame("Sign Up");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 2, 10, 10)); 

        // Setting up form labels and input fields
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        // Buttons for submitting the form and navigating back
        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        // Action listener for sign-up button
        signUpButton.addActionListener(e -> handleSignUp());
        // Action listener for back button
        backButton.addActionListener(e -> openWelcomePage());

        // Adding components to the frame
        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(signUpButton);
        frame.add(backButton);

        frame.setVisible(true); 
    }

    // Handles the sign-up logic
    private void handleSignUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // Validate that username and password are not empty
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Register the user and move to the dashboard if successful
            User newUser = logInSystem.registerUser(username, password);
            JOptionPane.showMessageDialog(frame, "Registration successful!");
            openDashboardUI(newUser);
        } catch (IllegalArgumentException ex) {
            
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Opens the user dashboard after successful registration
    private void openDashboardUI(User newUser) {
        frame.dispose();  
        new DashboardUI(newUser, logInSystem);  
    }

    // Navigates back to the Welcome Page
    private void openWelcomePage() {
        frame.dispose();  
        new WelcomePage(logInSystem);  
    }
}
