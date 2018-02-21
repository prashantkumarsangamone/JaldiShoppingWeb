package com.sangamone.jaldishopping.controller;

import java.util.List;
import com.sangamone.jaldishopping.domain.UserDetails;

public class JaldiShoppingResponse {
	
	private String responseCode;
	
	private String description;
	
	private Long vendorId;
	
	private String vendorName;
	
	private List<UserDetails> userDetails;
	
	
	

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public List<UserDetails> getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(List<UserDetails> userDetails) {
		this.userDetails = userDetails;
	}
	
	
	
}
