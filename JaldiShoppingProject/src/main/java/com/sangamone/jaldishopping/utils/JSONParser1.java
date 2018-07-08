package com.sangamone.jaldishopping.utils;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sangamone.jaldishopping.controller.Beacons;
import com.sangamone.jaldishopping.controller.Estimote;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sangamone.jaldishopping.controller.Estimote;
import com.sangamone.jaldishopping.controller.Items;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

public class JSONParser1 {
	
	public static List<Estimote> JSONparse(String json) throws JsonParseException, JsonMappingException, IOException{

	       /* String json = null;
	        String line = null;*/

	        String jsonData = json;
	      
	        //create ObjectMapper instance
	        ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	        objectMapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));

	        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

	        List<Estimote> estimoteList = objectMapper.readValue(jsonData, new TypeReference<List<Estimote>>(){});

	        /*
	        for(int i=0;i<estimoteList.size();i++){
	            System.out.println("Identifier:"+estimoteList.get(i).getIdentifier());
	            System.out.println("Name:"+estimoteList.get(i).getName());
	            System.out.println("Orientation:"+estimoteList.get(i).getOrientation());
	            System.out.println("UpdatedAt:"+estimoteList.get(i).getUpdated_at());
	            System.out.println("CreatedAt:"+estimoteList.get(i).getCreated_at());
	            for(Beacons beacons : estimoteList.get(i).getBeacons()) {
			        System.out.println("BeaconColor:"+beacons.getBeacon().getColor());
			        System.out.println("BeaconUuid:"+beacons.getBeacon().getUuid());
			        System.out.println("BeaconMajor:"+beacons.getBeacon().getMajor());
			        System.out.println("BeaconMinor:"+beacons.getBeacon().getMinor());
			        System.out.println("Positonx:"+beacons.getPosition().getX());
			        System.out.println("Positony:"+beacons.getPosition().getY());
			        System.out.println("PositionOrientation:"+beacons.getPosition().getOrientation());
	            }
	           // System.out.println(estimoteList.get(0).getLocation());
	           // System.out.println("Location:"+estimoteList.get(i).getLocation().getCountry());
	        }*/
	       
	        return estimoteList;

	}
}
