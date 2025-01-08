package TicketDownloadCode;

import javax.swing.*;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TicketDownloader {

    public static void downloadTicket(Component parentComponent, String ticketId, String route, String time, String price) {
        // Get the user's Downloads folder
        String userHome = System.getProperty("user.home");
        File downloadsDir = new File(userHome, "Downloads");

        // Create the file in the Downloads folder with a default name
        File fileToSave = new File(downloadsDir, "ticket_" + ticketId + ".txt");

        try (FileWriter writer = new FileWriter(fileToSave)) {
            // Write the ticket details to the file
            writer.write("Ticket ID: " + ticketId + "\n");
            writer.write("Route: " + route + "\n");
            writer.write("Time: " + time + "\n");
            writer.write("Price: £" + price + "\n");

            JOptionPane.showMessageDialog(parentComponent, "Ticket saved successfully to " + fileToSave.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parentComponent, "Error saving ticket: " + ex.getMessage());
        }
    }
}
