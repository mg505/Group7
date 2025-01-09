package BlackBoxTesting;

import PurchaseBasket.Basket;
import UserInterfaces.PurchaseBasketUI;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseBasketBBTesting {

    @Test
    public void testBasketAddTicket() {
        // Setup: Create a Basket instance
        Basket basket = new Basket();

        // Add a ticket to the basket
        String[] ticketDetails = {"Route A", "10:00 AM", "50.0"};
        basket.addTicket(1, ticketDetails, 50.0);

        // Verify the ticket is added correctly
        assertEquals(1, basket.getTickets().size(), "Basket should contain 1 ticket.");
        assertEquals(50.0, basket.calculateTotalCost(), "Total cost should be 50.0.");
    }

    @Test
    public void testBasketClear() {
        // Setup: Create a Basket instance
        Basket basket = new Basket();

        // Add a ticket and then clear the basket
        String[] ticketDetails = {"Route A", "10:00 AM", "50.0"};
        basket.addTicket(1, ticketDetails, 50.0);
        basket.clearBasket();

        // Verify the basket is cleared
        assertTrue(basket.getTickets().isEmpty(), "Basket should be empty after clearing.");
        assertEquals(0.0, basket.calculateTotalCost(), "Total cost should be 0.0 after clearing.");
    }

    @Test
    public void testPurchaseBasketUICreation() {
        // Setup: Create required objects
        LoginSystem loginSystem = new LoginSystem();
        User testUser = new User("testUser", "password123");
        Basket basket = new Basket();

        // Verify that the PurchaseBasketUI initializes without exceptions
        assertDoesNotThrow(() -> new PurchaseBasketUI(testUser, basket, loginSystem), "PurchaseBasketUI should initialize without exceptions.");
    }

    @Test
    public void testPurchaseBasketProceedWithPurchase() {
        // Setup: Create required objects
        LoginSystem loginSystem = new LoginSystem();
        User testUser = new User("testUser", "password123");
        Basket basket = new Basket();

        // Add a ticket to the basket
        basket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        // Verify that proceeding with purchase does not throw exceptions
        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, basket, loginSystem);
        assertDoesNotThrow(purchaseBasketUI::proceedWithPurchase, "Proceeding with purchase should not throw exceptions.");
    }
}
