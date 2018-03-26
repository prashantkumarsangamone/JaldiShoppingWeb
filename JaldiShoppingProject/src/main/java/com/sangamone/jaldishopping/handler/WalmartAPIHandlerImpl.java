package com.sangamone.jaldishopping.handler;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sangamone.jaldishopping.constants.Messages;
import com.sangamone.jaldishopping.controller.Items;
import com.sangamone.jaldishopping.controller.Response;

import com.sangamone.jaldishopping.repositories.ProductDetailsRepository;
import com.sangamone.jaldishopping.utils.JSONParser;
import com.sangamone.jaldishopping.utils.XmlParser;



public class WalmartAPIHandlerImpl implements WalmartAPIHandler {
	
	@Autowired
	Messages messages;

	
	private String requestUrl;



	public String getRequestUrl() {
		return requestUrl;
	}



	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
	private String requestUrl1;
	
	
	public String getRequestUrl1() {
		return requestUrl1;
	}



	public void setRequestUrl1(String requestUrl1) {
		this.requestUrl1 = requestUrl1;
	}
	
	
	

	@Autowired
	ProductDetailsRepository productDetailsRepository;
	
	public Response sendRequest(Long publisherId)
	 {
try {
			
			
			String smsGatewayUrl = MessageFormat.format(requestUrl,

					URLEncoder.encode(messages.getMessage(Messages.API_KEY), "UTF8"),
					URLEncoder.encode(String.valueOf(publisherId), "UTF8"));
					
					

			URL sendUrl = new URL(smsGatewayUrl);

			HttpURLConnection httpConnection = (HttpURLConnection) sendUrl.openConnection();
			httpConnection.setRequestMethod("GET");

			BufferedReader dataStreamFromUrl = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream()));
			String dataFromUrl = "", dataBuffer = "";

			while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
				dataFromUrl += dataBuffer;

				System.out.println("Response: " + dataFromUrl);
				
			}
			
			dataStreamFromUrl.close();
			
			Response response = XmlParser.parse(dataFromUrl);
			
			
		/*	System.out.println("response:::"+xMLResponse.row.get(0).getItemId());*/
			return response;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}


}





	@Override
	public Response sendRequest1(Long productId) {
try {
			
			
			String smsGatewayUrl = MessageFormat.format(requestUrl,

					URLEncoder.encode(messages.getMessage(Messages.API_KEY), "UTF8"),
					URLEncoder.encode(String.valueOf(productId), "UTF8"));
					
					

			URL sendUrl = new URL(smsGatewayUrl);

			HttpURLConnection httpConnection = (HttpURLConnection) sendUrl.openConnection();
			httpConnection.setRequestMethod("GET");

			BufferedReader dataStreamFromUrl = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream()));
			String dataFromUrl = "", dataBuffer = "";

			while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
				dataFromUrl += dataBuffer;

				System.out.println("Response: " + dataFromUrl);
				
			}
			
			dataStreamFromUrl.close();
			
			Response response = XmlParser.parse(dataFromUrl);
			
			
		/*	System.out.println("response:::"+xMLResponse.row.get(0).getItemId());*/
			return response;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}

	}



	@Override
	public List<Items> sendRequest2(String barCode) {
try {
			
			
			String smsGatewayUrl = MessageFormat.format(requestUrl1,

					URLEncoder.encode(messages.getMessage(Messages.API_KEY), "UTF8"),
					URLEncoder.encode(String.valueOf(barCode), "UTF8"));
					
					

			URL sendUrl = new URL(smsGatewayUrl);

			HttpURLConnection httpConnection = (HttpURLConnection) sendUrl.openConnection();
			httpConnection.setRequestMethod("GET");

			BufferedReader dataStreamFromUrl = new BufferedReader(
					new InputStreamReader(httpConnection.getInputStream()));
			String dataFromUrl = "", dataBuffer = "";

			while ((dataBuffer = dataStreamFromUrl.readLine()) != null) {
				dataFromUrl += dataBuffer;

				System.out.println("Response: " + dataFromUrl);
				
			}
			
			dataStreamFromUrl.close();
			
			List<Items> items = JSONParser.JSONparse(dataFromUrl);
			
			
		/*	System.out.println("response:::"+xMLResponse.row.get(0).getItemId());*/
			return items;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
	}



}





