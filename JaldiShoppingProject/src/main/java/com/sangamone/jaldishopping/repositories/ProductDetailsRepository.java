package com.sangamone.jaldishopping.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sangamone.jaldishopping.controller.JaldiShoppingResponse;
import com.sangamone.jaldishopping.domain.ProductDetails;


public interface ProductDetailsRepository  extends CrudRepository<ProductDetails, String> {

	List<ProductDetails> findByProductId(Long productId);

	


}
