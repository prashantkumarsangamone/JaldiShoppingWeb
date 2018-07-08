package com.sangamone.jaldishopping.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "js_cart_details")
public class CartDetails {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id

	private Long cartDetailsId;
	private Long userId;
	private String productId;
	private String numberOfItems;
	private String actualAmount;
	private String totalAmount;
	private String deleteFlag;
	

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false, nullable = false)
	private UserDetails userDetails;
	
	@ManyToOne
	@JoinColumn(name = "productId", insertable = false, updatable = false, nullable = false)
	private ProductDetails productDetails;
	
	

	public String getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(String numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	public Long getCartDetailsId() {
		return cartDetailsId;
	}

	public void setCartDetailsId(Long cartDetailsId) {
		this.cartDetailsId = cartDetailsId;
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

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}

	

}
