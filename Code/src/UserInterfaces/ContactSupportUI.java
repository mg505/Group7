package UserInterfaces;

import emailServices.EmailService;
import login.LoginSystem;
import login.User;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;

public class ContactSupportUI {

    private JFrame frame; 
    private User loggedInUser; 
    private LoginSystem loginSystem; 

    // Constructor initialises the UI with the logged-in user and login system info
    public ContactSupportUI(User user, LoginSystem loginSystem) {
        this.loggedInUser = user;
        this.loginSystem = loginSystem;
        createSupportUI(); 
    }

    // Creates the support UI to contact support and submit an issue
    private void createSupportUI() {
        // Create the main frame for the UI
        frame = new JFrame("Contact Support");
        frame.setSize(400, 300); 
        frame.setLocationRelativeTo(null); 
        frame.setLayout(new BorderLayout()); 

        // Instructions labels 
        JLabel instructions = new JLabel("Describe your issue below:");
        instructions.setHorizontalAlignment(SwingConstants.CENTER); 
        instructions.setFont(new Font("Arial", Font.BOLD, 14)); 
        frame.add(instructions, BorderLayout.NORTH); 

        // Area for the user to enter their issue
        JTextArea issueTextArea = new JTextArea();
        frame.add(new JScrollPane(issueTextArea), BorderLayout.CENTER); 

        // Submit button to submit the issue
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String issue = issueTextArea.getText().trim();
            // If the issue description is not empty, log and email the issue
            if (!issue.isEmpty()) {
                logAndEmailIssue(issue); 
                JOptionPane.showMessageDialog(frame, "Thank you! Your issue has been submitted.");
                frame.dispose(); 
             // Navigate back to the dashboard
                new DashboardUI(loggedInUser, loginSystem); 
            } else {
                JOptionPane.showMessageDialog(frame, "Please describe your issue before submitting.");
            }
        });

        
        frame.add(submitButton, BorderLayout.SOUTH);

        
        frame.setVisible(true);
    }

    // Log the issue to a file and send it via email
    public void logAndEmailIssue(String issue) {
        // Log the issue to a text file
        try (java.io.FileWriter writer = new java.io.FileWriter("support_issues.txt", true)) {
            writer.write("User: " + loggedInUser.getUsername() + "\n");
            writer.write("Issue: " + issue + "\n");
            writer.write("---------------------------------\n");
        } catch (java.io.IOException e) {
            e.printStackTrace(); // Handle file writing errors
        }

        // Send the issue via email to support
        EmailService emailService = new EmailService();
        String recipientEmail = "mg505@student.le.ac.uk"; 
        String subject = "Support Request from User: " + loggedInUser.getUsername();
        String emailBody = "User: " + loggedInUser.getUsername() + "\n\n" + "Issue:\n" + issue;

        try {
            emailService.sendEmail(recipientEmail, subject, emailBody); // Send email
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(frame, "Failed to send email to support. Please try again later.");
            e.printStackTrace(); 
        }
    }
}
