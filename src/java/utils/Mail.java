package utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    public static void SendEmail(String newAccEmail, String validationNums) 
            throws MessagingException {
        String toEmail = newAccEmail;

        // Sender's email ID needs to be mentioned
        String fromEmail = "onlinelearningsystemgroup6@gmail.com";
        
        String password = "abcd1234@";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(fromEmail, password);

            }

        });

        // Used to debug SMTP issues
        // session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(fromEmail));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            
            // Set Subject: header field
            message.setSubject("Online Learning System: Register new account");

            // Now set the actual message
            message.setText("You've just recently registered a new account on our website. Activation code: " + validationNums);
            
            // Send message
            Transport.send(message);
        } finally {}
    }
}

