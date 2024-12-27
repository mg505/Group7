package WhiteBoxTesting;


import emailServices.EmailService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmailServiceWBTesting {

    @Test
    public void testGenerateTicketConfirmationMessageInternalLogic() {
        // Setup: Create test ticket data
        Map<Integer, String[]> tickets = new HashMap<>();
        tickets.put(1, new String[]{"Route A", "10:00 AM", "50.0"});
        tickets.put(2, new String[]{"Route B", "2:00 PM", "30.0"});

        double totalCost = 80.0;  // Expected total cost

        // Create an instance of EmailService
        EmailService emailService = new EmailService();

        // Generate the ticket confirmation message
        String emailBody = emailService.generateTicketConfirmationMessage(tickets, totalCost);

        // Validate the structure of the email body
        assertTrue(emailBody.startsWith("Dear Customer,"), "Email should start with a greeting.");
        assertTrue(emailBody.contains("Ticket ID: 1"), "Email should include details of Ticket ID 1.");
        assertTrue(emailBody.contains("Route: Route A"), "Email should include Route A.");
        assertTrue(emailBody.contains("Total Cost: £80.0"), "Email should include the total cost.");
        assertTrue(emailBody.endsWith("Train Booking System Team"), "Email should end with the team signature.");
    }

    @Test
    public void testSendEmailWithValidCredentials() {
        // Setup: Prepare test email details
        String recipientEmail = "test@example.com";
        String subject = "Test Email";
        String messageBody = "This is a test email.";

        // Create an instance of EmailService
        EmailService emailService = new EmailService();

        // Test email sending logic
        assertDoesNotThrow(() -> emailService.sendEmail(recipientEmail, subject, messageBody),
                "Email sending logic should not throw exceptions with valid credentials.");
    }

    @Test
    public void testSendEmailWithInvalidRecipient() {
        // Setup: Prepare invalid recipient email
        String recipientEmail = "invalid-email";  // Invalid email format
        String subject = "Test Email";
        String messageBody = "This is a test email.";

        // Create an instance of EmailService
        EmailService emailService = new EmailService();

        // Expect an exception due to invalid recipient email
        assertThrows(Exception.class, () -> emailService.sendEmail(recipientEmail, subject, messageBody),
                "Sending email to an invalid recipient should throw an exception.");
    }
}
