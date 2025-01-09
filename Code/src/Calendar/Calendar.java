package Calendar;

import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.time.*;
import java.time.format.*;

public class Calendar {

    public static List<String[]> currentTickets;

    public Calendar() {
        currentTickets = new ArrayList<>();
        currentTickets.add(new String[]{"Route C", "12/1/25", "02:00 PM", "80.0"});
        currentTickets.add(new String[]{"Route B", "27/3/25", "12:00 PM", "30.0"});
        currentTickets.add(new String[]{"Route A", "10/2/25", "10:00 AM", "50.0"});
    }

    // Get current tickets list
    public List<String[]> getCurrentTickets() {
        return currentTickets;
    }

    // Get last month with a ticket
    public static YearMonth getLastMonthWithTicket() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");

        // Sort tickets by date and get the last ticket date
        return currentTickets.stream()
                .map(ticket -> LocalDate.parse(ticket[1], formatter))
                .sorted(Comparator.naturalOrder()) 
                .reduce((first, second) -> second) 
                .map(YearMonth::from)
             // Return current month if no tickets
                .orElse(YearMonth.now()); 
    }
}
