package BlackBoxTesting;

import Calendar.Calendar;
import UserInterfaces.CalendarUI;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarBBTesting {

    @Test
    public void testNavigateToDashboard() {
        // Setup: LoginSystem, User, and Calendar
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        Calendar calendar = new Calendar();

        // Assert no exceptions during dashboard navigation
        assertDoesNotThrow(() -> {
            CalendarUI calendarUI = new CalendarUI(calendar, user, loginSystem);
            calendarUI.navigateToDashboard();
        }, "Navigation to dashboard should succeed without exceptions.");
    }

    @Test
    public void testGetLastMonthWithTickets() {
        // Setup Calendar with pre-populated tickets
        Calendar calendar = new Calendar();

        // Check the last month with tickets
        YearMonth expectedLastMonth = YearMonth.of(2025, 3); // Based on the test data
        YearMonth actualLastMonth = Calendar.getLastMonthWithTicket();
        assertEquals(expectedLastMonth, actualLastMonth, "The last month with tickets should be March 2025.");
    }

    @Test
    public void testUpdateCalendarTable() {
        // Setup: LoginSystem, User, and Calendar
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        Calendar calendar = new Calendar();

        // Assert no exceptions during calendar table update
        assertDoesNotThrow(() -> {
            CalendarUI calendarUI = new CalendarUI(calendar, user, loginSystem);
            calendarUI.updateCalendarTable();
        }, "Updating the calendar table should succeed without exceptions.");
    }

    @Test
    public void testNavigationWithinValidMonthRange() {
        // Setup: LoginSystem, User, and Calendar
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        Calendar calendar = new Calendar();

        CalendarUI calendarUI = new CalendarUI(calendar, user, loginSystem);

        // Navigate to a valid previous month
        calendarUI.navigateMonth(-1);
        assertFalse(calendarUI.currentMonth.isBefore(YearMonth.now()), "Should not navigate to a month before the current month.");

        // Navigate to a valid future month
        calendarUI.navigateMonth(1);
        assertFalse(calendarUI.currentMonth.isAfter(Calendar.getLastMonthWithTicket()), "Should not navigate beyond the last month with tickets.");
    }
}
