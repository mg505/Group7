package TicketDownloadCode;
import javax.swing.*;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TicketDownloader {

    public static void downloadTicket(Component parentComponent, String ticketId, String route, String time, String price) {
        // Open a file chooser dialog for the user to select the save location
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Ticket");

        // Set default file name
        fileChooser.setSelectedFile(new File("ticket_" + ticketId + ".txt"));

        int userSelection = fileChooser.showSaveDialog(parentComponent);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

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
}
