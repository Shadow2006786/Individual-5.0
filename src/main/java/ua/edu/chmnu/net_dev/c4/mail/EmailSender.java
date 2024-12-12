package ua.edu.chmnu.net_dev.c4.mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailSender {

    public static void main(String[] args) {
        String recipient = "shadow2006786@gmail.com";

        String sender = "shadow912999@gmail.com";
        String senderPassword = "password";

        String host = "smtp.gmail.com";

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, senderPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            message.setSubject("This is Subject");

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("This is the body of the mail");

            BodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = "attachment.txt";
            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Mail successfully sent");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
