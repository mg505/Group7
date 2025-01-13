package BlackBoxTesting;

import login.LoginSystem;
import login.User;
import viewTickets.viewTickets;
import PurchaseBasket.Basket;
import UserInterfaces.ViewTicketsUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ViewTicketsBBTesting {

    @Test
    // Tests initialization of ViewTicketsUI
    public void testViewTicketsInitialization() {
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();

        assertDoesNotThrow(() -> new ViewTicketsUI(testBasket, testUser, loginSystem));
    }

    @Test
    // Tests adding a ticket to the basket
    public void testAddTicketToBasket() {
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();

        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);
        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);

        assertDoesNotThrow(() -> viewTicketsUI.addSelectedTicketToBasket(new viewTickets(testUser)));
    }

    @Test
    // Tests navigation to the dashboard from ViewTicketsUI
    public void testNavigateToDashboard() {
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();
        LoginSystem loginSystem = new LoginSystem();

        ViewTicketsUI viewTicketsUI = new ViewTicketsUI(testBasket, testUser, loginSystem);

        assertDoesNotThrow(viewTicketsUI::navigateToDashboard);
    }
}
