package com.sangamone.jaldishopping.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "js_product_details")
public class ProductDetails {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id

	
	private String productId;
	private String productName;
	private String productCode;
	private String barCode;
	private String productPrice;
	private String productQuantity;
	private String productType;
	private String productReview;
	private String productRatings;
	private String productDiscount;
	private String productFinalPrice;
	private String currency;
	private String productMoreInfo;
	private String productSize;
	private Long categoryId;
	private Long vendorId;
	private Long locationId;
	private String productImagePath1;
	private String productImagePath2;
	
	

	@ManyToOne
	@JoinColumn(name = "locationId", insertable = false, updatable = false, nullable = false)
	private LocationDetails locationDetails;
	
	@ManyToOne
	@JoinColumn(name = "categoryId", insertable = false, updatable = false, nullable = false)
	private CategoryDetails categoryDetails;
	
	@ManyToOne
	@JoinColumn(name = "vendorId", insertable = false, updatable = false, nullable = false)
	private VendorDetails vendorDetails;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(String productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductReview() {
		return productReview;
	}

	public void setProductReview(String productReview) {
		this.productReview = productReview;
	}

	public String getProductRatings() {
		return productRatings;
	}

	public void setProductRatings(String productRatings) {
		this.productRatings = productRatings;
	}

	public String getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(String productDiscount) {
		this.productDiscount = productDiscount;
	}

	public String getProductFinalPrice() {
		return productFinalPrice;
	}

	public void setProductFinalPrice(String productFinalPrice) {
		this.productFinalPrice = productFinalPrice;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getProductMoreInfo() {
		return productMoreInfo;
	}

	public void setProductMoreInfo(String productMoreInfo) {
		this.productMoreInfo = productMoreInfo;
	}

	public String getProductSize() {
		return productSize;
	}

	public void setProductSize(String productSize) {
		this.productSize = productSize;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getVendorId() {
		return vendorId;
	}

	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public String getProductImagePath1() {
		return productImagePath1;
	}

	public void setProductImagePath1(String productImagePath1) {
		this.productImagePath1 = productImagePath1;
	}

	public String getProductImagePath2() {
		return productImagePath2;
	}

	public void setProductImagePath2(String productImagePath2) {
		this.productImagePath2 = productImagePath2;
	}

	public LocationDetails getLocationDetails() {
		return locationDetails;
	}

	public void setLocationDetails(LocationDetails locationDetails) {
		this.locationDetails = locationDetails;
	}

	public CategoryDetails getCategoryDetails() {
		return categoryDetails;
	}

	public void setCategoryDetails(CategoryDetails categoryDetails) {
		this.categoryDetails = categoryDetails;
	}

	public VendorDetails getVendorDetails() {
		return vendorDetails;
	}

	public void setVendorDetails(VendorDetails vendorDetails) {
		this.vendorDetails = vendorDetails;
	}


}

