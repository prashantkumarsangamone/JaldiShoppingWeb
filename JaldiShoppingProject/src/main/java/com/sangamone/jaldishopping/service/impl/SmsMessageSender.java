package com.sangamone.jaldishopping.service.impl;



import org.springframework.beans.factory.annotation.Autowired;

import com.sangamone.jaldishopping.constants.Messages;
import com.sangamone.jaldishopping.handler.SmsHandler;
import com.sangamone.jaldishopping.services.MessageSender;




public class SmsMessageSender implements MessageSender {
	@Autowired
	private SmsHandler smsHandler;
	@Autowired
	Messages messages;
	public String getOTPChannel() {
		// TODO Auto-generated method stub
		return null;
	}
	public void sendMessage(long mobileNumber, String emailId, String message) {
		// TODO Auto-generated method stub
		
	}
	

}
