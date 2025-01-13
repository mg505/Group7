package BlackBoxTesting;

import emailServices.EmailService;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EmailServiceBBTesting {

    @Test
    public void testGenerateTicketConfirmationMessage() {
        // Tests that the ticket confirmation message is generated correctly.
        Map<Integer, String[]> tickets = new HashMap<>();
        tickets.put(1, new String[]{"Route A", "10:00 AM", "50.0"});
        tickets.put(2, new String[]{"Route B", "2:00 PM", "30.0"});

        double totalCost = 80.0;
        EmailService emailService = new EmailService();

        String emailBody = emailService.generateTicketConfirmationMessage(tickets, totalCost);

        assertTrue(emailBody.contains("Ticket ID: 1"), "Email body should include Ticket ID 1.");
        assertTrue(emailBody.contains("Route: Route A"), "Email body should include Route A.");
        assertTrue(emailBody.contains("Price: £50.0"), "Email body should include price £50.0.");
        assertTrue(emailBody.contains("Total Cost: £80.0"), "Email body should include total cost £80.0.");
    }

    @Test
    public void testSendEmail() {
        // Tests that sending an email does not throw exceptions.
        String recipientEmail = "mg505@student.le.ac.uk";
        String subject = "Test Email";
        String messageBody = "This is a test email.";

        EmailService emailService = new EmailService();

        assertDoesNotThrow(() -> emailService.sendEmail(recipientEmail, subject, messageBody),
                "Sending email should not throw any exception.");
    }
}