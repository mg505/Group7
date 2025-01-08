package UserInterfaces;

import login.LoginSystem;
import javax.swing.*;
import java.awt.*;

public class WelcomePage {

    private LoginSystem logInSystem;
    private JFrame frame;

    public WelcomePage(LoginSystem logInSystem) {
        this.logInSystem = logInSystem;
        createWelcomePageUI();
    }

    private void createWelcomePageUI() {
        frame = new JFrame("Train Ticket Service");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel welcomeLabel = new JLabel("Welcome to the Train Ticket Service!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        JLabel descriptionLabel = new JLabel("Please choose an option to proceed:");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(descriptionLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(Box.createVerticalStrut(50));

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setPreferredSize(new Dimension(250, 50));
        loginButton.addActionListener(e -> openLogInUI());
        buttonPanel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        signUpButton.setPreferredSize(new Dimension(250, 50));
        signUpButton.addActionListener(e -> openSignUpUI());
        buttonPanel.add(signUpButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        JPanel exitPanel = new JPanel();
        exitPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        exitPanel.setBackground(Color.WHITE);

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setPreferredSize(new Dimension(100, 40));
        exitButton.addActionListener(e -> System.exit(0));
        exitPanel.add(exitButton);

        panel.add(exitPanel, BorderLayout.SOUTH);

        frame.add(panel);
        frame.setVisible(true);
    }

    public void openLogInUI() {
        frame.dispose();
        new LogInUI(logInSystem);
    }

    private void openSignUpUI() {
        frame.dispose();
        new SignUpUI(logInSystem);
    }

    public static void main(String[] args) {
        LoginSystem loginSystem = new LoginSystem();
        new WelcomePage(loginSystem);
    }
}