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
        // Tests that adding a ticket to the basket updates it correctly.
        Basket basket = new Basket();
        String[] ticketDetails = {"Route A", "10:00 AM", "50.0"};
        basket.addTicket(1, ticketDetails, 50.0);

        assertEquals(1, basket.getTickets().size(), "Basket should contain 1 ticket.");
        assertEquals(50.0, basket.calculateTotalCost(), "Total cost should be 50.0.");
    }

    @Test
    public void testBasketClear() {
        // Tests that clearing the basket removes all tickets and resets the cost.
        Basket basket = new Basket();
        String[] ticketDetails = {"Route A", "10:00 AM", "50.0"};
        basket.addTicket(1, ticketDetails, 50.0);
        basket.clearBasket();

        assertTrue(basket.getTickets().isEmpty(), "Basket should be empty after clearing.");
        assertEquals(0.0, basket.calculateTotalCost(), "Total cost should be 0.0 after clearing.");
    }

    @Test
    public void testPurchaseBasketUICreation() {
        // Tests that the PurchaseBasketUI initializes without exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User testUser = new User("testUser", "password123");
        Basket basket = new Basket();

        assertDoesNotThrow(() -> new PurchaseBasketUI(testUser, basket, loginSystem), "PurchaseBasketUI should initialize without exceptions.");
    }

    @Test
    public void testPurchaseBasketProceedWithPurchase() {
        // Tests that proceeding with a purchase does not throw exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User testUser = new User("testUser", "password123");
        Basket basket = new Basket();
        basket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, basket, loginSystem);
        assertDoesNotThrow(purchaseBasketUI::proceedWithPurchase, "Proceeding with purchase should not throw exceptions.");
    }
}