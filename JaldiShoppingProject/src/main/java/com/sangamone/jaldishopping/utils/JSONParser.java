package com.sangamone.jaldishopping.utils;

import java.io.IOException;

import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sangamone.jaldishopping.controller.Items;




public class JSONParser {
	
	public static List<Items> JSONparse(String json) throws JsonParseException, JsonMappingException, IOException{
		 

				String jsonData = json;
				//create ObjectMapper instance
				ObjectMapper objectMapper = new ObjectMapper();
				
				objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
				//convert json string to object
				
				List<Items> items = objectMapper.readValue(jsonData, new TypeReference<List<Items>>(){});
				
				return items;

	}
}
