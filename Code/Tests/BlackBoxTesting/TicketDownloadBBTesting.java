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
    // Tests that downloading a ticket creates the expected file
    public void testDownloadTicketCreatesFile() {
        String ticketDetails = "123, Route A, 10:00 AM, 50.00";
        JFrame parentComponent = new JFrame();

        TicketDownloader.downloadTicket(parentComponent, ticketDetails);

        String userHome = System.getProperty("user.home");
        File downloadsDir = new File(userHome, "Downloads");
        File expectedFile = new File(downloadsDir, "ticket_123.txt");

        assertTrue(expectedFile.exists());
        expectedFile.delete();
    }

    @Test
    // Tests that the ticket file contains the correct details
    public void testDownloadTicketContent() throws Exception {
        String ticketDetails = "123, Route A, 10:00 AM, 50.00";
        JFrame parentComponent = new JFrame();

        TicketDownloader.downloadTicket(parentComponent, ticketDetails);

        String userHome = System.getProperty("user.home");
        File downloadsDir = new File(userHome, "Downloads");
        File expectedFile = new File(downloadsDir, "ticket_123.txt");

        assertTrue(expectedFile.exists());
        String fileContent = new String(Files.readAllBytes(Paths.get(expectedFile.getAbsolutePath())));
        assertTrue(fileContent.contains(ticketDetails));

        expectedFile.delete();
    }

    @Test
    // Tests handling of invalid file paths during ticket download
    public void testDownloadTicketWithInvalidPath() {
        System.setProperty("user.home", "/invalid/path");
        String ticketDetails = "123, Route A, 10:00 AM, 50.00";
        JFrame parentComponent = new JFrame();

        assertDoesNotThrow(() -> TicketDownloader.downloadTicket(parentComponent, ticketDetails));
    }
}
