package com.sangamone.jaldishopping.service.impl;



import java.text.MessageFormat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sangamone.jaldishopping.constants.Messages;
import com.sangamone.jaldishopping.handler.EmailHandler;
import com.sangamone.jaldishopping.handler.SmsHandler;
import com.sangamone.jaldishopping.services.MessageSender;


@Component(value = "messageSender")
public class SmsAndEmailMessageSender implements MessageSender {
	@Autowired
	private SmsHandler smsHandler;
	@Autowired
	private EmailHandler emailHandler;
	@Autowired
	Messages messages;

	public String getOTPChannel() {
		// TODO Auto-generated method stub
		 return null;
	}

	

	public void sendMessage(long mobileNumber, String emailId, String message)  {
		// TODO Auto-generated method stub

		try{
			smsHandler.sendSMS(mobileNumber, MessageFormat.format(
					messages.getMessage(Messages.SEND_MESSAGE_SMS),String.valueOf(emailId),
					String.valueOf(message)));
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		try {
			System.out.println("Send Email Service "+emailId+"    "+message+"    "+mobileNumber);

			emailHandler.sendEmail(emailId, MessageFormat.format(
					messages.getMessage(Messages.SEND_MESSAGE_EMAIL),String.valueOf(emailId),
					String.valueOf(message)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
