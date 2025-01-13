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
        // Verifies that navigating to the dashboard works without throwing exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        Calendar calendar = new Calendar();

        assertDoesNotThrow(() -> {
            CalendarUI calendarUI = new CalendarUI(calendar, user, loginSystem);
            calendarUI.navigateToDashboard();
        }, "Navigation to dashboard should succeed without exceptions.");
    }

    @Test
    public void testGetLastMonthWithTickets() {
        // Ensures that the correct last month with tickets is returned.
        Calendar calendar = new Calendar();
        YearMonth expectedLastMonth = YearMonth.of(2025, 3);
        YearMonth actualLastMonth = Calendar.getLastMonthWithTicket();
        assertEquals(expectedLastMonth, actualLastMonth, "The last month with tickets should be March 2025.");
    }

    @Test
    public void testUpdateCalendarTable() {
        // Checks that updating the calendar table works without throwing exceptions.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        Calendar calendar = new Calendar();

        assertDoesNotThrow(() -> {
            CalendarUI calendarUI = new CalendarUI(calendar, user, loginSystem);
            calendarUI.updateCalendarTable();
        }, "Updating the calendar table should succeed without exceptions.");
    }

    @Test
    public void testNavigationWithinValidMonthRange() {
        // Validates navigation within valid month range without exceeding limits.
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        Calendar calendar = new Calendar();

        CalendarUI calendarUI = new CalendarUI(calendar, user, loginSystem);

        calendarUI.navigateMonth(-1);
        assertFalse(calendarUI.currentMonth.isBefore(YearMonth.now()), "Shouldn't navigate to a month before the current month.");

        calendarUI.navigateMonth(1);
        assertFalse(calendarUI.currentMonth.isAfter(Calendar.getLastMonthWithTicket()), "Shouldn't navigate beyond the last month with tickets.");
    }
}
