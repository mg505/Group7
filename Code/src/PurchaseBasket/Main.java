package PurchaseBasket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import viewTickets.viewTickets;
import basket.Basket;

public class Main {

    static HashMap<Integer, String[]> tickets;
    static HashMap<Integer, Integer> basket;

    public Main() {
        tickets = new HashMap<>();
        basket = new HashMap<>();

        tickets.put(1, new String[]{"Leicester to Birmingham", "12:00-13:15", "12.00"});
        tickets.put(2, new String[]{"Sheffield to Manchester", "14:30-15:25", "15.00"});
        tickets.put(3, new String[]{"Leicester to Cambridge", "09:05-11:00", "23.00"});
        tickets.put(4, new String[]{"Leicester to Sheffield", "18:00-19:10", "17.40"});
    }

    public static void main(String[] args) {

        Main mainApp = new Main();

        Scanner reader = new Scanner(System.in);
        System.out.println("Enter page: ");
        int page = reader.nextInt();

        if (page == 2) {
            viewTickets viewTicketsPage = new viewTickets(mainApp.tickets, mainApp.basket);
            System.out.println("-----------BUY TICKETS----------- \n" + viewTicketsPage.showTickets());
            viewTicketsPage.nav();
        }

        System.out.println("Navigating to Basket Page...");
        Basket basketPage = new Basket(mainApp.tickets, mainApp.basket);
        basketPage.openBasket();

        System.out.println("Thank you for using the system!");
    }
}


