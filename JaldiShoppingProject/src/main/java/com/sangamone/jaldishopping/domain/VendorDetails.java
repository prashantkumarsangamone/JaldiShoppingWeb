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
public class VendorDetails extends BaseDomain{
	
	
	private Long vendorId;
	private String vendorName;

	

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



	public LocationDetails getLocationDetails() {
		return locationDetails;
	}



	public void setLocationDetails(LocationDetails locationDetails) {
		this.locationDetails = locationDetails;
	}



	@ManyToOne
	@JoinColumn(name = "locationId", insertable = true, updatable = true, nullable = true)
	private LocationDetails locationDetails;
	
	
	
}



