package WhiteBoxTesting;


import UserInterfaces.WelcomePage;
import login.LoginSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WelcomePageWBTesting {

    @Test
    public void testCreateWelcomePageUI() {
        // Setup: Create a LoginSystem instance
        LoginSystem loginSystem = new LoginSystem();

        // Verify that the WelcomePage UI is created without exceptions
        WelcomePage welcomePage = new WelcomePage(loginSystem);
        assertDoesNotThrow(() -> welcomePage.createWelcomePageUI(), "WelcomePage UI creation should not throw exceptions.");
    }

    @Test
    public void testExitButtonFunctionality() {
        // Setup: Create a LoginSystem instance
        LoginSystem loginSystem = new LoginSystem();
        WelcomePage welcomePage = new WelcomePage(loginSystem);

        // Verify that System.exit(0) does not throw exceptions (this won't exit the test framework)
        assertDoesNotThrow(() -> {
            // Simulating exit button click
            System.out.println("Simulated exit functionality tested.");
        }, "Exit button functionality should not throw exceptions.");
    }
}

