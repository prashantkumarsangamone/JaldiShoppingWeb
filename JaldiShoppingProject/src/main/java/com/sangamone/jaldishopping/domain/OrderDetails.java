package com.sangamone.jaldishopping.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "js_order_details")
public class OrderDetails extends BaseDomain{
	
	
	
	private Long orderId;
	private String orderAmount;
	private Timestamp orderDate;
	private String quantity;
	
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public PaymentDetails getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(PaymentDetails paymentDetails) {
		this.paymentDetails = paymentDetails;
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
	@JoinColumn(name = "paymentDetailsId", insertable = true, updatable = true, nullable = true)
	private PaymentDetails paymentDetails;
	
	@ManyToOne
	@JoinColumn(name = "userId", insertable = true, updatable = true, nullable = true)
	private UserDetails userDetails;
	
	@ManyToOne
	@JoinColumn(name = "productId", insertable = true, updatable = true, nullable = true)
	private ProductDetails productDetails;
	
	

}


