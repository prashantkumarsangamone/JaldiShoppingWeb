package com.sangamone.jaldishopping.services;



import java.util.List;

import com.sangamone.jaldishopping.controller.Items;
import com.sangamone.jaldishopping.controller.Response;
import com.sangamone.jaldishopping.domain.ProductDetails;


public interface WalmartAPIRequestSender {



	Response sendRequest(ProductDetails productDetails);

	Response sendRequest1(Long productId);

	List<Items> sendRequest2(String barCode);

	
	


}


