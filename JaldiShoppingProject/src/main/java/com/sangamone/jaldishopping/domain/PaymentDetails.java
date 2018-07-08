package com.sangamone.jaldishopping.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "js_payment_details")
public class PaymentDetails{
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id

	private Long paymentDetailsId;
	private String paymentTypeName;
	public Long getPaymentDetailsId() {
		return paymentDetailsId;
	}
	public void setPaymentDetailsId(Long paymentDetailsId) {
		this.paymentDetailsId = paymentDetailsId;
	}
	public String getPaymentTypeName() {
		return paymentTypeName;
	}
	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	

}

