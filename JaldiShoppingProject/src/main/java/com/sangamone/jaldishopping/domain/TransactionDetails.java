package com.sangamone.jaldishopping.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "js_transaction_details")
public class TransactionDetails {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	
	private Long transactionId;
	private String transactionAmount;
	private Timestamp transactionDate;
	private String quantity;
	private String qrCode;
	
	


	@ManyToOne
	@JoinColumn(name = "paymentDetailsId", insertable = true, updatable = true, nullable = true)
	private PaymentDetails paymentDetails;
	

	@ManyToOne
	@JoinColumn(name = "userId", insertable = true, updatable = true, nullable = true)
	private UserDetails userDetails;
	

	@ManyToOne
	@JoinColumn(name = "productId", insertable = true, updatable = true, nullable = true)
	private ProductDetails productDetails;


	public Long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}


	public String getTransactionAmount() {
		return transactionAmount;
	}


	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


	public Timestamp getTransactionDate() {
		return transactionDate;
	}


	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}


	public String getQuantity() {
		return quantity;
	}


	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


	public String getQrCode() {
		return qrCode;
	}


	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
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
	
	
}





