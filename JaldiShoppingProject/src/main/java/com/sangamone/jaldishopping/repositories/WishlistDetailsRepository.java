package com.sangamone.jaldishopping.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sangamone.jaldishopping.domain.CartDetails;
import com.sangamone.jaldishopping.domain.MyListDetails;
import com.sangamone.jaldishopping.domain.WishlistDetails;

public interface WishlistDetailsRepository  extends CrudRepository<WishlistDetails, String> {

	
	 @Query(value="SELECT * FROM js_wishlist_details mld, js_user_details u,js_product_details p  where deleteFlag='Y' and mld.userId=u.userId and mld.productId=p.productId and mld.userId=?1",nativeQuery=true)
	List<WishlistDetails> getByUserId(Long valueOf);

	 
	 @Query(value="SELECT * FROM js_wishlist_details mld where deleteFlag='Y' and mld.userId=?1 and mld.productId=?2",nativeQuery=true)	
	WishlistDetails validateDuplicateItem(Long valueOf, Long valueOf2);



}
