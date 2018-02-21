package com.sangamone.jaldishopping.services;



public interface MessageSender {


	String getOTPChannel();

	void sendMessage(long mobileNumber, String emailId, String message);

	
}
