package UserInterfaces;

import login.User;
import login.LoginSystem;

import javax.swing.*;
import java.awt.*;

public class UserProfileUI {

    private User loggedInUser;
    private JFrame frame;
    private LoginSystem loginSystem;

    // Constructor 
    public UserProfileUI(User loggedInUser, LoginSystem loginSystem) {
        this.loggedInUser = loggedInUser;
        this.loginSystem = loginSystem;
        createUserProfileUI();  // Call method to create the profile UI
    }

    // Creates the layout User Profile UI
    public void createUserProfileUI() {
        frame = new JFrame("User Profile - " + loggedInUser.getUsername());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);  
        frame.setLayout(new BorderLayout());

        // Panel to display user information 
        JPanel infoPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Labels for displaying the user's details 
        JLabel usernameLabel = new JLabel("Username: " + loggedInUser.getUsername());
        JLabel passwordLabel = new JLabel("Password: ********"); 
        JLabel currentTicketsLabel = new JLabel("Current Tickets: " + loggedInUser.getNumberOfCurrentTickets());
        JLabel expiredTicketsLabel = new JLabel("Expired Tickets: " + loggedInUser.getNumberOfExpiredTickets());

        // Add the labels to the info panel
        infoPanel.add(usernameLabel);
        infoPanel.add(passwordLabel);
        infoPanel.add(currentTicketsLabel);
        infoPanel.add(expiredTicketsLabel);

        // Panel to hold buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));

        // Button to edit user profile
        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.addActionListener(e -> openEditProfileUI());

        // Button to logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> logout());

        // Button to return to the dashboard
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(e -> openDashboardUI());

        // Button to delete account
        JButton deleteAccountButton = new JButton("Delete Account");
        deleteAccountButton.addActionListener(e -> deleteAccount());

        // Add buttons to the panel
        buttonPanel.add(editProfileButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(homeButton);
        buttonPanel.add(deleteAccountButton);  

        // Add the panels to the frame
        frame.add(infoPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);  // Display the frame
    }

    // Opens the Edit Profile UI where users can change their details
    private void openEditProfileUI() {
        // Create a new frame for editing profile
        JFrame editProfileFrame = new JFrame("Edit Profile");
        editProfileFrame.setSize(400, 300);
        editProfileFrame.setLocationRelativeTo(frame);
        editProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editProfileFrame.setLayout(new GridLayout(4, 2, 10, 10));

        // Labels and fields to edit the username and password
        JLabel usernameLabel = new JLabel("New Username:");
        JTextField usernameField = new JTextField(loggedInUser.getUsername());
        JLabel passwordLabel = new JLabel("New Password:");
        JPasswordField passwordField = new JPasswordField();

        // Save button to apply changes
        JButton saveButton = new JButton("Save Changes");
        saveButton.addActionListener(e -> handleSaveChanges(usernameField.getText(), new String(passwordField.getPassword())));

        // Cancel button to close the edit profile frame
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> editProfileFrame.dispose());

        // Add the components to the edit profile frame
        editProfileFrame.add(usernameLabel);
        editProfileFrame.add(usernameField);
        editProfileFrame.add(passwordLabel);
        editProfileFrame.add(passwordField);
        editProfileFrame.add(saveButton);
        editProfileFrame.add(cancelButton);

        editProfileFrame.setVisible(true);  
    }

    // Handles saving changes to the username and password
    private void handleSaveChanges(String newUsername, String newPassword) {
        // Ensure neither the username nor password are empty
        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Username and password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the new username is already taken 
        if (loginSystem.isUsernameTaken(newUsername) && !newUsername.equals(loggedInUser.getUsername())) {
            JOptionPane.showMessageDialog(frame, "Username already exists.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update user details
        loggedInUser.setUsername(newUsername);
        loggedInUser.setPassword(newPassword);  
        JOptionPane.showMessageDialog(frame, "Profile updated successfully!");

        // Close the edit profile frame and reopen the updated profile
        frame.dispose();
        new UserProfileUI(loggedInUser, loginSystem);
    }

    // Logs the user out and redirects to the Welcome Page
    private void logout() {
        JOptionPane.showMessageDialog(frame, "Goodbye, " + loggedInUser.getUsername());
        loginSystem.logout();  
        frame.dispose();  
        new WelcomePage(loginSystem); 
    }

    // Opens the user's dashboard
    private void openDashboardUI() {
        frame.dispose();
        new DashboardUI(loggedInUser, loginSystem);  
    }

    // Makes sure the user is sure about deleting their account
    private void deleteAccount() {
        int confirm = JOptionPane.showConfirmDialog(frame, 
            "Are you sure you want to delete your account? This action cannot be undone.", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            
            loginSystem.removeUser(loggedInUser);
            JOptionPane.showMessageDialog(frame, "Your account has been deleted.");

           
            frame.dispose();
            new WelcomePage(loginSystem);
        }
    }
}
