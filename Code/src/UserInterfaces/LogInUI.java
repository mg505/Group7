package UserInterfaces;

import login.LoginSystem;
import login.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogInUI {

    private LoginSystem logInSystem;  // Instance of LoginSystem
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    // Constructor to accept LoginSystem instance
    public LogInUI(LoginSystem logInSystem) {
        this.logInSystem = logInSystem;  // Initialize LoginSystem
        initializeUI();
    }

    // Initialize the Login UI components
    private void initializeUI() {
        frame = new JFrame("Login");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        JButton backButton = new JButton("Back");
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
        frame.add(loginButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    // Handle the login action
    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User loggedInUser = logInSystem.validateLogin(username, password);
        if (loggedInUser != null) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
            openDashboardUI(loggedInUser);  // Pass the logged-in user
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid credentials, please try again.");
        }
    }

    // Open the WelcomePage UI
    private void openWelcomePage() {
        frame.dispose();  // Close the Login UI window
        new WelcomePage();  // Open the WelcomePage UI
    }

    // Open the Dashboard UI
    private void openDashboardUI(User loggedInUser) {
        frame.dispose();  // Close the Login UI window
        new DashboardUI(loggedInUser);  // Pass the logged-in user to DashboardUI
    }
}
