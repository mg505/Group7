package WhiteBoxTesting;

import login.LoginSystem;
import login.User;
import PurchaseBasket.Basket;
import UserInterfaces.PurchaseBasketUI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PurchaseBasketWBTesting {

    @Test
    public void testBasketAddTicketInternalState() {
        // Validates adding tickets updates the basket correctly.
        Basket basket = new Basket();
        String[] ticketDetails = {"Route A", "10:00 AM", "50.0"};

        basket.addTicket(1, ticketDetails, 50.0);

        assertEquals(1, basket.getTickets().size());
        assertTrue(basket.getTickets().containsKey(1));
    }

    @Test
    public void testBasketGenerateEmailBody() {
        // Ensures email body reflects basket contents accurately.
        Basket basket = new Basket();
        basket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);
        String emailBody = basket.generateEmailBody(50.0);

        assertTrue(emailBody.contains("Route: Route A"));
        assertTrue(emailBody.contains("Price: £50.0"));
    }

    @Test
    public void testPurchaseBasketDisplayContents() {
        // Verifies UI display logic for basket contents.
        LoginSystem loginSystem = new LoginSystem();
        User testUser = new User("testUser", "password123");
        Basket basket = new Basket();
        basket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, basket, loginSystem);
        assertDoesNotThrow(() -> purchaseBasketUI.displayBasketContents());
    }

    @Test
    public void testPurchaseBasketProceedWithPurchaseInternalState() {
        // Validates purchase logic clears the basket.
        LoginSystem loginSystem = new LoginSystem();
        User testUser = new User("testUser", "password123");
        Basket basket = new Basket();
        basket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, basket, loginSystem);
        purchaseBasketUI.proceedWithPurchase();

        assertTrue(basket.getTickets().isEmpty());
        assertEquals(0.0, basket.calculateTotalCost());
    }
}