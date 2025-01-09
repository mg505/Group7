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
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Simulate logging an issue
	        ContactSupportUI supportUI = new ContactSupportUI(user, loginSystem);
	        String issue = "This is a test issue for logging.";

	        supportUI.logAndEmailIssue(issue);

	        // Verify the issue was logged to the file
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

	    @Test
	    public void testUIOpensDashboardAfterSubmission() {
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Simulate navigating back to the dashboard
	        assertDoesNotThrow(() -> {
	            new ContactSupportUI(user, loginSystem);
	            new DashboardUI(user, loginSystem);
	        }, "Navigating to the dashboard after submission should not throw exceptions.");
	    }

}
