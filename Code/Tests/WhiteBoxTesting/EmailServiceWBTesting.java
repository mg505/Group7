package WhiteBoxTesting;

import emailServices.EmailService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmailServiceWBTesting {

    @Test
    public void testGenerateTicketConfirmationMessageInternalLogic() {
        // Validates email generation structure and content.
        Map<Integer, String[]> tickets = new HashMap<>();
        tickets.put(1, new String[]{"Route A", "10:00 AM", "50.0"});
        tickets.put(2, new String[]{"Route B", "2:00 PM", "30.0"});
        double totalCost = 80.0;

        EmailService emailService = new EmailService();
        String emailBody = emailService.generateTicketConfirmationMessage(tickets, totalCost);

        assertTrue(emailBody.startsWith("Dear Customer,"));
        assertTrue(emailBody.contains("Ticket ID: 1"));
        assertTrue(emailBody.contains("Route: Route A"));
        assertTrue(emailBody.contains("Total Cost: £80.0"));
        assertTrue(emailBody.endsWith("Train Booking System Team"));
    }

    @Test
    public void testSendEmailWithValidCredentials() {
        // Ensures no exceptions are thrown for valid email details.
        String recipientEmail = "test@example.com";
        String subject = "Test Email";
        String messageBody = "This is a test email.";

        EmailService emailService = new EmailService();

        assertDoesNotThrow(() -> emailService.sendEmail(recipientEmail, subject, messageBody));
    }

    @Test
    public void testSendEmailWithInvalidRecipient() {
        // Verifies exception handling for invalid email recipients.
        String recipientEmail = "invalid-email";
        String subject = "Test Email";
        String messageBody = "This is a test email.";

        EmailService emailService = new EmailService();

        assertThrows(Exception.class, () -> emailService.sendEmail(recipientEmail, subject, messageBody));
    }
}