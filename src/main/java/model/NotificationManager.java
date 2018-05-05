package model;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import entity.Post;
import entity.User;

public class NotificationManager {

	public void sendEmail(String user,String rec, String usertype) {

		final String username = "designlabbookabook@gmail.com";
		final String password = "bookabook";

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
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(user));
			message.setSubject("Notification from book-a-book.com");
			String msg;
			if(usertype.equals("buyer")) {
				User u = User.getUser(rec);
				msg = "Name of Seller: "+u.getName() + "\nContact: "+u.getContact()+"\nAddress: "+u.getAddress()+"\nKindly Contact Within 7 days of placing Order.";
			}
			else if(usertype.equals("seller"))
			{
				User u = User.getUser(rec);
				msg = "Name of Buyer: "+u.getName() + "\nContact: "+u.getContact()+"\nAddress: "+u.getAddress()+"\nKindly Contact Within 7 days of placing Order.";
			}
			else {
				msg = "Your Request for isbn: "+usertype+" is available now.";
			}
			message.setText("Dear User,"
				+ "\n\n"+msg);

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}