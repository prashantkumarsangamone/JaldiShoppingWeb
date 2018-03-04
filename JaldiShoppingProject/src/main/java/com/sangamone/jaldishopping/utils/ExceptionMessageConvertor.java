package com.sangamone.jaldishopping.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;



@Component
@PropertySource("classpath:ExceptionMessages.properties")
public class ExceptionMessageConvertor {

	@Autowired
	Environment env;

	/*public String getCode(Exception e) {
		if (!(e instanceof JaldiShoppingBaseException)) {

			e = new TechnicalException();
		}

		return env.getProperty(e.getClass().getName() + ".code");
	}

	public String getMessage(Exception e) {
		if (!(e instanceof JaldiShoppingBaseException)) {

			e = new TechnicalException();
		}
		return env.getProperty(e.getClass().getName() + ".message");
	}*/

}