package com.sangamone.jaldishopping.utils;


import org.apache.commons.lang3.RandomStringUtils;

public class RandomString {

public static String generateRandomString(){
		
		return RandomStringUtils.random(8, true, true);
		
	}
	
}
