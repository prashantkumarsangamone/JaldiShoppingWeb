package com.sangamone.jaldishopping.services;

import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.domain.UserDetails;

public interface AdminService {



	UserDetails validateUser(String emailId);

	void addUsers(String firstName, String lastName, String userEmail, String userMobile,
			String zipCode);

	UserDetails validateLogin(String userEmail, String userPassword);

	ProductDetails addProductDetails(Long productId, Long categoryId, Long vendorId, Long locationId);



	
}
