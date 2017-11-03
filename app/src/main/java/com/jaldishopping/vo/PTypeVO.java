package com.jaldishopping.vo;

import java.util.ArrayList;

public class PTypeVO 
{
	private String itemName;



	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	private ArrayList<PNameVO> payeeDetails;

	public ArrayList<PNameVO> getPayeeDetails() {
		return payeeDetails;
	}
	public void setPayeeDetails(ArrayList<PNameVO> payeeDetails) {
		this.payeeDetails = payeeDetails;
	}
	

}// End of PTypeVO
