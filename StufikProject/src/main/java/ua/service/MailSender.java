package ua.service;

public interface MailSender {
	
	void sendMail(String content, String email, String mailBody);
}
