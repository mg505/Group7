package WhiteBoxTesting;

import login.User;
import PurchaseBasket.Basket;
import UserInterfaces.PurchaseBasketUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseBasketWBTesting {

    @Test
    public void testDisplayBasketContents() {
        // Setup: Create a User and Basket
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();

        // Add tickets to the basket
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);
        testBasket.addTicket(2, new String[]{"Route B", "2:00 PM", "30.0"}, 30.0);

        // Verify the basket's state
        assertEquals(2, testBasket.getTickets().size(), "Basket should contain two tickets.");
        assertTrue(testBasket.getTickets().containsKey(1), "Basket should contain Ticket ID 1.");
        assertTrue(testBasket.getTickets().containsKey(2), "Basket should contain Ticket ID 2.");
    }

    @Test
    public void testSendEmailConfirmationLogic() {
        // Setup: Create a User and Basket
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();

        // Add tickets to the basket
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        // Create the PurchaseBasketUI instance
        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, testBasket);

        // Simulate email confirmation
        assertDoesNotThrow(() -> purchaseBasketUI.sendEmailConfirmation("test@example.com"),
                "Sending email confirmation should not throw exceptions.");
    }

    @Test
    public void testProceedWithPurchaseLogic() {
        // Setup: Create a User and Basket
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();

        // Add tickets to the basket
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        // Verify initial basket state
        assertFalse(testBasket.getTickets().isEmpty(), "Basket should not be empty before purchase.");

        // Create the PurchaseBasketUI instance
        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, testBasket);

        // Simulate purchase
        purchaseBasketUI.proceedWithPurchase();

        // Verify that the basket is empty after purchase
        assertTrue(testBasket.getTickets().isEmpty(), "Basket should be empty after purchase.");
    }
}
