package com.sangamone.jaldishopping.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class ShaUtils {
	
	public static String getHash(String data) {

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("sha-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Hex.encodeHexString(messageDigest.digest(data.getBytes()));
	}
	public static String getHash1(String data) throws UnsupportedEncodingException {
		
				byte[] asBytes = Base64.decodeBase64(data);

		 return new String(asBytes, "utf-8");
	}
	
	
	public static String setHash(String data) throws UnsupportedEncodingException  {
		
		byte[] asB64 = Base64.encodeBase64(data.getBytes("utf-8"));
	System.out.println(asB64); // Output will be: c29tZSBzdHJpbmc=

		return new String(asB64, "utf-8");
}
	public static void main(String[] args) throws UnsupportedEncodingException{
		String data="1234567890987";
		System.out.println(getHash1(data));
	}
	
	
}