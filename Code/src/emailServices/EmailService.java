package emailServices;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {
	
	
	//Email address and app password used to send the email
	private static final String senderEmail = "mirandazoegriffith@gmail.com"; // Your email
	private static final String senderPassword = "cnke agep oqlu exkt";    

    public void sendEmail(String recipientEmail, String subject, String messageBody) {
        // Set up email server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a session with Gmail using the email and app password at the top
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Creating the actual email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(messageBody);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);
            
            //If something goes wrong, the system will present an error message
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error occurred while sending email.");
        }
    }
}

