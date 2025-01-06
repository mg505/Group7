package UserInterfaces;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class ContactSupportUI {

    private JFrame frame;

    public ContactSupportUI() {
        createSupportUI();
    }

    private void createSupportUI() {
        // Create and configure the main frame
        frame = new JFrame("Contact Support");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel instructions = new JLabel("Describe your issue below:");
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setFont(new Font("Arial", Font.BOLD, 14));
        frame.add(instructions, BorderLayout.NORTH);

        // Text area for entering issue description
        JTextArea issueTextArea = new JTextArea();
        frame.add(new JScrollPane(issueTextArea), BorderLayout.CENTER);

        // Submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String issue = issueTextArea.getText().trim();
            if (!issue.isEmpty()) {
                logIssue(issue);
                JOptionPane.showMessageDialog(frame, "Thank you! Your issue has been submitted.");
                frame.dispose(); // Close the support frame
            } else {
                JOptionPane.showMessageDialog(frame, "Please describe your issue before submitting.");
            }
        });

        frame.add(submitButton, BorderLayout.SOUTH);
        frame.setVisible(true);  // Make the frame visible
    }

    private void logIssue(String issue) {
        // Example: Log the issue to a file (you can replace this with a database or email logic)
        try (FileWriter writer = new FileWriter("support_issues.txt", true)) {
            writer.write(issue + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
