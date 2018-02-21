package com.sangamone.jaldishopping.handler;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;



public class SmsHandlerImpl implements SmsHandler {

	String username = "sangam";
	String password = "$angamOne";
	String fl = "0";
	String source = "APNKHT";
	String gwid = "2";

	private String smsSendUrl;

	public String getSmsSendUrl() {
		return smsSendUrl;
	}

	public void setSmsSendUrl(String smsSendUrl) {
		this.smsSendUrl = smsSendUrl;
	}

	public void sendSMS(long mobileNumber, String message){

		try {
			
			System.out.println("message"+message);
			
			String smsGatewayUrl = MessageFormat.format(smsSendUrl,

					URLEncoder.encode(username, "UTF8"),

					URLEncoder.encode(password, "UTF8"),
					URLEncoder.encode(String.valueOf(mobileNumber), "UTF8"),
					URLEncoder.encode(source, "UTF8"),
					URLEncoder.encode(message, "UTF8"),
					URLEncoder.encode(fl, "UTF8"),
					URLEncoder.encode(gwid, "UTF8"));

	
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
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}