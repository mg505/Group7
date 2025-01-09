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
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate entering and submitting a valid issue
        assertDoesNotThrow(() -> {
            ContactSupportUI supportUI = new ContactSupportUI(user, loginSystem);
            supportUI.logAndEmailIssue("This is a test issue.");
        }, "Submitting a valid issue should not throw exceptions.");
    }

    @Test
    public void testSubmitEmptyIssue() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate submitting an empty issue
        assertDoesNotThrow(() -> {
            ContactSupportUI supportUI = new ContactSupportUI(user, loginSystem);
            supportUI.logAndEmailIssue(""); // Simulate empty input
        }, "Submitting an empty issue should not throw exceptions.");
    }

    @Test
    public void testEmailSentSuccessfully() {
        // Setup: Create a LoginSystem and User
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        // Simulate sending an email
        EmailService emailService = new EmailService();
        String recipientEmail = "mg505@student.le.ac.uk";
        String subject = "Support Request from User: " + user.getUsername();
        String emailBody = "This is a test issue.";

        assertDoesNotThrow(() -> {
            emailService.sendEmail(recipientEmail, subject, emailBody);
        }, "Sending an email should not throw exceptions.");
    }

}
