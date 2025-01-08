package DashboardCode;

import java.util.Scanner;

public class contactSupport {

    public void handleSupport() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------------------------------------");
        System.out.println("              Contact Support            ");
        System.out.println("-----------------------------------------");

        System.out.print("Please describe the issue you're facing: ");
        String issue = scanner.nextLine();

        logIssue(issue);

        System.out.println("\nThank you! Your issue has been logged.");
        System.out.println("Our support team will get back to you shortly.");
    }

    private void logIssue(String issue) {
        System.out.println("Logging issue: " + issue);
    }
}
