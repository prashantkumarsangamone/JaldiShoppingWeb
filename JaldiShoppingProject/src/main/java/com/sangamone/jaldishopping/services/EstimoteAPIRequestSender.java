package com.sangamone.jaldishopping.services;

import java.util.List;

import com.sangamone.jaldishopping.controller.Estimote;

public interface EstimoteAPIRequestSender {
	
	List<Estimote> sendRequest3(String appId, String appToken);
	
}
