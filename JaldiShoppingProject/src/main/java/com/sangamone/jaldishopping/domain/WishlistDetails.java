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
public class WishlistDetails extends BaseDomain{
	
	
	private Long wishlistDetailsId;
	

	public Long getWishlistDetailsId() {
		return wishlistDetailsId;
	}

	public void setWishlistDetailsId(Long wishlistDetailsId) {
		this.wishlistDetailsId = wishlistDetailsId;
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

	@ManyToOne
	@JoinColumn(name = "userId", insertable = true, updatable = true, nullable = true)
	private UserDetails userDetails;

	@ManyToOne
	@JoinColumn(name = "productId", insertable = true, updatable = true, nullable = true)
	private ProductDetails productDetails;
	
	
}


