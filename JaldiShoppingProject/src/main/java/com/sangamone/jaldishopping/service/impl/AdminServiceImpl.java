package com.sangamone.jaldishopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sangamone.jaldishopping.domain.AuthorityDetails;
import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.repositories.AuthorityDetailsRepository;
import com.sangamone.jaldishopping.repositories.UserDetailsRepository;
import com.sangamone.jaldishopping.services.AdminService;
import com.sangamone.jaldishopping.services.MessageSender;
import com.sangamone.jaldishopping.utils.RandomString;
import com.sangamone.jaldishopping.utils.ShaUtils;




@Component("adminService")

public class AdminServiceImpl implements AdminService 
{

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private MessageSender messageSender;
	
	@Autowired
	private AuthorityDetailsRepository authorityDetailsRepository;


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
	
	


		
	

	
}
