package com.sangamone.jaldishopping.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "js_offer_details")
public class OfferDetails extends BaseDomain{
	
	

	private Long offerDetailsId;
	private String offerPercentage;
	
	public Long getOfferDetailsId() {
		return offerDetailsId;
	}

	public void setOfferDetailsId(Long offerDetailsId) {
		this.offerDetailsId = offerDetailsId;
	}

	public String getOfferPercentage() {
		return offerPercentage;
	}

	public void setOfferPercentage(String offerPercentage) {
		this.offerPercentage = offerPercentage;
	}

	public ProductDetails getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(ProductDetails productDetails) {
		this.productDetails = productDetails;
	}

	@ManyToOne
	@JoinColumn(name = "productId", insertable = true, updatable = true, nullable = true)
	private ProductDetails productDetails;

}

