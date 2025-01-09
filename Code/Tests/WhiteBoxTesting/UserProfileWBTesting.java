package WhiteBoxTesting;


import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import UserInterfaces.DashboardUI;

import static org.junit.jupiter.api.Assertions.*;

public class UserProfileWBTesting {

	 @Test
	    public void testHandleSaveChangesLogic() {
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Simulate editing the profile
	        String newUsername = "newusername";
	        String newPassword = "newpassword";

	        // Test: Update username and password
	        if (newUsername.isEmpty() || newPassword.isEmpty()) {
	            fail("Username and password cannot be empty.");
	        }
	        if (loginSystem.isUsernameTaken(newUsername) && !newUsername.equals(user.getUsername())) {
	            fail("Username already exists.");
	        }

	        user.setUsername(newUsername);
	        user.setPassword(newPassword);

	        // Validate
	        assertEquals("newusername", user.getUsername(), "Username should be updated.");
	        assertEquals("newpassword", user.getPassword(), "Password should be updated.");
	    }

	    @Test
	    public void testLogoutLogic() {
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Test: Logout logic
	        loginSystem.logout();

	        // Validate
	        assertNull(LoginSystem.getCurrentUser(), "Current user should be null after logout.");
	    }

	    @Test
	    public void testDeleteAccountLogic() {
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Test: Remove the user
	        loginSystem.removeUser(user);

	        // Validate: User is removed from the system
	        assertFalse(loginSystem.getAllUsers().contains(user), "Deleted user should not exist in the system.");
	    }

	    @Test
	    public void testRetainUserInfoAfterEdit() {
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Add tickets
	        user.addCurrentTicket("Ticket A");
	        user.addExpiredTicket("Ticket B");

	        // Test: Edit username and password
	        user.setUsername("newusername");
	        user.setPassword("newpassword");

	        // Validate: Tickets are retained
	        assertTrue(user.getCurrentTickets().contains("Ticket A"), "Current tickets should be retained.");
	        assertTrue(user.getExpiredTickets().contains("Ticket B"), "Expired tickets should be retained.");
	    }

	    @Test
	    public void testOpenDashboardUILogic() {
	        // Setup: Create a LoginSystem and User
	        LoginSystem loginSystem = new LoginSystem();
	        User user = loginSystem.registerUser("testuser", "password123");

	        // Simulate opening the dashboard
	        assertDoesNotThrow(() -> new DashboardUI(user, loginSystem),
	                "Opening the dashboard should not throw exceptions.");
	    }
	}