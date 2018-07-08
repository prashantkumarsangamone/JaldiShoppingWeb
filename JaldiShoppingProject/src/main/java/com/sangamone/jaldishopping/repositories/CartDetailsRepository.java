package com.sangamone.jaldishopping.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sangamone.jaldishopping.domain.CartDetails;
import com.sangamone.jaldishopping.domain.CategoryDetails;



public interface CartDetailsRepository extends CrudRepository<CartDetails, String> {

	
	 @Query(value="SELECT * FROM js_cart_details mld, js_user_details u,js_product_details p  where mld.userId=u.userId and mld.productId=p.productId and mld.userId=?1 and deleteFlag='Y'",nativeQuery=true)
	List<CartDetails> getByUserId(Long userId);
	
	 
	 @Query(value="SELECT * FROM js_cart_details mld where mld.deleteFlag='Y' and mld.userId=?1 and mld.productId=?2",nativeQuery=true)
	CartDetails validateDuplicateItem(Long userId, Long productId);

	
	
/*	 
	 @Query(value="SELECT cartDetailsId, SUM(numberOfItems)As numberOfItems,actualAmount, SUM(totalAmount)AS totalAmount,deleteFlag FROM js_cart_details mld, js_user_details u,js_product_details p  where mld.userId=u.userId and mld.productId=p.productId and mld.userId=?1 and deleteFlag='Y'",nativeQuery=true)
	 List<CartDetails> getByBillingDetails(Long userId);*/


	

	 /*@Query(value="UPDATE js_cart_details mld SET deleteFlag='N' where mld.userId=?1 and mld.productId=?2",nativeQuery=true)
	CartDetails deleteCartItem(Long userId, Long productId);*/
	 
	/*List<CartDetails> findByUserId(Long userId);*/
	 


}
