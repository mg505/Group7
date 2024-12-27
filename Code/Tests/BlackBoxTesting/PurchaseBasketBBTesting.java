package BlackBoxTesting;

import login.User;
import PurchaseBasket.Basket;
import UserInterfaces.PurchaseBasketUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseBasketBBTesting {

    @Test
    public void testPurchaseBasketInitialization() {
        // Setup: Create a User and Basket
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();

        // Create the PurchaseBasketUI instance
        assertDoesNotThrow(() -> new PurchaseBasketUI(testUser, testBasket),
                "PurchaseBasketUI should initialize without exceptions.");
    }

    @Test
    public void testClearBasket() {
        // Setup: Create a User and Basket
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();

        // Add tickets to the basket
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        // Create the PurchaseBasketUI instance
        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, testBasket);

        // Clear the basket
        purchaseBasketUI.clearBasket();

        // Verify that the basket is empty
        assertTrue(testBasket.getTickets().isEmpty(), "Basket should be empty after clearing.");
    }

    @Test
    public void testProceedWithPurchaseAndEmailConfirmation() {
        // Setup: Create a User and Basket
        User testUser = new User("testuser", "password123");
        Basket testBasket = new Basket();

        // Add tickets to the basket
        testBasket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        // Create the PurchaseBasketUI instance
        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, testBasket);

        // Simulate proceeding with the purchase
        assertDoesNotThrow(() -> purchaseBasketUI.proceedWithPurchase(),
                "Proceeding with purchase should not throw exceptions.");

        // Verify that the basket is empty after purchase
        assertTrue(testBasket.getTickets().isEmpty(), "Basket should be empty after purchase.");
    }
}
