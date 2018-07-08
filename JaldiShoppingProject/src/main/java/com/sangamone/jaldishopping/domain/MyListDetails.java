package com.sangamone.jaldishopping.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;



@Entity
@Table(name = "js_mylist_details")
public class MyListDetails {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	
	private Long mylistId;
	private Long userId;
	private String productId;
	
	
	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
	private UserDetails userDetails;
	
	//@ManyToOne
	@ManyToOne(fetch = FetchType.LAZY)
	@NotFound(action = NotFoundAction.IGNORE)
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
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
