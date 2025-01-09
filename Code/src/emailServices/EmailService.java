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

    // Email and app password
    private static final String senderEmail = "mirandazoegriffith@gmail.com"; 
    private static final String senderPassword = "ltnd cknt kows upyy"; 


    //Sends an email to a recipient with a specific subject and body message.
    public void sendEmail(String recipientEmail, String subject, String messageBody) throws MessagingException {
        // Set up email server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");  
        properties.put("mail.smtp.auth", "true"); 
        properties.put("mail.smtp.ssl.enable", "true"); 
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");  
        properties.put("mail.debug", "true");  

        // Create a session using properties and authenticate with email/password
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Make sure a valid email address has been given
            InternetAddress recipientAddress = new InternetAddress(recipientEmail);
            recipientAddress.validate(); 

            // Creates a MimeMessage object and set relevant information for the email to be sent
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));  
            message.setRecipient(Message.RecipientType.TO, recipientAddress); 
            message.setSubject(subject);  
            message.setText(messageBody);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);

        } catch (AddressException e) {
            // Handle invalid email address format
            System.err.println("Invalid email address: " + recipientEmail);
            throw e;  // Rethrow exception allowing it to be handled by the calling code
        } catch (MessagingException e) {
            // Handle issues with sending the email
            System.err.println("Error occurred while sending email.");
            throw e;  // Rethrow exception allowing it to be handled by the calling code
        }
    }

   
    //Generates a ticket confirmation email body for the customer
    public String generateTicketConfirmationMessage(Map<Integer, String[]> tickets, double totalCost) {
        StringBuilder message = new StringBuilder();
        
        // Basic email greeting
        message.append("Dear Customer,\n\n")
               .append("Thank you for booking tickets with us! Here are your booking details:\n\n");

        // Loop through each ticket and add its details to the message
        for (Map.Entry<Integer, String[]> entry : tickets.entrySet()) {
            Integer ticketId = entry.getKey();  // Ticket ID
            String[] details = entry.getValue();  // Route, Time, Price

            // Add ticket details to the message
            message.append("Ticket ID: ").append(ticketId).append("\n")
                   .append("Route: ").append(details[0]).append("\n")
                   .append("Time: ").append(details[1]).append("\n")
                   .append("Price: £").append(details[2]).append("\n")
                   .append("---------------------------------\n");
        }

        // Add the total cost and closing message
        message.append("\nTotal Cost: £").append(totalCost).append("\n")
               .append("We hope you have an enjoyable and safe journey!\n\n")
               .append("Kind regards,\nTrain Booking System Team");

        // Return the formatted email body
        return message.toString();
    }
}
