package com.sangamone.jaldishopping.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "js_mylist_details")
public class MyListDetails extends BaseDomain{
	
	
	private Long mylistId;
	private Long userId;
	private Long productId;
	
	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
	private UserDetails userDetails;
	
	@ManyToOne
	@JoinColumn(name = "productId", insertable = false, updatable = false, nullable = false)
	private ProductDetails productDetails;

	public Long getMylistId() {
		return mylistId;
	}

	public void setMylistId(Long mylistId) {
		this.mylistId = mylistId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}
	

}
