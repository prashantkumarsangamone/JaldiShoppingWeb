package com.sangamone.jaldishopping.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sangamone.jaldishopping.controller.Response;
import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.handler.WalmartAPIHandler;
import com.sangamone.jaldishopping.services.WalmartAPIRequestSender;



@Component(value = "rechargeRequestSender")
public class WalmartAPIImpl implements WalmartAPIRequestSender {

	@Autowired
	private WalmartAPIHandler walmartAPIHandler;
	

	public Response sendRequest(ProductDetails productDetails) {
		try {
			
			Response response = walmartAPIHandler.sendRequest(productDetails.getProductId());
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
	}


	@Override
	public Response sendRequest1(Long productId) {
try {
			
			Response response = walmartAPIHandler.sendRequest1(productId);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
	}


	@Override
	public Response sendRequest2(String barCode) {
try {
			
			Response response = walmartAPIHandler.sendRequest2(barCode);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
	}


}

