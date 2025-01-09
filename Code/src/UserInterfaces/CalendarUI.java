package UserInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import Calendar.Calendar;
import login.LoginSystem;
import login.User;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CalendarUI {

    // Label to display the current month in the calendar header
    public JLabel monthLabel; 

    // JTable to display the calendar's structure
    public JTable calendarTable;

    private JFrame frame; 
    private Calendar calendar; 
    public YearMonth currentMonth;
    private YearMonth lastMonthWithTickets; 
    private LoginSystem loginSystem; 
    private User user; 

    // Constructor that initialises the calendar UI with user and calendar data
    public CalendarUI(Calendar calendar, User user, LoginSystem loginSystem) {
        this.calendar = calendar;
        this.user = user;
        this.currentMonth = YearMonth.now(); 
        this.lastMonthWithTickets = Calendar.getLastMonthWithTicket(); 
        this.loginSystem = loginSystem;
        initialiseUI();
    }

    //Initialises and handles  UI for calendar
    private void initialiseUI() {
    	// Create a new JFrame for the calendar
        frame = new JFrame("Calendar"); 
        frame.setSize(600, 400); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setLayout(new BorderLayout()); 

        // Create header panel with navigation buttons and month label
        JPanel headerPanel = new JPanel(new BorderLayout());
        monthLabel = new JLabel("", JLabel.CENTER); 
        updateMonth(); 

        // Buttons to navigate between months
        JButton prevMonthBtn = new JButton("<");
        JButton nextMonthBtn = new JButton(">");

        // Action listeners for month navigation
        prevMonthBtn.addActionListener(e -> navigateMonth(-1));
        nextMonthBtn.addActionListener(e -> navigateMonth(1));

        // Home button to navigate go to the Dashboard
        JPanel homeBtnPanel = new JPanel();
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToDashboard(); 
            }
        });
        homeBtnPanel.add(homeButton); 
        
        
        headerPanel.add(prevMonthBtn, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextMonthBtn, BorderLayout.EAST);

   
        calendarTable = new JTable();
        updateCalendarTable();

     
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(calendarTable), BorderLayout.CENTER);
        frame.add(homeBtnPanel, BorderLayout.SOUTH);

        // Make the frame visible
        frame.setVisible(true);
    }

   
    public void updateMonth() {
        monthLabel.setText(currentMonth.getMonth().name() + " " + currentMonth.getYear());
    }

    public void navigateMonth(int delta) {
        YearMonth newMonth = currentMonth.plusMonths(delta);

        if (newMonth.isBefore(YearMonth.now())) {
            currentMonth = YearMonth.now();
        } else if (newMonth.isAfter(lastMonthWithTickets)) {
        	currentMonth = lastMonthWithTickets;
        } else {
            
            currentMonth = newMonth;
        }

        updateMonth();  
        updateCalendarTable();  
    }




    // Update the calendar table with the days of the current month and associated ticket info
    public void updateCalendarTable() {
        String[] columnNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Determine the first day of the month and the number of days in the month
        LocalDate firstDay = currentMonth.atDay(1);
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue() % 7;
        int daysInMonth = currentMonth.lengthOfMonth();

        // Map to hold ticket details for each day in the month
        Map<Integer, String> ticketDetails = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");

        // Retrieve ticket details and add them to calendar
        for (String[] ticket : calendar.getCurrentTickets()) {
            LocalDate ticketDate = LocalDate.parse(ticket[1], formatter);
            if (YearMonth.from(ticketDate).equals(currentMonth)) {
                int dayOfMonth = ticketDate.getDayOfMonth();
                String detail = " - " + ticket[0] + " @ " + ticket[2]; // Route and time for the ticket
                ticketDetails.put(dayOfMonth, ticketDetails.getOrDefault(dayOfMonth, "") + detail);
            }
        }

        Object[] week = new Object[7];
        for (int i = 0; i < firstDayOfWeek; i++) {
            week[i] = ""; 
        }

        // Populate the table with days and ticket details
        for (int day = 1; day <= daysInMonth; day++) {
            if (ticketDetails.containsKey(day)) {
                week[firstDayOfWeek] = day + ": " + ticketDetails.get(day); // Add ticket details if available
            } else {
                week[firstDayOfWeek] = day; 
            }
            firstDayOfWeek++;

            if (firstDayOfWeek == 7 || day == daysInMonth) {
                model.addRow(week);
                week = new Object[7];
                firstDayOfWeek = 0;
            }
        }

        // Set the updated model to the calendar table
        calendarTable.setModel(model);
    }

    // Navigate back to the Dashboard UI
    public void navigateToDashboard() {
        frame.dispose();
        new DashboardUI(user, loginSystem); 
    }
}
