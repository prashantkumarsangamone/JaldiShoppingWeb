package com.sangamone.jaldishopping.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sangamone.jaldishopping.controller.Estimote;
import com.sangamone.jaldishopping.handler.EstimoteAPIHandler;
import com.sangamone.jaldishopping.services.EstimoteAPIRequestSender;

@Component(value = "estimoteAPIRequestSender")
public class EstimoteAPIImpl implements EstimoteAPIRequestSender {

	@Autowired
	private EstimoteAPIHandler estimoteAPIHandler;

	@Override
	public List<Estimote> sendRequest3(String appId, String appToken) {
		// TODO Auto-generated method stub
		try {
					
					List<Estimote> items = estimoteAPIHandler.sendRequest3(appId,appToken);
					return items;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				
				}
			}
	}

	
