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

        //example of populated current tickets
        currentTickets.add(new String[]{"Route C", "12/1/25", "02:00 PM", "80.0"});
        currentTickets.add(new String[]{"Route B", "27/3/25", "12:00 PM", "30.0"});
        currentTickets.add(new String[]{"Route A", "10/2/25", "10:00 AM", "50.0"});
       
    }
    
    // Get the current tickets
    public List<String[]> getCurrentTickets() {
        return currentTickets;
    }
    
    //get last month with a ticket
    public static YearMonth getLastMonthWithTicket() {
    	
    	// date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");

        // Sort tickets by date to get the last tickets month
        return currentTickets.stream()
                .map(ticket -> LocalDate.parse(ticket[1], formatter))
                .sorted(Comparator.naturalOrder()) // Sort by date in ascending order
                .reduce((first, second) -> second) // Get the last date
                .map(YearMonth::from)
                .orElse(YearMonth.now()); 
    }
}