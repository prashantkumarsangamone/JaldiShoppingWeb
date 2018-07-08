package com.sangamone.jaldishopping.handler;

import java.util.List;

import com.sangamone.jaldishopping.controller.Estimote;
import com.sangamone.jaldishopping.controller.Items;
import com.sangamone.jaldishopping.controller.Response;


public interface WalmartAPIHandler {
	
	

	public Response sendRequest(Long publisherId);

	public Response sendRequest1(String productId);

	public List<Items> sendRequest2(String barCode);

	

	

}


