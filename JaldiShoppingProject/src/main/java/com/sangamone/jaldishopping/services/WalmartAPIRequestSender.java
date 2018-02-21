package com.sangamone.jaldishopping.services;



import java.util.ArrayList;

import com.sangamone.jaldishopping.controller.Response;
import com.sangamone.jaldishopping.domain.ProductDetails;


public interface WalmartAPIRequestSender {



	Response sendRequest(ProductDetails productDetails);
	


}


