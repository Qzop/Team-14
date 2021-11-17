package Account;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.*;



public class Messaging {


    public static void sendMail(String recepient) throws Exception {

        System.out.println("Preparing to send email");
        Properties properties = new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myGmailAccount = "projectcse360@gmail.com";
        String myPassword = "CSEProject360!@";

        Session session = Session.getInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myGmailAccount,myPassword);
            }
        });

        Message message = prepareMessage(session, myGmailAccount, recepient);

        Transport.send(message);
        System.out.println("Message Sent Successfully");

    }
    private static Message prepareMessage(Session session, String myGmailAccount,String recepient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myGmailAccount));
            message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
            message.setSubject("Verification Email");
            message.setText("Please click on the link to verify your email");

            return message;
        }
        catch(Exception ex) {
            Logger.getLogger(Messaging.class.getName()).log(Level.SEVERE,null,ex);
        }
        return null;
    }

}
