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
        // Setup: Create a Basket instance
        Basket basket = new Basket();

        // Add a ticket and verify internal state
        String[] ticketDetails = {"Route A", "10:00 AM", "50.0"};
        basket.addTicket(1, ticketDetails, 50.0);

        assertEquals(1, basket.getTickets().size(), "Basket should contain 1 ticket.");
        assertTrue(basket.getTickets().containsKey(1), "Basket should contain ticket ID 1.");
    }

    @Test
    public void testBasketGenerateEmailBody() {
        // Setup: Create a Basket instance
        Basket basket = new Basket();

        // Add a ticket and generate email body
        basket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);
        String emailBody = basket.generateEmailBody(50.0);

        assertTrue(emailBody.contains("Route: Route A"), "Email body should contain Route A.");
        assertTrue(emailBody.contains("Price: £50.0"), "Email body should contain the correct price.");
    }

    @Test
    public void testPurchaseBasketDisplayContents() {
        // Setup: Create required objects
        LoginSystem loginSystem = new LoginSystem();
        User testUser = new User("testUser", "password123");
        Basket basket = new Basket();

        // Add a ticket to the basket
        basket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        // Verify that displayBasketContents does not throw exceptions
        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, basket, loginSystem);
        assertDoesNotThrow(() -> purchaseBasketUI.displayBasketContents(), "Displaying basket contents should not throw exceptions.");
    }

    @Test
    public void testPurchaseBasketProceedWithPurchaseInternalState() {
        // Setup: Create required objects
        LoginSystem loginSystem = new LoginSystem();
        User testUser = new User("testUser", "password123");
        Basket basket = new Basket();

        // Add a ticket to the basket
        basket.addTicket(1, new String[]{"Route A", "10:00 AM", "50.0"}, 50.0);

        // Proceed with purchase
        PurchaseBasketUI purchaseBasketUI = new PurchaseBasketUI(testUser, basket, loginSystem);
        purchaseBasketUI.proceedWithPurchase();

        // Verify the basket is empty after purchase
        assertTrue(basket.getTickets().isEmpty(), "Basket should be empty after purchase.");
        assertEquals(0.0, basket.calculateTotalCost(), "Total cost should be 0.0 after purchase.");
    }
}
