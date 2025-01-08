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

    public SignUpUI(LoginSystem logInSystem) {
        this.logInSystem = logInSystem;
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Sign Up");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton signUpButton = new JButton("Sign Up");
        JButton backButton = new JButton("Back");

        signUpButton.addActionListener(e -> handleSignUp());
        backButton.addActionListener(e -> openWelcomePage());

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(signUpButton);
        frame.add(backButton);

        frame.setVisible(true);
    }

    private void handleSignUp() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter both username and password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            User newUser = logInSystem.registerUser(username, password);
            JOptionPane.showMessageDialog(frame, "Registration successful!");
            openDashboardUI(newUser);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openDashboardUI(User newUser) {
        frame.dispose();
        new DashboardUI(newUser, logInSystem);
    }

    private void openWelcomePage() {
        frame.dispose();
        new WelcomePage(logInSystem);
    }
}