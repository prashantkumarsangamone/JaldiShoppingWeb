package com.sangamone.jaldishopping.domain;

import javax.persistence.*;

@Entity
@Table(name = "js_authorities_details")
public class AuthorityDetails{
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id	
	 private Long itemCategoryId;

	private Long authorityId;
	
	private String userEmail;	

	private String authority;
	
	
	@ManyToOne
	@JoinColumn(name = "userEmail", insertable = false, updatable = false, nullable = false)
	private UserDetails userDetails;


	public Long getItemCategoryId() {
		return itemCategoryId;
	}


	public void setItemCategoryId(Long itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}


	public Long getAuthorityId() {
		return authorityId;
	}


	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}


	public UserDetails getUserDetails() {
		return userDetails;
	}


	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

}
