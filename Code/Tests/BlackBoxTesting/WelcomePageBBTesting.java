package BlackBoxTesting;

import UserInterfaces.WelcomePage;
import login.LoginSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WelcomePageBBTesting {

    @Test
    // Tests creation of WelcomePage
    public void testWelcomePageCreation() {
        LoginSystem loginSystem = new LoginSystem();

        assertDoesNotThrow(() -> new WelcomePage(loginSystem));
    }

    @Test
    // Tests opening the LogInUI from WelcomePage
    public void testOpenLogInUI() {
        LoginSystem loginSystem = new LoginSystem();
        WelcomePage welcomePage = new WelcomePage(loginSystem);

        assertDoesNotThrow(welcomePage::openLogInUI);
    }

    @Test
    // Tests opening the SignUpUI from WelcomePage
    public void testOpenSignUpUI() {
        LoginSystem loginSystem = new LoginSystem();
        WelcomePage welcomePage = new WelcomePage(loginSystem);

        assertDoesNotThrow(() -> welcomePage.openSignUpUI());
    }
}
