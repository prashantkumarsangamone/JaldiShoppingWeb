package com.sangamone.jaldishopping.domain;


import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "js_user_details")
public class UserDetails extends BaseDomain{
	
	  
	private Long userId;
	private String firstName;
	private String lastName;
	private String userEmail;
	private String userMobile;
	private String userPassword;
	private Long zipCode;
	private Long enabled;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserMobile() {
		return userMobile;
	}
	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Long getZipCode() {
		return zipCode;
	}
	public void setZipCode(Long zipCode) {
		this.zipCode = zipCode;
	}
	public Long getEnabled() {
		return enabled;
	}
	public void setEnabled(Long enabled) {
		this.enabled = enabled;
	}
	
}

