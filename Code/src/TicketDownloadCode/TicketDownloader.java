package TicketDownloadCode;

import javax.swing.*;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TicketDownloader {

    // Save ticket details to a file in the Downloads folder
    public static void downloadTicket(Component parentComponent, String ticket) {
        // Get user home directory
        String userHome = System.getProperty("user.home");
        // Get Downloads folder
        File downloadsDir = new File(userHome, "Downloads");

        // Create a file in the Downloads folder
        String ticketId = "ticket_" + System.currentTimeMillis(); // Generate a unique ticket ID based on current time
        File fileToSave = new File(downloadsDir, ticketId + ".txt");

        try (FileWriter writer = new FileWriter(fileToSave)) {
            // Write the whole ticket string to the file
            writer.write(ticket); // Write the full ticket string as is

            JOptionPane.showMessageDialog(parentComponent, "Ticket saved successfully.");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(parentComponent, "Error saving ticket: " + ex.getMessage());
        }
    }
}
