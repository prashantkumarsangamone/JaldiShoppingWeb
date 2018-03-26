package com.sangamone.jaldishopping.controller;



import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;



@XmlRootElement(name = "Response")
public class Response {
	
	public List<Item> item;
	
	
}
