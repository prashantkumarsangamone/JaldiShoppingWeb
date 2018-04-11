package com.sangamone.jaldishopping.repositories;

import org.springframework.data.repository.CrudRepository;
import com.sangamone.jaldishopping.domain.MyListDetails;
import com.sangamone.jaldishopping.domain.UserDetails;

public interface MyListDetailsRepository extends CrudRepository< MyListDetails, String>{

	MyListDetails findByUserId(String userId);

}
