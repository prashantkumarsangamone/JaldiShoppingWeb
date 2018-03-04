package com.sangamone.jaldishopping.domain;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "js_category_details")
public class CategoryDetails extends BaseDomain {
	
	
	private Long categoryId;
	private String categoryCode;
	private String categoryName;
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}

