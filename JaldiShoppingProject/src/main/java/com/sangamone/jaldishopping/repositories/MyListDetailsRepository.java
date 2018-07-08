package com.sangamone.jaldishopping.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sangamone.jaldishopping.domain.MyListDetails;

public interface MyListDetailsRepository extends CrudRepository< MyListDetails, String>{
	
	

}
