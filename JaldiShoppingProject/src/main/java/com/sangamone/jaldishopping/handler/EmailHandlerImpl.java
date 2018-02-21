package com.sangamone.jaldishopping.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.sangamone.jaldishopping.constants.Messages;


@Component(value = "emailHandler")
public class EmailHandlerImpl implements EmailHandler {
	@Autowired
	private MailSender mailSender;

	@Autowired
	Messages messages;

	public void sendEmail(String emailId, String message)  {

		System.out.println("Email Message :" + message);

		SimpleMailMessage setmessage = new SimpleMailMessage();

		setmessage.setTo(emailId);
		
		setmessage.setSubject(messages.getMessage(Messages.EMAIL_SUBJECT));

		setmessage.setText(message);

		mailSender.send(setmessage);

	}

	
}
