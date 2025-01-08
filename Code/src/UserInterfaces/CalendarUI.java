package UserInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import Calendar.Calendar; 
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class CalendarUI {


    private JLabel monthLabel;
    private JTable calendarTable;
    private JFrame frame;
    private Calendar calendar;
    private YearMonth currentMonth;
    private YearMonth lastMonthWithTickets;

    public CalendarUI(Calendar calendar) {
        this.calendar = calendar;
        this.currentMonth = YearMonth.now();
        this.lastMonthWithTickets = Calendar.getLastMonthWithTicket();
        initializeUI();
    }

    private void initializeUI() {
        
        frame = new JFrame("Calendar");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Header panel with month and navigation
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel monthLabel = new JLabel("", JLabel.CENTER);
        updateMonth();

        JButton prevMonthBtn = new JButton("<");
        JButton nextMonthBtn = new JButton(">");

        prevMonthBtn.addActionListener(e -> navigateMonth(-1));
        nextMonthBtn.addActionListener(e -> navigateMonth(1));
        
        //home button - goes to dash board
        JPanel homeBtnPanel = new JPanel();
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToDashboard();  // Go back to dash board
            }
        });
        homeBtnPanel.add(homeButton); // Add home button to the panel

        headerPanel.add(prevMonthBtn, BorderLayout.WEST);
        headerPanel.add(monthLabel, BorderLayout.CENTER);
        headerPanel.add(nextMonthBtn, BorderLayout.EAST);

        // Calendar table
        calendarTable = new JTable();
        updateCalendarTable();

        // Add components to frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(calendarTable), BorderLayout.CENTER);
        frame.add(homeBtnPanel, BorderLayout.SOUTH); 


        frame.setVisible(true);
    }

    private void updateMonth() {
        monthLabel.setText(currentMonth.getMonth().name() + " " + currentMonth.getYear());
    }

    private void navigateMonth(int delta) {
        YearMonth newMonth = currentMonth.plusMonths(delta);

        // so we don't go past current month and the last month with tickets
        if (!newMonth.isBefore(YearMonth.now()) && !newMonth.isAfter(lastMonthWithTickets)) {
            currentMonth = newMonth;
            updateMonth();
            updateCalendarTable();
        }
    }

    private void updateCalendarTable() {
        String[] columnNames = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        LocalDate firstDay = currentMonth.atDay(1);
        int firstDayOfWeek = firstDay.getDayOfWeek().getValue() % 7;
        int daysInMonth = currentMonth.lengthOfMonth();

        // Create a map of ticket days to their details to display
        Map<Integer, String> ticketDetails = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy");
        for (String[] ticket : calendar.getCurrentTickets()) {
            LocalDate ticketDate = LocalDate.parse(ticket[1], formatter);
            if (YearMonth.from(ticketDate).equals(currentMonth)) {
                int dayOfMonth = ticketDate.getDayOfMonth();
                String detail = " - " + ticket[0] + " @ " + ticket[2]; // Route and time
                ticketDetails.put(dayOfMonth, ticketDetails.getOrDefault(dayOfMonth, "") + detail );

            }
        }

        Object[] week = new Object[7];
        for (int i = 0; i < firstDayOfWeek; i++) {
            week[i] = ""; // Empty cells before the first day of the month
        }

        for (int day = 1; day <= daysInMonth; day++) {
            if (ticketDetails.containsKey(day)) {
                week[firstDayOfWeek] = day + ": " + ticketDetails.get(day); // Add route and time
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

        calendarTable.setModel(model);
    }
    
    private void navigateToDashboard() {
    	
        // Navigate back to the Dashboard UI
        frame.dispose();  // Close the current calendar frame
        
        new DashboardUI();  // Create and display a new DashboardUI instance
    }

}
