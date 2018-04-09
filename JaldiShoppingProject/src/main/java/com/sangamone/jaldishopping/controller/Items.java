package com.sangamone.jaldishopping.controller;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Items {
	
	@JsonIgnoreProperties(value={"items","attributes","gender","addToCartUrl","affiliateAddToCartUrl","freeShippingOver50Dollars","imageEntities","variants","offerType","isTwoDayShippingEligible","availableOnline"})
	
	public List<Items> items;
	
	public String ItemId;
	
	public String parentItemId;

	public String Name;

	public String Msrp;

	public String SalePrice;
	
	public String Upc;
	
	public String CategoryPath;
	
	public String ShortDescription;
	
	public String LongDescription;
	
	public String BrandName;
	
	public String ThumbnailImage;
	
	public String MediumImage;
	
	public String LargeImage;
	
	public String ProductTrackingUrl;
	
	public String NinetySevenCentShipping;
	
	public String StandardShipRate;
	
	public String Size;
	
	public String Color;
	
	public String Marketplace;
	
	public String ShipToStore;
	
	public String FreeShipToStore;
	
	public String ModelNumber;
	
	public String ProductUrl;
	
	public String CustomerRating;
	
	public String NumReviews;
	
	public String CustomerRatingImage;
	
	public String CategoryNode;
	
	public String Bundle;
	
	public String Clearance;
	
	public String PreOrder;
	
	public String Stock;

	

	public String getItemId() {
		return ItemId;
	}

	public void setItemId(String itemId) {
		ItemId = itemId;
	}

	public String getParentItemId() {
		return parentItemId;
	}

	public void setParentItemId(String parentItemId) {
		this.parentItemId = parentItemId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMsrp() {
		return Msrp;
	}

	public void setMsrp(String msrp) {
		Msrp = msrp;
	}

	public String getSalePrice() {
		return SalePrice;
	}

	public void setSalePrice(String salePrice) {
		SalePrice = salePrice;
	}

	public String getUpc() {
		return Upc;
	}

	public void setUpc(String upc) {
		Upc = upc;
	}

	public String getCategoryPath() {
		return CategoryPath;
	}

	public void setCategoryPath(String categoryPath) {
		CategoryPath = categoryPath;
	}

	public String getShortDescription() {
		return ShortDescription;
	}

	public void setShortDescription(String shortDescription) {
		ShortDescription = shortDescription;
	}

	public String getLongDescription() {
		return LongDescription;
	}

	public void setLongDescription(String longDescription) {
		LongDescription = longDescription;
	}

	public String getBrandName() {
		return BrandName;
	}

	public void setBrandName(String brandName) {
		BrandName = brandName;
	}

	public String getThumbnailImage() {
		return ThumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		ThumbnailImage = thumbnailImage;
	}

	public String getMediumImage() {
		return MediumImage;
	}

	public void setMediumImage(String mediumImage) {
		MediumImage = mediumImage;
	}

	public String getLargeImage() {
		return LargeImage;
	}

	public void setLargeImage(String largeImage) {
		LargeImage = largeImage;
	}

	public String getProductTrackingUrl() {
		return ProductTrackingUrl;
	}

	public void setProductTrackingUrl(String productTrackingUrl) {
		ProductTrackingUrl = productTrackingUrl;
	}

	public String getNinetySevenCentShipping() {
		return NinetySevenCentShipping;
	}

	public void setNinetySevenCentShipping(String ninetySevenCentShipping) {
		NinetySevenCentShipping = ninetySevenCentShipping;
	}

	public String getStandardShipRate() {
		return StandardShipRate;
	}

	public void setStandardShipRate(String standardShipRate) {
		StandardShipRate = standardShipRate;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		Color = color;
	}

	public String getMarketplace() {
		return Marketplace;
	}

	public void setMarketplace(String marketplace) {
		Marketplace = marketplace;
	}

	public String getShipToStore() {
		return ShipToStore;
	}

	public void setShipToStore(String shipToStore) {
		ShipToStore = shipToStore;
	}

	public String getFreeShipToStore() {
		return FreeShipToStore;
	}

	public void setFreeShipToStore(String freeShipToStore) {
		FreeShipToStore = freeShipToStore;
	}

	public String getModelNumber() {
		return ModelNumber;
	}

	public void setModelNumber(String modelNumber) {
		ModelNumber = modelNumber;
	}

	public String getProductUrl() {
		return ProductUrl;
	}

	public void setProductUrl(String productUrl) {
		ProductUrl = productUrl;
	}

	public String getCustomerRating() {
		return CustomerRating;
	}

	public void setCustomerRating(String customerRating) {
		CustomerRating = customerRating;
	}

	public String getNumReviews() {
		return NumReviews;
	}

	public void setNumReviews(String numReviews) {
		NumReviews = numReviews;
	}

	public String getCustomerRatingImage() {
		return CustomerRatingImage;
	}

	public void setCustomerRatingImage(String customerRatingImage) {
		CustomerRatingImage = customerRatingImage;
	}

	public String getCategoryNode() {
		return CategoryNode;
	}

	public void setCategoryNode(String categoryNode) {
		CategoryNode = categoryNode;
	}

	public String getBundle() {
		return Bundle;
	}

	public void setBundle(String bundle) {
		Bundle = bundle;
	}

	public String getClearance() {
		return Clearance;
	}

	public void setClearance(String clearance) {
		Clearance = clearance;
	}

	public String getPreOrder() {
		return PreOrder;
	}

	public void setPreOrder(String preOrder) {
		PreOrder = preOrder;
	}

	public String getStock() {
		return Stock;
	}

	public void setStock(String stock) {
		Stock = stock;
	}

	public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}


}
