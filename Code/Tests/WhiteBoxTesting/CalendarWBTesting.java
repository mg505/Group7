package WhiteBoxTesting;

import Calendar.Calendar;
import UserInterfaces.CalendarUI;
import login.LoginSystem;
import login.User;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarWBTesting {

    @Test
    public void testGetLastMonthWithTicketsLogic() {
        // Verify sorting and last ticket month extraction logic
        Calendar calendar = new Calendar();
        YearMonth expectedLastMonth = YearMonth.of(2025, 3);
        YearMonth actualLastMonth = Calendar.getLastMonthWithTicket();
        assertEquals(expectedLastMonth, actualLastMonth, "Expected last ticket month to be March 2025.");
    }

    @Test
    public void testUpdateMonthLabel() {
        // Test CalendarUI's updateMonth() logic
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        Calendar calendar = new Calendar();

        CalendarUI calendarUI = new CalendarUI(calendar, user, loginSystem);
        YearMonth newMonth = YearMonth.of(2025, 2); // Change month manually
        calendarUI.currentMonth = newMonth;
        calendarUI.updateMonth();

        assertEquals("FEBRUARY 2025", calendarUI.monthLabel.getText(), "Month label should reflect February 2025.");
    }

    @Test
    public void testNavigationLogic() {
        // Validate month navigation boundaries
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        Calendar calendar = new Calendar();

        CalendarUI calendarUI = new CalendarUI(calendar, user, loginSystem);

        // Try navigating to invalid months
        // Try navigating backward to a month before the current month
        calendarUI.navigateMonth(-12); // This should stay within the current valid month range
        assertEquals(YearMonth.now(), calendarUI.currentMonth, "Should not navigate to months before the current month.");

        // Test navigating forward to a month beyond the last month with tickets
        calendarUI.navigateMonth(12); // This should stay within the valid range, i.e., March 2025
        assertEquals(Calendar.getLastMonthWithTicket(), calendarUI.currentMonth, "Should not navigate beyond the last ticket month.");
    }


    @Test
    public void testUpdateCalendarTableLogic() {
        // Verify correct ticket assignment to the calendar table
        LoginSystem loginSystem = new LoginSystem();
        User user = loginSystem.registerUser("testuser", "password123");
        Calendar calendar = new Calendar();

        CalendarUI calendarUI = new CalendarUI(calendar, user, loginSystem);
        calendarUI.updateCalendarTable();

        // Manually verify table content (white-box check)
        assertTrue(calendarUI.calendarTable.getRowCount() > 0, "Table should populate rows.");
        assertTrue(calendarUI.calendarTable.getColumnCount() == 7, "Table should have 7 columns for days of the week.");
    }
}
