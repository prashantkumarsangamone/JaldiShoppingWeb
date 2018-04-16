package com.sangamone.jaldishopping.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sangamone.jaldishopping.domain.MyListDetails;

public interface MyListDetailsRepository extends CrudRepository< MyListDetails, String>{
	
	 @Query(value="SELECT * FROM jaldishopping.js_mylist_details mld, js_user_details u,js_product_details p  where mld.userId=u.userId and mld.productId=p.productId and mld.userId=?1",nativeQuery=true)
	List<MyListDetails> getByUserId(Long userId);

}
