package com.sangamone.jaldishopping.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.sangamone.jaldishopping.domain.ProductDetails;


public interface ProductDetailsRepository  extends CrudRepository<ProductDetails, String> {

	List<ProductDetails> findByProductId(String productId);

	List<ProductDetails> findByBarCode(String barCode);

	
@Query(value="SELECT * FROM js_product_details where productId=?1",nativeQuery=true)	
	ProductDetails getProductAmount(Long productId);

	/*ProductDetails findByProductId(Long productId);*/


	

}
