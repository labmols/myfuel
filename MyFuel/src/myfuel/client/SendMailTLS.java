package myfuel.client;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class using JavaMail API for sending mail .
 */
public class SendMailTLS {

	/**
	 * Send an Email via Gmail SMTP server using TLS connection.
	 * @param emailAddress - Destination email address.
	 * @param subject - Mail Subject.
	 * @param content - Mail Content.
	 */
	public static void sendMail(String emailAddress, String subject, String content){
		final String username = "myfuel.braude@gmail.com";
		final String password = "labmols1";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("myfuel.braude@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(emailAddress));
			message.setSubject(subject);
			message.setText(content);

			Transport.send(message);

			

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		
	}
}