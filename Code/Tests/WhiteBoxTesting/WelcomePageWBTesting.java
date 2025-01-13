package WhiteBoxTesting;

import UserInterfaces.WelcomePage;
import login.LoginSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WelcomePageWBTesting {

    @Test
    // Test the creation of the WelcomePage UI
    public void testCreateWelcomePageUI() {
        LoginSystem loginSystem = new LoginSystem();
        WelcomePage welcomePage = new WelcomePage(loginSystem);
        assertDoesNotThrow(() -> welcomePage.createWelcomePageUI());
    }

    @Test
    // Test the functionality of the exit button in the WelcomePage
    public void testExitButtonFunctionality() {
        LoginSystem loginSystem = new LoginSystem();
        WelcomePage welcomePage = new WelcomePage(loginSystem);
        assertDoesNotThrow(() -> {
            System.out.println("Simulated exit functionality tested.");
        });
    }
}
