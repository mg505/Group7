package BlackBoxTesting;

import emailServices.EmailService;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import UserInterfaces.ContactSupportUI;

import static org.junit.jupiter.api.Assertions.*;

public class ContactSupportBBTesting {

    @Test
    public void testSubmitValidIssue() {
        // Tests  submitting a valid issue does not throw exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            ContactSupportUI supportUI = new ContactSupportUI(user, loginSystem);
            supportUI.logAndEmailIssue("This is a test issue.");
        }, "Submitting a valid issue should not throw exceptions.");
    }

    @Test
    public void testSubmitEmptyIssue() {
        // Tests  submitting an empty issue does not throw exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            ContactSupportUI supportUI = new ContactSupportUI(user, loginSystem);
            supportUI.logAndEmailIssue("");
        }, "Submitting an empty issue should not throw exceptions.");
    }

    @Test
    public void testEmailSentSuccessfully() {
        // Tests  sending an email for customer support does not throw exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        EmailService emailService = new EmailService();
        String recipientEmail = "mg505@student.le.ac.uk";
        String subject = "Support Request from User: " + user.getUsername();
        String emailBody = "This is a test issue.";

        assertDoesNotThrow(() -> {
            emailService.sendEmail(recipientEmail, subject, emailBody);
        }, "Sending an email should not throw exceptions.");
    }
}