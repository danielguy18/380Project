package app;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility 
{
	String body = "";

	public static void sendEmail(String toEmail, String subject, String body)
	{
		final String toemail = toEmail;
        final String fromEmail = "no.reply.zadb@gmail.com"; //requires valid gmail id
		final String password = "dsov trvn icgy ljby "; // correct password for gmail id

		/*
		String subject = "CONFIRMATION CODE: " + rsvp.getConfirmationCode();
		String body = 	"Thank you for choosing Z.A.D.B Hotel to enjoy your spectacular trip!" + 
						"\n\nYour reservation information:\n" + rsvp.toString();
						*/
		
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port
		
		Authenticator auth = new Authenticator() 
		{
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session2 = Session.getDefaultInstance(props, auth);

		try
	    {
			MimeMessage msg = new MimeMessage(session2);
			//set message headers
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("no.reply.zadb@gmail.com", "NoReply-ZADB"));

			msg.setReplyTo(InternetAddress.parse("no.reply.zadb@gmail.com", false));

			msg.setSubject(subject, "UTF-8");

			msg.setText(body, "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toemail, false));
			System.out.println("Message is ready");
			Transport.send(msg);  

			System.out.println("Email Sent Successfully!!");
	    }
	    catch (Exception e) 
		{
	      	e.printStackTrace();
	    }
	}
}
