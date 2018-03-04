package com.sangamone.jaldishopping.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "js_cart_details")
public class CartDetails extends BaseDomain{
	

	private Long cartDetailsId;
	
	
	@ManyToOne
	@JoinColumn(name = "userId", insertable = true, updatable = true, nullable = true)
	private UserDetails userDetails;
	
	@ManyToOne
	@JoinColumn(name = "productId", insertable = true, updatable = true, nullable = true)
	private ProductDetails productDetails;

	public Long getCartDetailsId() {
		return cartDetailsId;
	}

	public void setCartDetailsId(Long cartDetailsId) {
		this.cartDetailsId = cartDetailsId;
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
