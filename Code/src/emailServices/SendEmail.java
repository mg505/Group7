package emailServices;

import java.util.HashMap;
import java.util.Map;

public class SendEmail {

    public static void main(String[] args) {
        // Initialize the EmailService
        EmailService emailService = new EmailService();

        // Test Data
        String recipientEmail = "miranda@mirandagriffith.com";  // Replace with your test email
        String subject = "Train Ticket Confirmation";

        // Simulate ticket details
        Map<Integer, String[]> tickets = new HashMap<>();
        tickets.put(1, new String[]{"Route A", "10:00 AM", "50.0"});
        tickets.put(2, new String[]{"Route B", "2:00 PM", "30.0"});

        // Calculate total cost
        double totalCost = tickets.values().stream()
                .mapToDouble(details -> Double.parseDouble(details[2]))
                .sum();

        // Generate email body
        String emailBody = emailService.generateTicketConfirmationMessage(tickets, totalCost);

        // Print email details for debugging
        System.out.println("Recipient Email: " + recipientEmail);
        System.out.println("Email Subject: " + subject);
        System.out.println("Email Body:\n" + emailBody);

        // Send the email
        try {
            emailService.sendEmail(recipientEmail, subject, emailBody);
            System.out.println("Test email sent successfully.");
        } catch (Exception e) {
            System.out.println("An error occurred while sending the email:");
            e.printStackTrace();
        }
    }
}
