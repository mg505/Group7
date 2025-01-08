package UserInterfaces;

import login.User;
import login.LoginSystem;

import javax.swing.*;
import java.awt.*;

public class UserProfileUI {

    private User loggedInUser;
    private JFrame frame;
    private LoginSystem loginSystem;

    public UserProfileUI(User loggedInUser, LoginSystem loginSystem) {
        this.loggedInUser = loggedInUser;
        this.loginSystem = loginSystem;
        createUserProfileUI();
    }

    public void createUserProfileUI() {
        frame = new JFrame("User Profile - " + loggedInUser.getUsername());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400); // Increased size to fit new components
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel infoPanel = new JPanel(new GridLayout(5, 1, 5, 5)); // Use GridLayout for info
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel usernameLabel = new JLabel("Username: " + loggedInUser.getUsername());
        JLabel passwordLabel = new JLabel("Password: ********"); // Mask the password
        JLabel currentTicketsLabel = new JLabel("Current Tickets: " + loggedInUser.getNumberOfCurrentTickets());
        JLabel expiredTicketsLabel = new JLabel("Expired Tickets: " + loggedInUser.getNumberOfExpiredTickets());

        infoPanel.add(usernameLabel);
        infoPanel.add(passwordLabel);
        infoPanel.add(currentTicketsLabel);
        infoPanel.add(expiredTicketsLabel);

        // Panel for edit and logout buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.addActionListener(e -> openEditProfileUI());

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout()); // Logout logic here

        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> openDashboardUI());

        buttonPanel.add(editProfileButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(homeButton);

        frame.add(infoPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void openEditProfileUI() {
        // Create a new frame for editing profile
        JFrame editProfileFrame = new JFrame("Edit Profile");
        editProfileFrame.setSize(400, 300);
        editProfileFrame.setLocationRelativeTo(frame);
        editProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editProfileFrame.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel usernameLabel = new JLabel("New Username:");
        JTextField usernameField = new JTextField(loggedInUser.getUsername());
        JLabel passwordLabel = new JLabel("New Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> handleSaveChanges(usernameField.getText(), new String(passwordField.getPassword())));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> editProfileFrame.dispose());

        editProfileFrame.add(usernameLabel);
        editProfileFrame.add(usernameField);
        editProfileFrame.add(passwordLabel);
        editProfileFrame.add(passwordField);
        editProfileFrame.add(saveButton);
        editProfileFrame.add(cancelButton);

        editProfileFrame.setVisible(true);
    }

    private void handleSaveChanges(String newUsername, String newPassword) {
        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Username and password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the new username is already taken
        if (loginSystem.isUsernameTaken(newUsername) && !newUsername.equals(loggedInUser.getUsername())) {
            JOptionPane.showMessageDialog(frame, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update the user details
        loggedInUser.setUsername(newUsername);
        loggedInUser.setPassword(newPassword); // You should hash the password in a real application
        JOptionPane.showMessageDialog(frame, "Profile updated successfully!");

        // Close the edit profile frame
        frame.dispose();
        new UserProfileUI(loggedInUser, loginSystem); // Reopen the updated profile
    }

    private void logout() {
        // Perform logout logic
        JOptionPane.showMessageDialog(frame, "Goodbye, " + loggedInUser.getUsername());
        loginSystem.logout();
        frame.dispose();
        new WelcomePage(loginSystem); // Assuming you have a WelcomePage to show after logout
    }

    private void openDashboardUI() {
        frame.dispose();
        new DashboardUI(loggedInUser, loginSystem); // Open the user's dashboard
    }

    private void deleteAccount() {
        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete your account?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            loginSystem.removeUser(loggedInUser);
            JOptionPane.showMessageDialog(frame, "Your account has been deleted.");
            frame.dispose();
            new WelcomePage(loginSystem); // Redirect to the welcome page after deletion
        }
    }
}
