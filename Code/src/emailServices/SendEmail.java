package emailServices;

public class SendEmail {
	  public static void main(String[] args) {
		  // Replace with actual variables when ready
	        String userEmail = "miranda@mirandagriffith.com"; 
	        String departureTime = "10:00 AM, December 2, 2024"; 
	        String seatNumber = "A12";                      
	        String startStation = "St.Pancreas";              
	        String destination = "Leicester"; 

	        // Email subject and body
	        String subject = "Train Ticket Confirmation";
	        String message = generateTicketConfirmationMessage(userEmail,departureTime, seatNumber, startStation,destination);

	        // Creates an instance of EmailService and sends the email
	        EmailService emailService = new EmailService();
	        emailService.sendEmail(userEmail, subject, message);

	        System.out.println("Train ticket confirmation email sent to " + userEmail);
	    }

	    private static String generateTicketConfirmationMessage(String userEmail,String startStation,String destination ,String departureTime, String seatNumber) {
	        return "Dear Customer,\n\n" +
	               "Thank you for booking a ticket with us! Here are your booking details:\n\n" +
	               "Boarding Station: " + startStation + "\n\n" +
	               "Destination: " + destination + "\n\n" +
	               "Departure Time: " + departureTime + "\n" +
	               "Seat Number: " + seatNumber + "\n" +
	               "We hope you have a pleasant and safe journey!\n\n" +
	               "Best regards,\nTrain Booking System Team";
	    }
	

}
