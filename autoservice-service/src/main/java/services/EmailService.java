package services;

public interface EmailService  {

	public void sendEmail(String emailTo,String senderEmail, String senderName, String body);
}
