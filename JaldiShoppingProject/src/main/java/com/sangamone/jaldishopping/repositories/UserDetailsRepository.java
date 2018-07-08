package com.sangamone.jaldishopping.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sangamone.jaldishopping.domain.UserDetails;


public interface UserDetailsRepository  extends CrudRepository<UserDetails, String> {

	UserDetails findByUserEmail(String userEmail);

	UserDetails findByUserEmailAndUserPassword(String userEmail, String userPassword);

	UserDetails findByUserId(Long userId);
	
	

	

	

}
