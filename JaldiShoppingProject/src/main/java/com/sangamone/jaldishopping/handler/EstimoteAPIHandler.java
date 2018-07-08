package com.sangamone.jaldishopping.handler;

import java.util.List;

import com.sangamone.jaldishopping.controller.Estimote;

public interface EstimoteAPIHandler {
	
	public List<Estimote> sendRequest3(String appId, String appToken);

}
