package com.sangamone.jaldishopping.services;

import java.util.List;

import com.sangamone.jaldishopping.controller.JaldiShoppingResponse;
import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.exception.JaldiShoppingBaseException;

public interface AdminService {



	UserDetails validateUser(String emailId);

	void addUsers(String firstName, String lastName, String userEmail, String userMobile,
			String zipCode);

	UserDetails validateLogin(String userEmail, String userPassword);

	ProductDetails addProductDetails(Long productId, Long categoryId, Long vendorId, Long locationId);

	List<ProductDetails> findByProductId(Long productId) throws JaldiShoppingBaseException;

	ProductDetails addProductDetails(Long productId);

	List<ProductDetails> findByBarCode(String barCode) throws JaldiShoppingBaseException;

	ProductDetails addProductDetails(String barCode);







	
}
