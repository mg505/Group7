package UserInterfaces;

import login.LoginSystem;
import login.User;

import javax.swing.*;
import java.awt.*;

public class LogInUI {

    private LoginSystem logInSystem;
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LogInUI(LoginSystem logInSystem) { // Corrected Constructor
        this.logInSystem = logInSystem;
        initializeUI();
    }

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
        loginButton.addActionListener(e -> handleLogin());

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> openWelcomePage());

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(loginButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User loggedInUser = logInSystem.validateLogin(username, password);
        if (loggedInUser != null) {
            JOptionPane.showMessageDialog(frame, "Login successful!");
            openDashboardUI(loggedInUser);
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openWelcomePage() {
        frame.dispose();
        new WelcomePage(logInSystem);
    }

    private void openDashboardUI(User loggedInUser) {
        frame.dispose();
        new DashboardUI(loggedInUser, logInSystem);
    }
}