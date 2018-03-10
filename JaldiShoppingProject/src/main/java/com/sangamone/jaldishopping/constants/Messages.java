package com.sangamone.jaldishopping.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:messages.properties")
public class Messages {

	@Autowired
	Environment environment;
	public static final String SEND_MESSAGE_SMS = "SEND_MESSAGE_SMS";

	public static final String SEND_MESSAGE_EMAIL = "SEND_MESSAGE_EMAIL";

	public static final String EMAIL_SUBJECT = "EMAIL_SUBJECT";
	
	public static final String API_KEY = "API_KEY";
	
	public String getMessage(String key) {
		return environment.getProperty(key);

	}

}