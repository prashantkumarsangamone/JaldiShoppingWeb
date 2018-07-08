package com.sangamone.jaldishopping.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "js_vendor_details")
public class VendorDetails {
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private Long vendorId;
	private String vendorName;
	private String image;
	


	@ManyToOne
	@JoinColumn(name = "locationId", insertable = true, updatable = true, nullable = true)
	private LocationDetails locationDetails;



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



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public LocationDetails getLocationDetails() {
		return locationDetails;
	}



	public void setLocationDetails(LocationDetails locationDetails) {
		this.locationDetails = locationDetails;
	}


	
	
}



