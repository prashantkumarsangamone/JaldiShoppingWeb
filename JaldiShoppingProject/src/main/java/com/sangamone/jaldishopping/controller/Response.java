package com.sangamone.jaldishopping.controller;


import java.sql.Timestamp;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.hibernate.type.ForeignKeyDirection;


@XmlRootElement(name = "Response")
public class Response {
	
	public List<Row> row;

	
}
