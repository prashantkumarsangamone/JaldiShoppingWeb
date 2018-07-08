package com.sangamone.jaldishopping.controller;

import java.util.List;

import com.sangamone.jaldishopping.domain.BeaconLocations;
import com.sangamone.jaldishopping.domain.CartDetails;
import com.sangamone.jaldishopping.domain.MyListDetails;
import com.sangamone.jaldishopping.domain.OrderDetails;
import com.sangamone.jaldishopping.domain.ProductDetails;
import com.sangamone.jaldishopping.domain.TransactionDetails;
import com.sangamone.jaldishopping.domain.UserDetails;
import com.sangamone.jaldishopping.domain.VendorDetails;
import com.sangamone.jaldishopping.domain.WishlistDetails;

public class JaldiShoppingResponse {
	
	private String responseCode;
	
	private String description;
	
	private Long vendorId;
	
	private String vendorName;
	
	private String Image;
	
	private List<UserDetails> userDetails;
	
	private List<VendorDetails> vendorDetails;
	
	private List<ProductDetails> productDetails;
	
	private List<MyListDetails> myListDetails;
	
	private List<WishlistDetails> wishlistDetails;
	
	private List<CartDetails> cartDetails;
	
	private List<OrderDetails> orderDetails;
	
	private List<BeaconLocations> beaconLocations;
	
	private List<TransactionDetails> transactionDetails;

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

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public List<VendorDetails> getVendorDetails() {
		return vendorDetails;
	}

	public void setVendorDetails(List<VendorDetails> vendorDetails) {
		this.vendorDetails = vendorDetails;
	}

	public List<ProductDetails> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetails> productDetails) {
		this.productDetails = productDetails;
	}

	public List<MyListDetails> getMyListDetails() {
		return myListDetails;
	}

	public void setMyListDetails(List<MyListDetails> myListDetails) {
		this.myListDetails = myListDetails;
	}

	public List<CartDetails> getCartDetails() {
		return cartDetails;
	}

	public void setCartDetails(List<CartDetails> cartDetails) {
		this.cartDetails = cartDetails;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<BeaconLocations> getBeaconLocations() {
		return beaconLocations;
	}

	public void setBeaconLocations(List<BeaconLocations> beaconLocations) {
		this.beaconLocations = beaconLocations;
	}

	public List<WishlistDetails> getWishlistDetails() {
		return wishlistDetails;
	}

	public void setWishlistDetails(List<WishlistDetails> wishlistDetails) {
		this.wishlistDetails = wishlistDetails;
	}

	public List<TransactionDetails> getTransactionDetails() {
		return transactionDetails;
	}

	public void setTransactionDetails(List<TransactionDetails> transactionDetails) {
		this.transactionDetails = transactionDetails;
	}

	
	
	
	
	
}
