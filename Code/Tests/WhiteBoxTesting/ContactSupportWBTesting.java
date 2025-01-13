package WhiteBoxTesting;
import emailServices.EmailService;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import UserInterfaces.ContactSupportUI;
import UserInterfaces.DashboardUI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
public class ContactSupportWBTesting {
    @Test
    public void testLogIssueToFile() {
        // Tests issue logging functionality.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        ContactSupportUI supportUI = new ContactSupportUI(user, loginSystem);
        String issue = "This is a test issue for logging.";

        supportUI.logAndEmailIssue(issue);

        boolean issueLogged = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("support_issues.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(issue)) {
                    issueLogged = true;
                    break;
                }
            }
        } catch (IOException e) {
            fail("Error reading the log file.");
        }

        assertTrue(issueLogged, "The issue should be logged to the file.");
    }

    @Test
    public void testSendEmailLogic() {
        // Validates email sending logic.
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

    @Test
    public void testUIOpensDashboardAfterSubmission() {
        // Ensures dashboard opens after issue submission.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");

        assertDoesNotThrow(() -> {
            new ContactSupportUI(user, loginSystem);
            new DashboardUI(user, loginSystem);
        }, "Navigating to the dashboard after submission should not throw exceptions.");
    }
}