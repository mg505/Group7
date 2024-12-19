package UserInterfaces;

import login.LoginSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpUI {

    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton signUpButton;
    private JButton backButton;  // Changed button name to 'Back'
    private LoginSystem logInSystem;

    public SignUpUI(LoginSystem logInSystem) {
        this.logInSystem = logInSystem;
        initializeUI();
    }

    // Initialize the UI components
    private void initializeUI() {
        // Create the frame
        frame = new JFrame("Sign Up");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        
        signUpButton = new JButton("Sign Up");
        backButton = new JButton("Back");  // Changed button name to 'Back'

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUp();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWelcomePage();  // Navigate back to the WelcomePage
            }
        });

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(signUpButton);
        frame.add(backButton);

        // Make the frame visible
        frame.setVisible(true);
    }

    // Handle the sign-up action
    private void handleSignUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both username and password.");
            return;
        }

        // Register the user using the LogInSystem class
        logInSystem.registerUser(username, password);
        JOptionPane.showMessageDialog(frame, "Registration successful! Redirecting to Dashboard.");

        openDashboardUI(); // After successful registration, show the dashboard panel
    }

    // Open the Dashboard UI (instead of login UI)
    private void openDashboardUI() {
        frame.dispose(); // Close the sign-up window
        new DashboardUI(); // Open the Dashboard UI after registration
    }

    // Open the WelcomePage UI
    private void openWelcomePage() {
        frame.dispose(); // Close the sign-up window
        new WelcomePage(); // Open the WelcomePage UI
    }
}
