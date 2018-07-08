package com.sangamone.jaldishopping.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "js_wishlist_details")
public class WishlistDetails {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	
	private Long wishlistDetailsId;
	private Long userId;
	private String productId;
	private String deleteFlag;
	

	
	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
	private UserDetails userDetails;

	@ManyToOne
	@JoinColumn(name = "productId", insertable = false, updatable = false, nullable = false)
	private ProductDetails productDetails;

	public Long getWishlistDetailsId() {
		return wishlistDetailsId;
	}

	public void setWishlistDetailsId(Long wishlistDetailsId) {
		this.wishlistDetailsId = wishlistDetailsId;
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

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
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


