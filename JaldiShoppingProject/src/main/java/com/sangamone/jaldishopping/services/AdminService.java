package com.sangamone.jaldishopping.services;

import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.exception.JaldiShoppingBaseException;

public interface AdminService {



	UserDetails validateUser(String emailId);

	void addUsers(String firstName, String lastName, String userEmail, String userMobile,
			String zipCode);

	UserDetails validateLogin(String userEmail, String userPassword);

	
}
