package fr.ippon.chat.server;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.ippon.chat.client.service.EmailService;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class EmailServiceImpl extends RemoteServiceServlet implements
		EmailService {

	public void sendMail(){
		
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = " See website";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("admin@example.com", "Example.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("sebastien.foubert@gmail.com", "Sebastien"));
            msg.setSubject("Message");
            msg.setText(msgBody);
            Transport.send(msg);
        } catch (UnsupportedEncodingException e) {
        	System.out.println(e.getMessage());
        } catch (AddressException e) {
        	System.out.println(e.getMessage());
        } catch (MessagingException e) {
        	System.out.println(e.getMessage());
        }
	}

}
