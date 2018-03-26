package com.sangamone.jaldishopping.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sangamone.jaldishopping.controller.Items;
import com.sangamone.jaldishopping.controller.Response;
import com.sangamone.jaldishopping.domain.AuthorityDetails;
import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.domain.UserDetails;
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

	@Override
	public ProductDetails addProductDetails(Long productId) {
		 String initialstring="null"; 
		 
			long initialvalue = 1; 
			 Response response = walmartAPIRequestSender.sendRequest1(productId);
			 System.out.println(response);
			ProductDetails productDetails1 = new ProductDetails();
			    productDetails1.setId(initialstring);
			    productDetails1.setProductId(Long.valueOf(response.item.get(0).getItemId()));
			    productDetails1.setProductName(response.item.get(0).getName());
				productDetails1.setProductCode(initialstring);
				productDetails1.setBarCode(response.item.get(0).getUpc());
				productDetails1.setProductPrice(response.item.get(0).getSalePrice());
				productDetails1.setProductQuantity(initialstring);
				productDetails1.setProductInfo(response.item.get(0).getCategoryPath());
				productDetails1.setProductReview(response.item.get(0).getNumReviews());
				productDetails1.setCategoryId(initialvalue);
				productDetails1.setLocationId(initialvalue);
				productDetails1.setVendorId(initialvalue);
				
				productDetailsRepository.save(productDetails1);
				return productDetails1;
	}
	
	@Override
	public List<ProductDetails> findByBarCode(String barCode) throws JaldiShoppingBaseException {
	
			
			return (List<ProductDetails>) productDetailsRepository.findByBarCode(barCode);
		
	}
	
	@Override
	public ProductDetails addProductDetails1(String barCode) {
		String initialstring="null"; 
		 
		long initialvalue = 1; 
		 List<Items> items = walmartAPIRequestSender.sendRequest2(barCode);
		
		 System.out.println(items);
		 for(int i=0;i<=items.size();i++){
			 System.out.println("FreeShip"+items.get(0).getFreeShipToStore());
		 }
		 
		ProductDetails productDetails1 = new ProductDetails();
		    productDetails1.setId(initialstring);
		    productDetails1.setProductId(Long.valueOf(items.get(0).getItems().get(0).getItemId()));
		    System.out.println("Product Id"+items.get(0).getItems().get(0).getItemId());
		    productDetails1.setProductName(items.get(0).getItems().get(0).getName());
		    System.out.println("Product Id"+items.get(0).getItems().get(0).getName());
			productDetails1.setProductCode(initialstring);
			productDetails1.setBarCode(items.get(0).getItems().get(0).getUpc());
			productDetails1.setProductPrice(items.get(0).getItems().get(0).getSalePrice());
			productDetails1.setProductQuantity(initialstring);
			productDetails1.setProductInfo(items.get(0).getItems().get(0).getCategoryPath());
			productDetails1.setProductReview(items.get(0).getItems().get(0).getNumReviews());
			productDetails1.setCategoryId(initialvalue);
			productDetails1.setLocationId(initialvalue);
			productDetails1.setVendorId(initialvalue);
			
			productDetailsRepository.save(productDetails1);
			return productDetails1;
	}
	
	
	
	
	
	

	
}
