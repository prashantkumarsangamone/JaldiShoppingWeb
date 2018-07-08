package com.sangamone.jaldishopping.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sangamone.jaldishopping.controller.Items;
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
			
			Response response = walmartAPIHandler.sendRequest(Long.valueOf(productDetails.getProductId()));
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
	}


	@Override
	public Response sendRequest1(String productId) {
try {
			
			Response response = walmartAPIHandler.sendRequest1(productId);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
	}


	@Override
	public List<Items> sendRequest2(String barCode) {
try {
			
			List<Items> items = walmartAPIHandler.sendRequest2(barCode);
			return items;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
	}



}

