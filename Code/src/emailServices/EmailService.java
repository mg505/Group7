package emailServices;

import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

    private static final String senderEmail = "mirandazoegriffith@gmail.com"; 
    private static final String senderPassword = "ltnd cknt kows upyy"; 

    public void sendEmail(String recipientEmail, String subject, String messageBody) throws MessagingException {
        // Set up email server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");  // Use port 465 for SSL
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");  // Enable SSL
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");  // Trust the server's certificate
        properties.put("mail.debug", "true");  // Enable debugging

        // Create a session with Gmail using the email and app password
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Validate recipient email address
            InternetAddress recipientAddress = new InternetAddress(recipientEmail);
            recipientAddress.validate(); // Throws AddressException if invalid

            // Creating the actual email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipient(Message.RecipientType.TO, recipientAddress);
            message.setSubject(subject);
            message.setText(messageBody);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);

        } catch (AddressException e) {
            System.err.println("Invalid email address: " + recipientEmail);
            throw e; // Rethrow the exception for the test to catch
        } catch (MessagingException e) {
            System.err.println("Error occurred while sending email.");
            throw e; // Rethrow other messaging exceptions for the test to catch
        }
    }

    public String generateTicketConfirmationMessage(Map<Integer, String[]> tickets, double totalCost) {
        StringBuilder message = new StringBuilder();
        message.append("Dear Customer,\n\n")
               .append("Thank you for booking tickets with us! Here are your booking details:\n\n");

        for (Map.Entry<Integer, String[]> entry : tickets.entrySet()) {
            Integer ticketId = entry.getKey();
            String[] details = entry.getValue();
            message.append("Ticket ID: ").append(ticketId).append("\n")
                   .append("Route: ").append(details[0]).append("\n")
                   .append("Time: ").append(details[1]).append("\n")
                   .append("Price: £").append(details[2]).append("\n")
                   .append("---------------------------------\n");
        }

        message.append("\nTotal Cost: £").append(totalCost).append("\n")
               .append("We hope you have a pleasant and safe journey!\n\n")
               .append("Best regards,\nTrain Booking System Team");

        return message.toString();
    }
}
