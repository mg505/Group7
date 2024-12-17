package viewTickets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	
    static HashMap<Integer, String[]> tickets;
	
	static ArrayList<Integer> basket;

    public Main() {
        tickets = new HashMap<>();
        basket = new ArrayList<>();
        
        tickets.put(1, new String[]{"leicester to birmingham", "12:00-13:15", "£12.00"});
        tickets.put(2, new String[]{"sheffield to manchester", "14:30-15:25", "£15.00"});
        tickets.put(3, new String[]{"leicester to cambridge", "09:05-11:00", "£23.00"});
        tickets.put(4, new String[]{"leicester to sheffield", "18:00-19:10", "£17.40"});
    }
    
    
	public static void main (String [] args) {
		
		Main mainApp = new Main();
		
		
		Scanner reader = new Scanner(System.in);
		System.out.println("enter page: ");
		int page = reader.nextInt();
	
		
		if (page == 2) {
			
			viewTickets viewTicketsPage = new viewTickets(mainApp.tickets, mainApp.basket);
			//print all available tickets
			
			System.out.println("-----------BUY TICKETS----------- \n"+ viewTicketsPage.showTickets());
			
			// navigation for view tickets page
			viewTicketsPage.nav();
		
		}
		
		System.out.println("exited page 2 \n"
				+ "-------------BASKET!-------------");
		for (Integer id: basket) {
			String[] ticket = tickets.get(id);
            
            System.out.println("Ticket ID: " + id);
            System.out.println("Route: " + ticket[0]);
            System.out.println("Time: " + ticket[1]);
            System.out.println("Price: " + ticket[2]);
            System.out.println("---------------------------------");						
		}
		
	}
}


