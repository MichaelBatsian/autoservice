package services.impl;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import services.EmailService;

public class EmailServiceImpl implements EmailService  {
	
	
	public void sendEmail(String emailTo,String senderEmail, String senderName, String body){
		
	    Email email = new SimpleEmail();
	try { 	    
	    email.setSmtpPort(587);
	    email.setAuthenticator(new DefaultAuthenticator("aservice.by@gmail.com","aservice"));
	    email.setDebug(false);
	    email.setHostName("smtp.gmail.com");
	 	email.setFrom("aservice.by@gmail.com");
	    email.setSubject("Обратная связь : e-mail клиента: "+emailTo+" | Имя клиента: "+senderEmail);
	    email.setMsg(body);
	    email.addTo(emailTo);
	    email.setStartTLSEnabled(true);
	    email.send();
	    } catch (EmailException e) {
			throw new RuntimeException();
		}
	    System.out.println("Mail sent!");
	}

	
}
