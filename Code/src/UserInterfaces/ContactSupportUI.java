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

    // Modify constructor to accept LoginSystem
    public ContactSupportUI(User user, LoginSystem loginSystem) {
        this.loggedInUser = user;
        this.loginSystem = loginSystem;
        createSupportUI();
    }

    private void createSupportUI() {
        // Create and configure the main frame
        frame = new JFrame("Contact Support");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel instructions = new JLabel("Describe your issue below:");
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(instructions, BorderLayout.NORTH);

        // Text area for entering issue description
        JTextArea issueTextArea = new JTextArea();
        frame.add(new JScrollPane(issueTextArea), BorderLayout.CENTER);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String issue = issueTextArea.getText().trim();
            if (!issue.isEmpty()) {
                logAndEmailIssue(issue);
                JOptionPane.showMessageDialog(frame, "Thank you! Your issue has been submitted.");
                frame.dispose();
                new DashboardUI(loggedInUser, loginSystem); // Navigate back to the user's dashboard
            } else {
                JOptionPane.showMessageDialog(frame, "Please describe your issue before submitting.");
            }
        });

        frame.add(submitButton, BorderLayout.SOUTH);
        frame.setVisible(true);  // Make the frame visible
    }

    private void logAndEmailIssue(String issue) {
        // Log the issue to a file
        try (java.io.FileWriter writer = new java.io.FileWriter("support_issues.txt", true)) {
            writer.write("User: " + loggedInUser.getUsername() + "\n");
            writer.write("Issue: " + issue + "\n");
            writer.write("---------------------------------\n");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }

        // Send the issue via email
        EmailService emailService = new EmailService();
        String recipientEmail = "mg505@student.le.ac.uk"; // Replace with your support email
        String subject = "Support Request from User: " + loggedInUser.getUsername();
        String emailBody = "User: " + loggedInUser.getUsername() + "\n\n" + "Issue:\n" + issue;

        try {
            emailService.sendEmail(recipientEmail, subject, emailBody);
        } catch (MessagingException e) {
            JOptionPane.showMessageDialog(frame, "Failed to send email to support. Please try again later.");
            e.printStackTrace();
        }
    }
}
