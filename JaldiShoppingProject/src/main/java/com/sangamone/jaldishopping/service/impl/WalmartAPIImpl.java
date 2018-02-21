/*package com.sangamone.jaldishopping.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sangamone.mobilerecharge.controller.Response;
import com.sangamone.mobilerecharge.domain.TransactionDetails;
import com.sangamone.mobilerecharge.handler.RechargeHandler;
import com.sangamone.mobilerecharge.repositories.TransactionDetailsRepository;
import com.sangamone.mobilerecharge.service.RechargeRequestSender;



@Component(value = "rechargeRequestSender")
public class WalmartAPIImpl implements RechargeRequestSender {

	@Autowired
	private RechargeHandler rechargeHandler;
	

	public Response sendRequest(TransactionDetails transactionDetails) {
		try {
			
			Response response = rechargeHandler.sendRequest(transactionDetails.getMobileNumber(),transactionDetails.getOperatorId(),
					transactionDetails.getRechargeAmount(),transactionDetails.getUniqueId());
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		
		}
		
		
	}
	
}

*/