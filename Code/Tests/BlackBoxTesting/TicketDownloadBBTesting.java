package BlackBoxTesting;

import TicketDownloadCode.TicketDownloader;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class TicketDownloadBBTesting {

    @Test
    public void testDownloadTicketCreatesFile() {
        // Simulate ticket details
        String ticketDetails = "123, Route A, 10:00 AM, 50.00";  

        // Mock parent component
        JFrame parentComponent = new JFrame();

        // Call the downloadTicket method
        TicketDownloader.downloadTicket(parentComponent, ticketDetails);

        // Check if the file exists in the Downloads folder
        String userHome = System.getProperty("user.home");
        File downloadsDir = new File(userHome, "Downloads");
        File expectedFile = new File(downloadsDir, "ticket_123.txt");

        assertTrue(expectedFile.exists(), "The ticket file should be created in the Downloads folder.");

        // Clean up: Delete the file after test
        expectedFile.delete();
    }

    @Test
    public void testDownloadTicketContent() throws Exception {
        // Simulate ticket details
        String ticketDetails = "123, Route A, 10:00 AM, 50.00"; 

        // Mock parent component
        JFrame parentComponent = new JFrame();

        // Call the downloadTicket method
        TicketDownloader.downloadTicket(parentComponent, ticketDetails);

        // Verify the file content
        String userHome = System.getProperty("user.home");
        File downloadsDir = new File(userHome, "Downloads");
        File expectedFile = new File(downloadsDir, "ticket_123.txt");

        assertTrue(expectedFile.exists(), "The ticket file should exist.");

        String fileContent = new String(Files.readAllBytes(Paths.get(expectedFile.getAbsolutePath())));
        assertTrue(fileContent.contains(ticketDetails), "The file content should include the ticket details.");

        // Clean up: Delete the file after test
        expectedFile.delete();
    }

    @Test
    public void testDownloadTicketWithInvalidPath() {
        // Simulate invalid user home path
        System.setProperty("user.home", "/invalid/path");

        // Simulate ticket details
        String ticketDetails = "123, Route A, 10:00 AM, 50.00";  // Full ticket string as displayed in the UI

        // Mock parent component
        JFrame parentComponent = new JFrame();

        // Call the downloadTicket method
        assertDoesNotThrow(() -> TicketDownloader.downloadTicket(parentComponent, ticketDetails),
                "The method should handle invalid paths gracefully.");
    }
}
