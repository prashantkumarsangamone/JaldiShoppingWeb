package com.sangamone.jaldishopping.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sangamone.jaldishopping.controller.JaldiShoppingResponse;
import com.sangamone.jaldishopping.controller.Response;
import com.sangamone.jaldishopping.domain.AuthorityDetails;
import com.sangamone.jaldishopping.domain.CategoryDetails;
import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.exception.InvalidProductCodeException;
import com.sangamone.jaldishopping.exception.JaldiShoppingBaseException;
import com.sangamone.jaldishopping.repositories.AuthorityDetailsRepository;
import com.sangamone.jaldishopping.repositories.CategoryDetailsRepository;
import com.sangamone.jaldishopping.repositories.LocationDetailsRepository;
import com.sangamone.jaldishopping.repositories.ProductDetailsRepository;
import com.sangamone.jaldishopping.repositories.UserDetailsRepository;
import com.sangamone.jaldishopping.repositories.VendorDetailsRepository;
import com.sangamone.jaldishopping.services.AdminService;
import com.sangamone.jaldishopping.services.MessageSender;
import com.sangamone.jaldishopping.services.WalmartAPIRequestSender;
import com.sangamone.jaldishopping.utils.RandomString;
import com.sangamone.jaldishopping.utils.ShaUtils;






@Component("adminService")

public class AdminServiceImpl implements AdminService 
{

	@Autowired
	private UserDetailsRepository userDetailsRepository;
	
	@Autowired
	private CategoryDetailsRepository categoryDetailsRepository;
	
	
	@Autowired
	private VendorDetailsRepository vendorDetailsRepository;
	
	@Autowired
	private LocationDetailsRepository locationDetailsRepository;
	

	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	private WalmartAPIRequestSender walmartAPIRequestSender;
	
	@Autowired
	private AuthorityDetailsRepository authorityDetailsRepository;
	
	@Autowired
	private ProductDetailsRepository productDetailsRepository;


	@Override
	public UserDetails validateUser(String userEmail) {
		// TODO Auto-generated method stub
		return userDetailsRepository.findByUserEmail(userEmail);
	}
	@Override
	public void addUsers(String firstName, String lastName, String userEmail, String userMobile,
			String zipCode) {
		// TODO Auto-generated method stub

		
		String enabled="1";
		UserDetails userDetails=new UserDetails();
		userDetails.setFirstName(firstName);
		userDetails.setLastName(lastName);
		userDetails.setUserEmail(userEmail);
		userDetails.setUserMobile(userMobile);
		String password = RandomString.generateRandomString();
		userDetails.setUserPassword(ShaUtils.getHash(password));
		userDetails.setZipCode(Long.parseLong(zipCode));
		userDetails.setEnabled(Long.parseLong(enabled));
		userDetailsRepository.save(userDetails);

		AuthorityDetails authorityDetails=new AuthorityDetails();
		authorityDetails.setUserEmail(userEmail);;
		authorityDetails.setAuthority("ROLE_ADMIN");
		authorityDetailsRepository.save(authorityDetails);
		String message = ":" + password + " \n - " ;

		try {
			messageSender.sendMessage(Long.parseLong(userMobile), userEmail,  message);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	public UserDetails validateLogin(String userEmail, String userPassword) {
		// TODO Auto-generated method stub
		UserDetails userDetails = userDetailsRepository.findByUserEmailAndUserPassword(userEmail,
				ShaUtils.getHash(userPassword));
		return userDetails;
	}
	
	
	@Override
	public ProductDetails addProductDetails(Long productId, Long categoryId, Long vendorId, Long locationId) {
		// TODO Auto-generated method stub
					int initialvalue= 0;
					
					String initialstring="unknown"; 
					
					ProductDetails productDetails = new ProductDetails();
					productDetails.setProductId(productId);
					productDetails.setProductName(initialstring);
					productDetails.setProductCode(initialstring);
					productDetails.setBarCode(initialstring);
					productDetails.setProductPrice(initialstring);
					productDetails.setProductQuantity(initialstring);
					productDetails.setProductInfo(initialstring);
					productDetails.setProductReview(initialstring);
					productDetails.setCategoryDetails(categoryDetailsRepository.findByCategoryId(Long.valueOf(categoryId)));
					productDetails.setVendorDetails(vendorDetailsRepository.findByVendorId(Long.valueOf(vendorId)));
					productDetails.setLocationDetails(locationDetailsRepository.findByLocationId(Long.valueOf(locationId)));
				
					
					 Response response = walmartAPIRequestSender.sendRequest(productDetails); 
					
					productDetailsRepository.save(productDetails);

					productDetails.setProductName(response.item.get(0).getName());
					productDetails.setProductPrice(response.item.get(0).getSalePrice());
					
					
					
					productDetailsRepository.save(productDetails);
					
					
				 return productDetails;
	}
	
	public List<ProductDetails> findByProductId(Long productId) throws JaldiShoppingBaseException {
		 

		
		return (List<ProductDetails>) productDetailsRepository.findByProductId(productId);
	}
	
	
	
	
	

	
}
