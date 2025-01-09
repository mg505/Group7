package BlackBoxTesting;
import UserInterfaces.WelcomePage;
import login.LoginSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WelcomePageBBTesting {

    @Test
    public void testWelcomePageCreation() {
        // Setup: Create a LoginSystem instance
        LoginSystem loginSystem = new LoginSystem();

        // Verify that the WelcomePage UI is created without exceptions
        assertDoesNotThrow(() -> new WelcomePage(loginSystem), "WelcomePage creation should not throw exceptions.");
    }

    @Test
    public void testOpenLogInUI() {
        // Setup: Create a LoginSystem instance
        LoginSystem loginSystem = new LoginSystem();
        WelcomePage welcomePage = new WelcomePage(loginSystem);

        // Verify that opening LogInUI does not throw exceptions
        assertDoesNotThrow(welcomePage::openLogInUI, "Opening LogInUI should not throw exceptions.");
    }

    @Test
    public void testOpenSignUpUI() {
        // Setup: Create a LoginSystem instance
        LoginSystem loginSystem = new LoginSystem();
        WelcomePage welcomePage = new WelcomePage(loginSystem);

        // Verify that opening SignUpUI does not throw exceptions
        assertDoesNotThrow(() -> {
            welcomePage.openSignUpUI();
        }, "Opening SignUpUI should not throw exceptions.");
    }
}
