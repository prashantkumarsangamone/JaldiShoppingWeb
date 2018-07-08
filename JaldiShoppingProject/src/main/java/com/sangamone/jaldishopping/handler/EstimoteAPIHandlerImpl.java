package com.sangamone.jaldishopping.handler;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

import com.sangamone.jaldishopping.constants.Messages;
import com.sangamone.jaldishopping.controller.Estimote;
import com.sangamone.jaldishopping.utils.JSONParser1;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Encoder;

public class EstimoteAPIHandlerImpl implements EstimoteAPIHandler {

    @Autowired
    Messages messages;

    private String requestUrl2;



    public String getRequestUrl2() {
        return requestUrl2;
    }



    public void setRequestUrl2(String requestUrl2) {
        this.requestUrl2 = requestUrl2;
    }

    @Override
    public List<Estimote> sendRequest3(String appId, String appToken) {
        try {


            String smsGatewayUrl = MessageFormat.format(requestUrl2,

                    URLEncoder.encode(String.valueOf(appId), "UTF8"),
                    URLEncoder.encode(String.valueOf(appToken), "UTF8"));

            String authString = appId + ":" + appToken;
            String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
            Client restClient = Client.create();
            WebResource webResource = restClient.resource(smsGatewayUrl);
            ClientResponse resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc).get(ClientResponse.class);
            if(resp.getStatus() != 200){
                System.err.println("Unable to connect to the server");
            }
            String output = resp.getEntity(String.class);
            System.out.println("response: "+output);
            List<Estimote> estimote = JSONParser1.JSONparse(output);

            /*System.out.println("response:::"+xMLResponse.row.get(0).getItemId());*/
            return estimote;


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }

}
